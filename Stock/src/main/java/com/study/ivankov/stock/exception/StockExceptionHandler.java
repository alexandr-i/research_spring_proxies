package com.study.ivankov.stock.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.study.ivankov.common.exception.ExceptionRestResponse;

/**
 * @author Ivankov_A
 *
 */
@ControllerAdvice
public class StockExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(StockExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ExceptionRestResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		LOG.warn("Exception: {}", e.getMessage());
		return new ExceptionRestResponse(e.getMessage());
	}
}
