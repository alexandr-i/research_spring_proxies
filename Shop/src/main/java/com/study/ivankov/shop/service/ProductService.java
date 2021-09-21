package com.study.ivankov.shop.service;

import java.util.List;

import com.study.ivankov.shop.domain.Product;

/**
 * @author Ivankov_A
 *
 */
public interface ProductService {

	List<Product> findAll();

	Product getOne(int id);

	List<Product> searchByName(String name);

	Product save(Product bean);

	void saveAll(List<Product> beans);

	void delete(int id);

}
