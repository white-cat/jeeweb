package cn.jeeweb.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sys.entity.DictEntity;
import cn.jeeweb.modules.sys.entity.DictGroup;
import cn.jeeweb.modules.sys.service.IDictGroupService;

@Controller
@RequestMapping("${admin.url.prefix}/sys/dict")
@RequiresPathPermission("sys:user")
public class DictController extends BaseCRUDController<DictEntity, String> {
	@Autowired
	private IDictGroupService dictGroupService;

	@Override
	public void preList(Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		DictGroup group = dictGroupService.get(gid);
		model.addAttribute("group", group);
	}

	@Override
	public void preAjaxList(Queryable queryable, DetachedCriteria detachedCriteria, HttpServletRequest request,
			HttpServletResponse response) {
		String gid = request.getParameter("gid");
		queryable.addCondition("gid", gid);
		super.preAjaxList(queryable, detachedCriteria, request, response);
	}

	@Override
	public void preEdit(DictEntity entity, Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		model.addAttribute("gid", gid);
	}
}
