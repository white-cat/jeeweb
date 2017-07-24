package cn.jeeweb.modules.codegen.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.mapper.JsonMapper;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.PropertiesUtil;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.codegen.codegenerator.data.DbTableInfo;
import cn.jeeweb.modules.codegen.codegenerator.data.GeneratorInfo;
import cn.jeeweb.modules.codegen.entity.Column;
import cn.jeeweb.modules.codegen.entity.Scheme;
import cn.jeeweb.modules.codegen.entity.Table;
import cn.jeeweb.modules.codegen.service.ISchemeService;
import cn.jeeweb.modules.codegen.service.ITableService;
import cn.jeeweb.modules.sys.entity.Menu;
import cn.jeeweb.modules.sys.service.IMenuService;

@Controller
@RequestMapping("${admin.url.prefix}/codegen/table")
@RequiresPathPermission("codegen:table")
public class TableController extends BaseCRUDController<Table, String> {
	@Autowired
	private ITableService tableService;
	@Autowired
	private ISchemeService schemeService;
	@Autowired
	private IMenuService menuService;

	private String[] types = { "String", "Double", "Text", "Date", "Blob", "Short", "Integer", "User",
			"this" };

	@Override
	public void preEdit(Table entity, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (entity != null && !StringUtils.isEmpty(entity.getId()) && StringUtils.isEmpty(entity.getClassName())) {
			String entityName = StringUtils.toUpperCaseFirstOne(StringUtils.underlineToCamel(entity.getTableName()));
			entity.setClassName(entityName);
		}
		// 查询表明
		List<DbTableInfo> dbTableInfos = tableService.getTableNameList();
		request.setAttribute("dbTableInfos", dbTableInfos);
		List<Column> columns = entity.getColumns();
		String columnsJson = JsonMapper.toJsonString(columns);
		// String columnsJson=JSON.toJSONString(columns);
		request.setAttribute("columns", columnsJson);
		List<String> javaTypes = Arrays.asList(types);
		String extendTypes = "";

		for (Column column : columns) {
			String javaType = column.getJavaType();
			if (javaType.contains("|")) {
				String[] innerJavaTypes = javaType.split("\\|");
				if (!javaTypes.contains(innerJavaTypes[1]) && !extendTypes.contains(innerJavaTypes[1])) {
					extendTypes += ";";
					extendTypes += javaType + ":" + innerJavaTypes[1];
				}
			}
		}
		request.setAttribute("extendTypes", extendTypes);
		request.setAttribute("javaTypes", javaTypes);
	}

	@Override
	public void preSave(Table table, HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isEmpty(table.getId())) {
			Table oldTable = commonService.get(table.getId());
			String[] fields = { "tableName", "remarks" };
			if (!ObjectUtils.isEquals(oldTable, table, fields)) {
				table.setSyncDatabase(Boolean.FALSE);
			}
			List<Column> oldColumnList = oldTable.getColumns();
			// 字段
			String columnListStr = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("columnList"));
			List<Column> columnList = JSONObject.parseArray(columnListStr, Column.class);
			if (checkIsModify(oldColumnList, columnList)) {
				table.setSyncDatabase(Boolean.FALSE);
			}
		}
	}

	public Boolean checkIsModify(List<Column> oldColumnList, List<Column> newColumnList) {
		if (oldColumnList.size() != newColumnList.size()) {
			return Boolean.TRUE;
		}
		for (Column columnEntity : newColumnList) {
			Boolean isUpdate = Boolean.FALSE;
			for (Column oldColumnEntity : oldColumnList) {
				if (columnEntity.getId().equals(oldColumnEntity.getId())) {
					String[] fields = { "remarks", "columnName", "typeName", "columnSize", "parmaryKey", "importedKey",
							"importedKey", "nullable", "columnDef", "decimalDigits", "javaType" };
					if (ObjectUtils.isEquals(columnEntity, oldColumnEntity, fields)) {
						isUpdate = Boolean.FALSE;
						break;
					}
				}
				isUpdate = Boolean.TRUE;
			}
			if (isUpdate) {
				return isUpdate;
			}
		}
		return Boolean.FALSE;
	}

	@RequestMapping(value = "{id}/generateCode", method = RequestMethod.GET)
	public String generateCode(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Table table = tableService.get(id);
		if (!table.getSyncDatabase()) {
			return display("un_sync_database");
		}
		Scheme scheme = schemeService.get("table.id", id);
		if (scheme == null) {
			String tableName = table.getTableName();
			String remarks = table.getRemarks();
			String entityName = StringUtils.toUpperCaseFirstOne(StringUtils.underlineToCamel(tableName));
			String properiesName = "codegen.properties";
			PropertiesUtil propertiesUtil = new PropertiesUtil(properiesName);
			String pathName = propertiesUtil.getString("default.path.name");
			String packageName = propertiesUtil.getString("default.package.name");
			String functionAuthor = propertiesUtil.getString("default.function.author");
			scheme = new Scheme();
			scheme.setPathName(pathName);
			scheme.setPackageName(packageName);
			scheme.setEntityName(entityName);
			scheme.setTableName(tableName);
			scheme.setFunctionAuthor(functionAuthor);
			scheme.setFunctionDesc(remarks);
			scheme.setFunctionName(remarks);
			scheme.setTable(table);
			schemeService.save(scheme);
		}
		request.setAttribute("scheme", scheme);
		request.setAttribute("tableid", id);
		return display("generate_code");
	}

	@RequestMapping(value = "generateCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson generateCode(Scheme scheme, GeneratorInfo generatorInfo, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("代码生成成功");
		try {
			String tableid = request.getParameter("tableid");
			Table table = tableService.get(tableid);
			scheme.setTableType(table.getTableType());
			// FORM NULL不更新
			Scheme oldEntity = schemeService.get(scheme.getId());
			MyBeanUtils.copyBeanNotNull2Bean(scheme, oldEntity);
			schemeService.update(oldEntity);
			tableService.generateCode(table, generatorInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("代码生成失败");
		}
		return ajaxJson;
	}

	@RequestMapping(value = "/importDatabase", method = RequestMethod.GET)
	public String importDatabase(HttpServletRequest request, HttpServletResponse response) {
		// 查询表明
		List<DbTableInfo> dbTableInfos = tableService.getTableNameList();
		request.setAttribute("dbTableInfos", dbTableInfos);
		request.setAttribute("data", new Table());
		return display("import_database");
	}

	@RequestMapping(value = "/importDatabase", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importDatabase(HttpServletRequest request, Table table, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("数据库导入成功");
		try {
			tableService.importDatabase(table);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("数据库导入失败");
		}
		return ajaxJson;
	}

	@RequestMapping(value = "{id}/syncDatabase", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson syncDatabase(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("数据库同步成功");
		if (JeewebPropertiesUtil.getProperties().getBoolean("demoMode")) {
			ajaxJson.fail("演示模式，不允许同步数据库！");
			return ajaxJson;
		}
		try {
			tableService.dropTable(id);
			tableService.syncDatabase(id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(e.getMessage());
		}
		return ajaxJson;
	}

	@RequestMapping(value = "{id}/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson remove(@PathVariable("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("移除成功");
		try {
			tableService.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("移除失败");
		}
		return ajaxJson;
	}

	@RequestMapping(value = "{id}/createMenu", method = RequestMethod.GET)
	public String createMenu(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("tableid", request.getParameter("id"));
		Scheme scheme = schemeService.get("table.id", id);
		if (scheme == null || StringUtils.isEmpty(scheme.getModuleName())) {
			return display("un_generate_code");
		}
		Menu menu = new Menu();
		String moduleName = scheme.getModuleName();
		String entityName = scheme.getEntityName();
		String url = moduleName.toLowerCase() + "/" + entityName.toLowerCase();
		String permission = moduleName.toLowerCase() + ":" + entityName.toLowerCase();
		menu.setUrl(url);
		menu.setPermission(permission);
		menu.setName(scheme.getFunctionName());
		menu.setIsshow((short) 1);
		request.setAttribute("data", menu);
		return display("create_menu");
	}

	@RequestMapping(value = "{id}/saveMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("菜单生成成功");
		try {
			menuService.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("菜单生成失败");
		}
		return ajaxJson;
	}
}
