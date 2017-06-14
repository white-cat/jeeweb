package cn.jeeweb.modules.common.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import cn.jeeweb.modules.common.DataBaseConstant;
import cn.jeeweb.modules.sys.utils.UserUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: Hiberate拦截器
 * @package cn.jeeweb.modules.common.interceptor
 * @description: 实现创建人，创建时间，创建人名称自动注入; 修改人,修改时间,修改人名自动注入;
 * @author: 王存见
 * @date: 2017年5月29日 下午9:56:35
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
public class HiberAspect extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	/**
	 * 保存
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		// 获得用户
		try {
			// 添加数据
			for (int index = 0; index < propertyNames.length; index++) {
				if (DataBaseConstant.DEL_FLAG.equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建时间"属性赋上值 */
					if (state[index] == null) {
						state[index] = "0";
					}
					continue;
				}
				/* 找到名为"添加人"的属性 */
				if (DataBaseConstant.CREATE_BY.equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建时间"属性赋上值 */
					if (state[index] == null) {
						state[index] = UserUtils.getUser();
					}
					continue;
				}
				/* 找到名为"创建时间"的属性 */
				if (DataBaseConstant.CREATE_DATE.equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建时间"属性赋上值 */
					if (state[index] == null) {
						state[index] = new Date();
					}
					continue;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 更新
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] state, Object[] previousState,
			String[] propertyNames, Type[] types) {

		// 添加数据
		for (int index = 0; index < propertyNames.length; index++) {
			/* 找到名为"修改人"的属性 */
			if (DataBaseConstant.UPDATE_BY.equals(propertyNames[index])) {
				/* 使用拦截器将对象的"创建时间"属性赋上值 */
				if (state[index] == null) {
					state[index] = UserUtils.getUser();
				}
				continue;
			}
			/* 找到名为"修改时间"的属性 */
			if (DataBaseConstant.UPDATE_DATE.equals(propertyNames[index])) {
				/* 使用拦截器将对象的"修改时间"属性赋上值 */
				if (state[index] == null) {
					state[index] = new Date();
				}
				continue;
			}
		}
		return true;
	}
}
