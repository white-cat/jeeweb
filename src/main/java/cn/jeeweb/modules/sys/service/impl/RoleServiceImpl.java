package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.Role;
import cn.jeeweb.modules.sys.service.IRoleService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends CommonServiceImpl<Role> implements IRoleService {

	@Override
	public List<Role> findListByUserId(String userid) {
		return listByHql("from Role r  WHERE r.id in (SELECT ur.roleId from UserRole ur WHERE ur.userId=?)", userid);
	}

}
