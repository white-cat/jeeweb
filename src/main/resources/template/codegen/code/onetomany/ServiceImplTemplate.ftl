package ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.entity.${entityName?cap_first};
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.service.I${entityName?cap_first}Service;
<#list schedules as schedule>
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.entity.${schedule.className?cap_first};
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.service.I${schedule.className?cap_first}Service;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

/**   
 * @Title: ${functionName}
 * @Description: ${functionDesc}
 * @author ${functionAuthor}
 * @date ${time}
 * @version V1.0   
 *
 */
@Transactional
@Service("${entityName?uncap_first}Service")
public class ${entityName?cap_first}ServiceImpl  extends CommonServiceImpl<${entityName?cap_first}> implements  I${entityName?cap_first}Service {
	<#list schedules as schedule>
	@Autowired
	private I${schedule.className?cap_first}Service ${schedule.className?uncap_first}Service;
	</#list>
	
	@Override
	public void save(${entityName?cap_first} ${entityName?uncap_first}) {
		// 保存主表
		super.save(${entityName?uncap_first});
		<#list schedules as schedule>
		// 保存${schedule.remarks!}
		String ${schedule.className?uncap_first}ListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("${schedule.className?uncap_first}ListJson"));
		List<${schedule.className?cap_first}> ${schedule.className?uncap_first}List = JSONObject.parseArray(${schedule.className?uncap_first}ListJson, ${schedule.className?cap_first}.class);
		for (${schedule.className?cap_first} ${schedule.className?uncap_first} : ${schedule.className?uncap_first}List) {
			// 保存字段列表
			${schedule.className?uncap_first}.set${schedule.parentField?cap_first}(${entityName?uncap_first});
			${schedule.className?uncap_first}Service.save(${schedule.className?uncap_first});
		}
		</#list>
	}
	
	@Override
	public void update(${entityName?cap_first} ${entityName?uncap_first}) {
		try {
		    <#list schedules as schedule>
			// 获得以前的数据
			List<${schedule.className?cap_first}> old${schedule.className?cap_first}List = ${schedule.className?uncap_first}Service.list("${schedule.parentField?uncap_first}", ${entityName?uncap_first});
			// 字段
			String ${schedule.className?uncap_first}ListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("${schedule.className?uncap_first}ListJson"));
			List<${schedule.className?cap_first}> ${schedule.className?uncap_first}List = JSONObject.parseArray(${schedule.className?uncap_first}ListJson,
					${schedule.className?cap_first}.class);
			</#list>
			// 更新主表
			super.update(${entityName?uncap_first});
			<#list schedules as schedule>
			${schedule.className?uncap_first}List = JSONObject.parseArray(${schedule.className?uncap_first}ListJson, ${schedule.className?cap_first}.class);
			List<String> news${schedule.className?cap_first}IdList = new ArrayList<String>();
			// 保存或更新数据
			for (${schedule.className?cap_first} ${schedule.className?uncap_first} : ${schedule.className?uncap_first}List) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(${schedule.className?uncap_first}.getId())) {
					// 保存字段列表
					${schedule.className?uncap_first}.set${schedule.parentField?cap_first}(${entityName?uncap_first});
					${schedule.className?uncap_first}Service.save(${schedule.className?uncap_first});
				} else {
					${schedule.className?cap_first} oldTicket = ${schedule.className?uncap_first}Service.get(${schedule.className?uncap_first}.getId());
					MyBeanUtils.copyBeanNotNull2Bean(${schedule.className?uncap_first}, oldTicket);
					${schedule.className?uncap_first}Service.update(oldTicket);
				}
				news${schedule.className?cap_first}IdList.add(${schedule.className?uncap_first}.getId());
			}

			// 删除老数据
			for (${schedule.className?cap_first} ${schedule.className?uncap_first} : old${schedule.className?cap_first}List) {
				String ${schedule.className?uncap_first}Id = ${schedule.className?uncap_first}.getId();
				if (!news${schedule.className?cap_first}IdList.contains(${schedule.className?uncap_first}Id)) {
					${schedule.className?uncap_first}Service.deleteById(${schedule.className?uncap_first}Id);
				}
			}
		    </#list>
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
}