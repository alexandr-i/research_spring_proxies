package com.study.ivankov.shop.fwk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.study.ivankov.shop.converter.PriceCorrectionAnnotationCorrector;
import com.study.ivankov.shop.domain.Product;
import com.study.ivankov.shop.security.ShopSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.study.ivankov.common.annotation.PriceCorrection;

/**
 * @author Ivankov_A
 *
 */
@Component
public class PriceCorrectionAnnotationBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ApplicationContext appContext;

	private final Logger LOG = LoggerFactory.getLogger(PriceCorrectionAnnotationBeanPostProcessor.class);

	private final Map<String, Class<?>> cache = new HashMap<>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		LOG.trace("Trying to find PriceCorrection annotation in class: {}, string: {}", bean.getClass(), bean);
		Class<?> clz = bean.getClass();
		for (Method next : clz.getDeclaredMethods()) {
			if (next.isAnnotationPresent(PriceCorrection.class)) {
				cache.put(beanName, clz);
				break;
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		final Class<?> clz = cache.get(beanName);
		if (clz != null) {
			try {
				LOG.info("Creating PriceCorrection interceptor for: {}", beanName);
				bean = Proxy.newProxyInstance(clz.getClassLoader(), clz.getInterfaces(), new PriceCorrectionAnnotationInvocationHandler(clz, bean));
			} catch (Exception e) {
				LOG.error("Error while making proxy for class: " + clz + ". Message: " + bean, e);
				throw new FatalBeanException(e.getMessage());
			}
		}
		return bean;
	}

	private class PriceCorrectionAnnotationInvocationHandler implements InvocationHandler {

		private final Object originalObj;
		private final Class<?> originalClass;

		public PriceCorrectionAnnotationInvocationHandler(Class<?> originalClass, Object originalObj) {
			this.originalClass = originalClass;
			this.originalObj = originalObj;
		}

		@Override
		public Object invoke(Object bean, Method method, Object[] args) throws Throwable {
			Object result = method.invoke(originalObj, args);
			PriceCorrection annotation = getPriceCorrectionAnnotation(method);

			if (annotation != null) {
				if (result instanceof Product) {
					Product product = (Product) result;
					String role = ShopSecurityUtils.getCurrentUserRole();
					String username = ShopSecurityUtils.getCurrentUserName();
					LOG.debug("PriceCorrection beginning.... User: {}, Cost before: {}", username, product.getCost());
					appContext.getBean(PriceCorrectionAnnotationCorrector.class).correctPrice(product, role);
					LOG.debug("PriceCorrection ended.... User: {}, Cost after: {}", username, product.getCost());
				} else {
					throw new IllegalArgumentException("@PriceCorrection annotation can be used only for " + Product.class.getName() + " class. Current: " + bean.getClass().getName());
				}
			}
			return result;
		}

		private PriceCorrection getPriceCorrectionAnnotation(Method method) throws NoSuchMethodException {
			return originalClass.getMethod(method.getName(), method.getParameterTypes()).getAnnotation(PriceCorrection.class);
		}
	}
}
