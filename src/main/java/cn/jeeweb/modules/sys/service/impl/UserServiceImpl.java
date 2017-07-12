package cn.jeeweb.modules.sys.service.impl;

import java.io.Serializable;
import java.util.List;
import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.service.IUserOrganizationService;
import cn.jeeweb.modules.sys.service.IUserRoleService;
import cn.jeeweb.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<User> implements IUserService {
	@Autowired
	PasswordService passwordService;
	@Autowired
	private IUserOrganizationService userOrganizationService;
	@Autowired
	private IUserRoleService userRoleService;

	@Override
	public void changePassword(String userid, String newPassword) {
		User user = get(userid);
		if (user != null) {
			user.setPassword(newPassword);
			passwordService.encryptPassword(user);
		}
		saveOrUpdate(user);
	}

	@Override
	public User findByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		return get("username", username);
	}

	@Override
	public User findByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		return get("email", email);
	}

	@Override
	public User findByPhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		return get("phone", phone);
	}

	@Override
	public void save(User user) {
		passwordService.encryptPassword(user);
		super.save(user);
	}

	@Override
	public void deleteById(Serializable id) {
		super.deleteById(id);
		// 删除角色关联
		userRoleService.batchDeleteByProperty("userId", id);
		// 删除部门关联
		userOrganizationService.batchDeleteByProperty("userId", id);
	}

	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object id : ids) {
			this.deleteById((Serializable) id);
		}
	}
}
