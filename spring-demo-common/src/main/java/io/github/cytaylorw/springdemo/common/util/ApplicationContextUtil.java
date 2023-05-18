package io.github.cytaylorw.springdemo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Boot ApplicationContext Utility
 * 
 * @author Taylor Wong
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	/**
	 * Get {@link ApplicationContext}
	 * 
	 * @return ApplicationContext instance
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * Get the bean instance with {@link ApplicationContext#getBean(Class)}
	 * 
	 * @param <T>   the return type
	 * @param clazz class of the return type
	 * @return the bean instance
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * Get the bean instance with {@link ApplicationContext#getBean(String, Class)}
	 * 
	 * @param <T>      the return type
	 * @param beanName the bean name
	 * @param clazz    clazz class of the return type
	 * @return the bean instance
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getContext().getBean(beanName, clazz);
	}
}
