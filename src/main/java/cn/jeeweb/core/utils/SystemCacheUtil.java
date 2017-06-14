package cn.jeeweb.core.utils;

import cn.jeeweb.modules.sys.entity.User;

/**
 * 系统缓存加载
 * 
 * @author 王存见
 * @version 2017-01-19
 */
public class SystemCacheUtil extends EhcacheUtil {
	private static SystemCacheUtil cacheUtil = null;
	private static final String cacheName = "syscommoncache";

	public static SystemCacheUtil create() {
		if (cacheUtil == null) {
			cacheUtil = new SystemCacheUtil();
			cacheUtil.load(cacheName);
		}
		return cacheUtil;
	}

	public static void main(String[] args) {
		String key = "key";
		User user = new User();
		user.setId("sdfsdfsdfs");
		user.setUsername("王存见");
		SystemCacheUtil.create().set(key, user);
		User testUser = (User) SystemCacheUtil.create().get(key);
		System.out.println(testUser.getUsername() + "---------------" + testUser.getId());
	}

}