package com.study.ivankov.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;
import com.study.ivankov.shop.domain.Product;
import com.study.ivankov.shop.exception.ProductNotFoundException;
import com.study.ivankov.shop.jpa.ProductRepository;
import com.study.ivankov.shop.service.StockService;

/**
 * @author Ivankov_A
 *
 */
@Service
public class StockServiceRestConsumer implements StockService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Value("${shop.rest.stockUrl}")
	private String stockRestUrl;

	@Override
	public ProductStock getStock(Integer productId) {
		checkIfProductExist(productId);
		ResponseEntity<ProductStock> result = restTemplate.getForEntity(stockRestUrl + "/stock/" + productId, ProductStock.class);
		return result.getBody();
	}

	@Override
	public void reserveFromStock(ProductReservation ps) {
		checkIfProductExist(ps.getProductId());
		HttpEntity<ProductReservation> data = new HttpEntity<>(ps);
		restTemplate.postForEntity(stockRestUrl + "/reserve", data, String.class);
	}

	private void checkIfProductExist(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			throw new ProductNotFoundException("No product found for id: " + id);
		}
	}
}
