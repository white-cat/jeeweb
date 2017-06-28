package cn.jeeweb.core.common.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jeeweb.core.common.dao.IBaseDao;

public class BaseDaoImpl implements IBaseDao {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public final Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}