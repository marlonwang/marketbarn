package com.springmvc.marketbarn.spring;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextHolder implements ApplicationContextAware,
		DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringContextHolder.class);

	/**
	 * 
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {

		assertContextInjected();

		return applicationContext;

	}

	/**
	 * 
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {

		assertContextInjected();

		return (T) applicationContext.getBean(name);

	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {

		assertContextInjected();

		return applicationContext.getBean(requiredType);

	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */

	public static void clearHolder() {

		LOGGER.debug("clear SpringContextHolder ApplicationContext:"
				+ applicationContext);

		applicationContext = null;

	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {

		LOGGER.debug("Inject ApplicationContext to SpringContextHolder:{}",applicationContext);

		if (SpringContextHolder.applicationContext != null) {

			LOGGER.warn("SpringContextHolder ApplicationContext is overwrited, old ApplicationContext:"
					+ SpringContextHolder.applicationContext);

		}
		SpringContextHolder.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {

		SpringContextHolder.clearHolder();

	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {

		Validate.validState(applicationContext != null,
				"applicaitonContext is null, please define applicationContext.xml.");
	}
}
