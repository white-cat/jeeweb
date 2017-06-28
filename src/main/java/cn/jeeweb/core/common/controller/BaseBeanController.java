package cn.jeeweb.core.common.controller;

import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import cn.jeeweb.core.utils.ReflectionUtils;
import cn.jeeweb.core.utils.StringUtils;

import java.io.Serializable;

public abstract class BaseBeanController<Entity extends Serializable> extends BaseController {

	/**
	 * 实体类型
	 */
	protected final Class<Entity> entityClass;

	protected BaseBeanController() {
		this.entityClass = ReflectionUtils.getSuperGenericType(getClass());
	}

	/**
	 * 设置通用数据
	 *
	 * @param model
	 */
	protected void setCommonData(Model model) {
	}

	protected Entity newModel() {
		try {
			return entityClass.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
		}
	}

	/**
	 * 共享的验证规则 验证失败返回true
	 *
	 * @param m
	 * @param result
	 * @return
	 */
	protected boolean hasError(Entity entity, BindingResult result) {
		Assert.notNull(entity);
		return result.hasErrors();
	}

	/**
	 * @param backURL
	 *            null 将重定向到默认getViewPrefix()
	 * @return
	 */
	protected String redirectToUrl(String backURL) {
		if (StringUtils.isEmpty(backURL)) {
			backURL = getViewPrefix();
		}
		if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
			backURL = "/" + backURL;
		}
		return "redirect:" + backURL;
	}

}
