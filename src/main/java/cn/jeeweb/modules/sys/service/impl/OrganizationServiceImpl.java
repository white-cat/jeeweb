package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.TreeCommonServiceImpl;
import cn.jeeweb.modules.sys.entity.Organization;
import cn.jeeweb.modules.sys.service.IOrganizationService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends TreeCommonServiceImpl<Organization, String>
		implements IOrganizationService {

	@Override
	public List<Organization> findListByUserId(String userid) {
		return listByHql(
				"from Organization o where  o.id in (SELECT uo.organizationId from UserOrganization uo WHERE uo.userId = ?)",
				userid);
	}

}
