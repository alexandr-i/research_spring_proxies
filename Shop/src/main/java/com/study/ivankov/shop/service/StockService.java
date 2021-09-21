package com.study.ivankov.shop.service;

import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface StockService {

	ProductStock getStock(Integer productId);

	void reserveFromStock(ProductReservation reservation);
}
