package com.study.ivankov.stock.service;

import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface ProductReservationService {

	ProductStock getStock(int productId);

	void reserveProduct(ProductReservation reservation);

	ProductStock save(ProductStock bean);
}
