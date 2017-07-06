package cn.jeeweb.modules.codegen.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.FreeMarkerUtils;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.codegen.codegenerator.GeneratorManagor;
import cn.jeeweb.modules.codegen.codegenerator.data.AttributeInfo;
import cn.jeeweb.modules.codegen.codegenerator.data.DbColumnInfo;
import cn.jeeweb.modules.codegen.codegenerator.data.DbTableInfo;
import cn.jeeweb.modules.codegen.codegenerator.data.GeneratorInfo;
import cn.jeeweb.modules.codegen.codegenerator.exception.GenerationException;
import cn.jeeweb.modules.codegen.codegenerator.utils.CodeGenUtils;
import cn.jeeweb.modules.codegen.dao.IGeneratorDao;
import cn.jeeweb.modules.codegen.entity.ColumnEntity;
import cn.jeeweb.modules.codegen.entity.TableEntity;
import cn.jeeweb.modules.codegen.service.IColumnService;
import cn.jeeweb.modules.codegen.service.ITableService;
import cn.jeeweb.modules.sys.entity.Menu;
import cn.jeeweb.modules.sys.service.IMenuService;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: TableService.java
 * @package cn.jeeweb.modules.codegen.service.impl
 * @description:
 * @author: 王存见
 * @date: 2017年5月8日 上午11:17:41
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
@Transactional
@Service("tableService")
public class TableServiceImpl extends CommonServiceImpl<TableEntity> implements ITableService {
	@Autowired
	private IGeneratorDao generatorDao;

	@Autowired
	private IColumnService columnService;

	private GeneratorManagor generatorManagor;

	private IMenuService menuService;

	@PostConstruct
	public void initTable() {
		generatorManagor = new GeneratorManagor();
	}

	@Override
	public List<DbTableInfo> getTableNameList() {
		return generatorDao.getDbTables();
	}

	@Override
	public void save(TableEntity table) {
		table.setSyncDatabase(Boolean.FALSE);
		// 保存主表
		super.save(table);
		// 字段
		String columnListStr = StringEscapeUtils.unescapeHtml4(ServletUtils.getRequest().getParameter("columnList"));
		List<ColumnEntity> columnList = JSONObject.parseArray(columnListStr, ColumnEntity.class);
		for (ColumnEntity column : columnList) {
			// 保存字段列表
			column.setTable(table);
			columnService.save(column);
		}
	}

	@Override
	public void update(TableEntity table) {
		// 删除已经删除的数据
		List<ColumnEntity> oldColumnList = columnService.list("table", table);
		// 字段
		String columnListStr = StringEscapeUtils.unescapeHtml4(ServletUtils.getRequest().getParameter("columnList"));
		List<ColumnEntity> columnList = JSONObject.parseArray(columnListStr, ColumnEntity.class);

		// 更新主表
		super.update(table);
		columnList = JSONObject.parseArray(columnListStr, ColumnEntity.class);
		List<String> newsIdList = new ArrayList<String>();
		int sort = 1;
		// 保存或更新数据
		for (ColumnEntity column : columnList) {
			column.setSort(sort);
			// 保存字段列表
			if (StringUtils.isEmpty(column.getId()) || column.getId().contains("templateid")) {
				// 保存字段列表
				column.setTable(table);
				columnService.save(column);
			} else {
				// 设置不变更的字段
				columnService.merge(column);
			}
			sort++;
			newsIdList.add(column.getId());
		}

		// 删除老数据
		for (ColumnEntity column : oldColumnList) {
			String columnId = column.getId();
			if (!newsIdList.contains(columnId)) {
				columnService.deleteById(columnId);
			}
		}

	}

	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object id : ids) {
			deleteById((Serializable) id);
		}
	}

	@Override
	public void deleteById(Serializable id) {
		// 删除已经删除的数据
		TableEntity table = get(id);
		// 先刪除表
		try {
			generatorDao.dropTable(table.getTableName());
		} catch (Exception e) {
			// 部分数据库在没有表而执行删表语句时会报错
			logger.error(e.getMessage());
		}
		removeById(id);
	}

	@Override
	public void removeById(Serializable id) {
		// 删除已经删除的数据
		TableEntity table = get(id);
		List<ColumnEntity> columnList = table.getColumns();
		// 保存或更新数据
		for (ColumnEntity column : columnList) {
			columnService.delete(column);
		}
		super.delete(table);
	}

	@Override
	public void generateCode(TableEntity table, GeneratorInfo generatorInfo) throws IOException, GenerationException {
		generatorInfo.setTableName(table.getTableName());
		table.setClassName(generatorInfo.getEntityName());
		// generatorInfo.setGeneratorType(table.getTablePKType());
		List<ColumnEntity> oldColumnList = table.getColumns();
		List<AttributeInfo> attributeInfos = new ArrayList<AttributeInfo>();
		for (ColumnEntity column : oldColumnList) {
			attributeInfos.add(new AttributeInfo(column));
		}
		generatorInfo.setType(table.getTableType());
		generatorInfo.setColumns(oldColumnList);
		generatorInfo.setAttributeInfos(attributeInfos);
		// 查询附表
		List<TableEntity> schedules = null;
		if (table.getTableType().equals("2")) {
			// 获得附表
			schedules = findSubTable(table.getTableName());
			for (TableEntity tableEntity : schedules) {
				List<ColumnEntity> oldSubColumnList = tableEntity.getColumns();
				for (ColumnEntity column : oldSubColumnList) {
					if (!StringUtils.isEmpty(column.getForeignTable())
							&& column.getForeignTable().equals(table.getTableName())) {
						tableEntity.setParentField(column.getJavaField());
					}
				}
			}
			generatorInfo.setSchedules(schedules);
			// 生成附表实体

		}
		generatorManagor.process(generatorInfo);
		// 查询附表
		if (table.getTableType().equals("2")) {
			for (TableEntity tableEntity : schedules) {
				generatorInfo.setTableName(tableEntity.getTableName());
				generatorInfo.setFunctionDesc(tableEntity.getRemarks());
				generatorInfo.setEntityName(tableEntity.getClassName());
				generatorInfo.setFunctionName(tableEntity.getRemarks());
				List<ColumnEntity> oldSubColumnList = tableEntity.getColumns();
				List<AttributeInfo> subAttributeInfos = new ArrayList<AttributeInfo>();
				for (ColumnEntity column : oldSubColumnList) {
					if (!StringUtils.isEmpty(column.getForeignTable())
							&& column.getForeignTable().equals(table.getTableName())) {
						column.setJavaType(table.getClassName() + "Entity");
						tableEntity.setParentField(column.getJavaField());
						column.setImportedKey(Boolean.TRUE);
					}
					AttributeInfo attributeInfo = new AttributeInfo(column);
					subAttributeInfos.add(attributeInfo);
				}
				generatorInfo.setType(tableEntity.getTableType());
				generatorInfo.setColumns(oldSubColumnList);
				generatorInfo.setAttributeInfos(subAttributeInfos);
				List<String> generatorKeys = new ArrayList<String>();
				generatorKeys.add("Entity");
				generatorKeys.add("IService");
				generatorKeys.add("ServiceImpl");
				generatorInfo.setGeneratorKeys(generatorKeys);
				generatorManagor.process(generatorInfo);
			}
		}
	}

	@Override
	public void importDatabase(TableEntity table) {
		String tableName = table.getTableName();
		table.setTitle(tableName);
		table.setSyncDatabase(Boolean.TRUE);
		// 保存主表
		super.save(table);
		List<DbColumnInfo> dbColumnInfos = generatorDao.getDbColumnInfo(tableName);
		for (int j = 0; j < dbColumnInfos.size(); j++) {
			ColumnEntity column = new ColumnEntity(dbColumnInfos.get(j));
			// 保存字段列表
			column.setTable(table);
			columnService.save(column);
		}

	}

	public void dropTable(String tableid) {
		TableEntity table = get(tableid);
		try {
			generatorDao.dropTable(table.getTableName());
		} catch (Exception e) {
			// 部分数据库在没有表而执行删表语句时会报错
			logger.error(e.getMessage());
		}
	}

	@Override
	public void syncDatabase(String tableid) throws HibernateException, SQLException {
		TableEntity table = get(tableid);
		String dbType = CodeGenUtils.getDbType();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("dbType", dbType);
		// 生成数据库模版
		String xml = FreeMarkerUtils.initByDefaultTemplate().processToString("/codegen/sql/createTable.ftl", root);
		logger.info(xml);
		generatorDao.createTableByXml(xml);
		table.setSyncDatabase(Boolean.TRUE);
		saveOrUpdate(table);
	}

	@Override
	public void createMenu(TableEntity table, Menu menu) {
		String url = "";
		String permission = "";
		menu.setIsshow((short) 1);
		menu.setUrl(url);
		menu.setPermission(permission);
		menu.setRemarks(table.getRemarks());
		menuService.save(menu);
	}

	@Override
	public List<TableEntity> findSubTable(String tablename) {
		return listByHql(
				"from TableEntity t  WHERE t.id in (SELECT table.id from ColumnEntity c WHERE c.foreignTable=?) and t.tableType='3'",
				tablename);
	}

}
