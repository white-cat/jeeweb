package cn.jeeweb.core.common.hibernate;

import org.hibernate.criterion.DetachedCriteria;

public class JeeWebCriteria extends DetachedCriteria {

	private static final long serialVersionUID = 2395424771338393797L;

	public JeeWebCriteria(String entityName) {
		super(entityName);
	}

	public static JeeWebCriteria initJeeWebCriteria(Class<?> clazz) {
		return new JeeWebCriteria(clazz.getName());
	}
}
