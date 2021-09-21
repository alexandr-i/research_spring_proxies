package com.study.ivankov.shop.exception;

import com.study.ivankov.common.exception.CommonWfRuntimeException;

/**
 * @author Ivankov_A
 *
 */
public class ShopRuntimeException extends CommonWfRuntimeException {

	private static final long serialVersionUID = -2456816826768950899L;

	public ShopRuntimeException(String message) {
		super(message);
	}

}
