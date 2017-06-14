package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.entity.Organization;
import cn.jeeweb.modules.sys.entity.Role;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.entity.UserOrganization;
import cn.jeeweb.modules.sys.entity.UserRole;
import cn.jeeweb.modules.sys.service.IOrganizationService;
import cn.jeeweb.modules.sys.service.IRoleService;
import cn.jeeweb.modules.sys.service.IUserOrganizationService;
import cn.jeeweb.modules.sys.service.IUserRoleService;
import cn.jeeweb.modules.sys.service.IUserService;
import cn.jeeweb.modules.sys.utils.UserUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: UserController.java
 * @package cn.jeeweb.modules.sys.controller
 * @description: 用户操作控制器
 * @author: 王存见
 * @date: 2017年5月25日 上午9:52:20
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/user")
@RequiresPathPermission("sys:user")
public class UserController extends BaseCRUDController<User, String> {
	@Autowired
	private IUserService userService;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IUserOrganizationService userOrganizationService;

	public UserController() {
		setCommonService(userService);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		preEdit(user, model, request, response);
		model.addAttribute("data", user);
		return display("create");
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String changePassword(@RequestParam(required = false) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.get(id);
		model.addAttribute("data", user);
		return display("changePassword");
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson changePassword(User user, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("密码修改成功");
		try {
			String password = request.getParameter("password");
			userService.changePassword(user.getId(), password);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("密码修改失败");
		}
		return ajaxJson;
	}

	@RequestMapping(value = "avatar", method = RequestMethod.GET)
	public String avatar(@RequestParam(required = false) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.get(id);
		model.addAttribute("data", user);
		return display("avatar");
	}

	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson avatar(User user, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("头像修改成功");
		try {
			User oldUser = commonService.get(user.getId());
			MyBeanUtils.copyBeanNotNull2Bean(user, oldUser);
			commonService.update(oldUser);
			String currentUserId = UserUtils.getUser().getId();
			if (currentUserId.equals(user.getId())) {
				UserUtils.clearCache();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("头像修改失败");
		}
		return ajaxJson;
	}

	@Override
	public void preEdit(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 查询所有的角色List
		List<Role> allRoles = roleService.list();
		request.setAttribute("allRoles", allRoles);
		if (!StringUtils.isEmpty(user.getId())) {
			// 查找关联角色
			List<Role> userRoles = roleService.findListByUserId(user.getId());
			request.setAttribute("roleIdList", userRoles);
			List<Organization> organizations = organizationService.findListByUserId(user.getId());
			String organizationIds = "";
			String organizationNames = "";
			for (Organization organization : organizations) {
				if (!StringUtils.isEmpty(organizationIds)) {
					organizationIds += ",";
					organizationNames += ",";
				}
				String organizationId = organization.getId();
				organizationIds += organizationId;
				organizationNames += organization.getName();

			}
			request.setAttribute("organizationIds", organizationIds);
			request.setAttribute("organizationNames", organizationNames);
		}
	}

	@Override
	public void preSave(User entity, HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public void afterSave(User entity, HttpServletRequest request, HttpServletResponse response) {
		// 删除角色关联
		String[] roleIdList = request.getParameterValues("roleIdList");
		if (roleIdList != null && roleIdList.length > 0) {
			userRoleService.batchDeleteByProperty("userId", entity.getId());
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			for (String roleid : roleIdList) {
				UserRole userRole = new UserRole();
				userRole.setUserId(entity.getId());
				userRole.setRoleId(roleid);
				userRoleList.add(userRole);
			}
			userRoleService.batchSave(userRoleList);
		}

		// 删除部门关联
		String organizationIdListStr = request.getParameter("organizationIds");
		String[] organizationIdList = organizationIdListStr.split(",");
		if (organizationIdList != null && organizationIdList.length > 0) {
			userOrganizationService.batchDeleteByProperty("userId", entity.getId());
			List<UserOrganization> userOrganizationList = new ArrayList<UserOrganization>();
			for (String organizationId : organizationIdList) {
				UserOrganization userOrganization = new UserOrganization();
				userOrganization.setUserId(entity.getId());
				userOrganization.setOrganizationId(organizationId);
				userOrganizationList.add(userOrganization);
			}
			userOrganizationService.batchSave(userOrganizationList);
		}
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String info(@RequestParam(required = false) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userService.get(id);
		model.addAttribute("data", user);
		return display("info");
	}
}