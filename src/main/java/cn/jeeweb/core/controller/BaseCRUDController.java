package cn.jeeweb.core.controller;


import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import cn.jeeweb.core.entity.AbstractEntity;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.service.ICommonService;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseCRUDController<Entity extends AbstractEntity<ID>, ID extends Serializable>
		extends BaseBeanController<Entity> {

	protected ICommonService<Entity> commonService;

	/**
	 * 设置基础service
	 *
	 * @param baseService
	 */
	@Autowired
	public void setCommonService(ICommonService<Entity> commonService) {
		this.commonService = commonService;
	}

	/*@ModelAttribute
	public Entity get(@RequestParam(required = false) ID id) {
		if (!ObjectUtils.isNullOrEmpty(id)) {
			return commonService.get(id);
		} else {
			return newModel();
		}
	}*/

	/**
	 * list 运行之前
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	public void preList(Model model, HttpServletRequest request, HttpServletResponse response) {

	}

	@RequiresMethodPermissions("list")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		preList(model, request, response);
		return display("list");
	}

	/**
	 * 在异步获取数据之前
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	public void preAjaxList(Queryable queryable, DetachedCriteria detachedCriteria, HttpServletRequest request,
			HttpServletResponse response) {

	}

	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxList", method = RequestMethod.GET)
	private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		preAjaxList(queryable, detachedCriteria, request, response);
		propertyPreFilterable.addQueryProperty("id");
	 	SerializeFilter filter=	propertyPreFilterable.constructFilter(entityClass);
		PageJson<Entity> pagejson = new PageJson<Entity>(commonService.list(queryable, detachedCriteria));
		String content=JSON.toJSONString(pagejson,filter);
		StringUtils.printJson(response, content);
	}

	/**
	 * list 运行之前
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	public void preEdit(Entity entity, Model model, HttpServletRequest request, HttpServletResponse response) {

	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) ID id,Model model, HttpServletRequest request, HttpServletResponse response) {
		Entity entity=null;
		if (!ObjectUtils.isNullOrEmpty(id)) {
			entity=commonService.get(id);
		} else {
			entity=newModel();
		}
		preEdit(entity, model, request, response);
		model.addAttribute("data", entity);
		return display("edit");
	}

	/**
	 * 保存数据之前
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 */
	public void preSave(Entity entity, HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doSave(Entity entity, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("保存成功");
		try {
			preSave(entity, request, response);
			if (ObjectUtils.isNullOrEmpty(entity.getId())) {
				commonService.save(entity);
			} else {
				// FORM NULL不更新
				Entity oldEntity = commonService.get(entity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(entity, oldEntity);
				commonService.update(oldEntity);
			}
			afterSave(entity, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("保存失败");
		}
		return ajaxJson;
	}

	/**
	 * 保存数据之后
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 */
	public void afterSave(Entity entity, HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson batchDelete(@RequestParam("ids") ID[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");
		try {
			List<ID> idList = java.util.Arrays.asList(ids);
			commonService.batchDeleteById(idList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("删除失败");
		}
		return ajaxJson;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(@RequestParam("id") ID id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");
		try {
			commonService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("删除失败");
		}
		return ajaxJson;
	}

}
