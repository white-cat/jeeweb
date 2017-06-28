package cn.jeeweb.core.common.dao.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.Order;
import org.springframework.util.StringUtils;

/**
 * 排序
 * 
 * @author 王存见
 *
 */
@SuppressWarnings("serial")
public class OrderHelper implements Iterable<Order>, Serializable {

	public static final Direction DEFAULT_DIRECTION = Direction.ASC;

	private final List<Order> orders;

	public static OrderHelper create(String propertyName) {
		return new OrderHelper(propertyName);
	}

	public static OrderHelper create(String propertyName, Direction direction) {
		return new OrderHelper(direction, propertyName);
	}

	public OrderHelper() {
		orders = new ArrayList<Order>();
	}

	public OrderHelper(List<Order> orders) {

		if (null == orders || orders.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
		}

		this.orders = orders;
	}

	public OrderHelper(String... properties) {
		this(DEFAULT_DIRECTION, properties);
	}

	public OrderHelper(Direction direction, String... properties) {
		this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));
	}

	public OrderHelper(Direction direction, List<String> properties) {

		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.orders = new ArrayList<Order>(properties.size());

		for (String property : properties) {
			if (direction == Direction.DESC) {
				this.orders.add(Order.desc(property));
			} else {
				this.orders.add(Order.asc(property));
			}
		}
	}

	public OrderHelper desc(String property) {
		this.orders.add(Order.desc(property));
		return this;
	}

	public OrderHelper asc(String property) {
		this.orders.add(Order.desc(property));
		return this;
	}

	public Iterator<Order> iterator() {
		return this.orders.iterator();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OrderHelper)) {
			return false;
		}

		OrderHelper that = (OrderHelper) obj;

		return this.orders.equals(that.orders);
	}

	@Override
	public int hashCode() {

		int result = 17;
		result = 31 * result + orders.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return StringUtils.collectionToCommaDelimitedString(orders);
	}

	public static enum Direction {

		ASC, DESC;

		public static Direction fromString(String value) {

			try {
				return Direction.valueOf(value.toUpperCase(Locale.US));
			} catch (Exception e) {
				throw new IllegalArgumentException(String.format(
						"Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
						value), e);
			}
		}

		public static Direction fromStringOrNull(String value) {

			try {
				return fromString(value);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	}

}
