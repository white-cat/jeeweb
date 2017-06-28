package cn.jeeweb.core.common.dao;

import org.hibernate.Session;

public interface IBaseDao {
	/**
	 * 公用此方法，当所有查询都不满足的时候，可以调用自己实现。
	 * 
	 * @return
	 */
	Session getSession();
}