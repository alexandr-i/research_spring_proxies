package com.study.ivankov.stock.app;

import java.util.List;

import com.study.ivankov.stock.dao.ProductReservationDao;
import com.study.ivankov.stock.dao.ProductStockDao;
import com.study.ivankov.stock.fwk.CountEnhancerAnnotationMethodResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
@SpringBootApplication
@ComponentScan("com.study.ivankov.stock")
public class AppStockConfig extends WebMvcConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AppStockConfig.class);

	@Bean
	@DependsOn("liquibase")
	public Void demo(ProductStockDao stockDao, ProductReservationDao reservDao, AppStockProperties props) {
		LOG.info("INFO: {}, DEBUG: {}, TRACE: {}", LOG.isInfoEnabled(), LOG.isDebugEnabled(), LOG.isTraceEnabled());
		LOG.info("AppShopProperties found:");
		LOG.info("-------------------------------");
		LOG.info("isProduction: {}", props.isProduction());
		LOG.info("isProduction: {}", props.isProduction());

		LOG.info("ProductStock found with findAll():");
		LOG.info("-------------------------------");
		for (ProductStock next : stockDao.findAll()) {
			LOG.info(next.toString());
		}
		LOG.info("-------------------------");

		LOG.info("ProductReservation found with findAll():");
		LOG.info("-------------------------------");
		for (ProductReservation next : reservDao.findAll()) {
			LOG.info(next.toString());
		}
		LOG.info("-------------------------");

		return null;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new CountEnhancerAnnotationMethodResolver());
	}
}
