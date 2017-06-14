package cn.jeeweb.core.query.utils;

import java.lang.reflect.Method;
import java.util.Locale;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import cn.jeeweb.core.query.data.Condition;
import cn.jeeweb.core.query.data.Condition.Filter;
import cn.jeeweb.core.query.data.Condition.Operator;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.utils.ObjectUtils;

/**
 * http://blog.csdn.net/cuiran/article/details/6324083
 * 
 * @author Administrator
 *
 */
public class CriteriaParseUtils {

	public static void parseCondition(DetachedCriteria criteria, Queryable queryable) {
		Condition condition = queryable.getCondition();
		if (condition != null) {
			for (Filter filter : condition) {
				Object value = filter.getValue();
				if (!ObjectUtils.isNullOrEmpty(value)) {
					// 可以使用反射快速绑定
					Operator operator = filter.getOperator();
					String property = filter.getProperty();

					Criterion criterion = null;
					if (operator == Operator.custom) {
						continue;
					} else if (operator == Operator.isNull) {
						criterion = Restrictions.isNull(property);
					} else if (operator == Operator.isNotNull) {
						criterion = Restrictions.isNotNull(property);
					} else if (operator == Operator.between) {
						Object[] between = (Object[]) value;
						if (between.length == 2) {
							criterion = Restrictions.between(property, between[0], between[1]);
						}
					} else if (operator.name().toUpperCase(Locale.US).contains("LIKE")) {
						value = parseLike(filter);
						if (operator.name().contains("NOT")) {
							criterion = Restrictions.not(Restrictions.like(filter.getProperty(), value));
						} else {
							criterion = Restrictions.like(filter.getProperty(), value);
						}
					} else {
						criterion = getCriterion(filter);
					}
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
		}

	}

	public static Object parseLike(Filter filter) {
		String operatorStr = filter.getOperator().name().toUpperCase(Locale.US);
		Object value = filter.getValue();
		if (operatorStr.contains("PREFIX")) {
			value = "%" + value;
		} else if (operatorStr.contains("SUFFIX")) {
			value = value + "%";
		} else {
			value = "%" + value + "%";
		}
		return value;
	}

	/**
	 * 
	 * 
	 * @param filter
	 * @return
	 */
	public static Criterion getCriterion(Filter filter) {
		try {
			Class<?> threadClazz = Restrictions.class;
			Method method = threadClazz.getMethod(filter.getOperator().name(), String.class, Object.class);
			SimpleExpression simpleExpression = (SimpleExpression) method.invoke(null, filter.getProperty(),
					filter.getValue());
			return simpleExpression;
		} catch (Exception e) {

		}
		return null;
	}

	public static void parseSort(DetachedCriteria target, Queryable queryable) {
		cn.jeeweb.core.query.data.Sort sort = queryable.getSort();
		if (sort != null) {
			for (cn.jeeweb.core.query.data.Sort.Order order : sort) {
				if (order.getDirection() == cn.jeeweb.core.query.data.Sort.Direction.DESC) {
					target.addOrder(Order.desc(order.getProperty()));
				} else if (order.getDirection() == cn.jeeweb.core.query.data.Sort.Direction.ASC) {
					target.addOrder(Order.asc(order.getProperty()));
				}
			}
		}
	}
}
