package cn.jeeweb.modules.sys.tags;

import cn.jeeweb.core.utils.JeewebPropertiesUtil;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: SysFunctions.java
 * @package cn.jeeweb.modules.sys.tags
 * @description: 提供一些公用的函数
 * @author: 王存见
 * @date: 2017年3月28日 下午10:04:07
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
public class SysFunctions {
	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String getAdminUrlPrefix() {
		String adminUrlPrefix = JeewebPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}
	
	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String get() {
		String adminUrlPrefix = JeewebPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}
}
