package com.study.ivankov.stock.dao;

import java.util.List;

import com.study.ivankov.common.domain.ProductReservation;

/**
 * @author Ivankov_A
 *
 */
public interface ProductReservationDao {

	List<ProductReservation> findAll();

	void insert(ProductReservation productReservation);

}
