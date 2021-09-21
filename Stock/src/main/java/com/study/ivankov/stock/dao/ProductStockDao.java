package com.study.ivankov.stock.dao;

import java.util.List;

import com.study.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface ProductStockDao {

	List<ProductStock> findAll();

	ProductStock getStock(int productId);

	void deleteAll();

	void insert(ProductStock bean);
	
	void update(ProductStock bean);
}
