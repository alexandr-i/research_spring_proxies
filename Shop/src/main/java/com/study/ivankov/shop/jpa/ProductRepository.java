package com.study.ivankov.shop.jpa;

import java.util.List;

import com.study.ivankov.shop.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Ivankov_A
 *
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	List<Product> findByName(@Param("name") String name);

}
