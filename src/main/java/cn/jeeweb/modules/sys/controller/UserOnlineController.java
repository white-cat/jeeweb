package cn.jeeweb.modules.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jeeweb.core.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sys.entity.UserOnline;
import cn.jeeweb.modules.sys.security.shiro.session.mgt.OnlineSession;
import cn.jeeweb.modules.sys.security.shiro.session.mgt.eis.OnlineSessionDAO;
import cn.jeeweb.modules.sys.service.IUserOnlineService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-1-28 下午4:29
 * <p>
 * Version: 1.0
 */
@Controller
@RequestMapping(value = "${admin.url.prefix}/sys/online")
@RequiresPathPermission("sys:online")
public class UserOnlineController extends BaseCRUDController<UserOnline, String> {

	@Autowired
	private IUserOnlineService userOnlineService;
	@Autowired
	private OnlineSessionDAO onlineSessionDAO;

	public UserOnlineController() {
	}

	@RequestMapping("/forceLogout")
	@ResponseBody
	public AjaxJson forceLogout(@RequestParam(value = "ids") String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setMsg("强制退出成功");
		for (String id : ids) {
			UserOnline online = userOnlineService.get(id);
			if (online == null) {
				continue;
			}
			OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(online.getId());
			if (onlineSession == null) {
				continue;
			}
			onlineSession.setStatus(OnlineSession.OnlineStatus.force_logout);
			online.setStatus(OnlineSession.OnlineStatus.force_logout);
			userOnlineService.update(online);
		}
		return ajaxJson;
	}

}
