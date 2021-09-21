package com.study.ivankov.stock.controller;

import com.study.ivankov.stock.validation.ProductReservationBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.ivankov.common.annotation.CountEnhancer;
import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;
import com.study.ivankov.stock.service.ProductReservationService;

/**
 * @author Ivankov_A
 *
 */
@RestController
@RequestMapping("/stock")
public class StockRestController {

	private static final Logger LOG = LoggerFactory.getLogger(StockRestController.class);

	@Autowired
	private ProductReservationService reservationService;
	@Autowired
	private ProductReservationBeanValidator prValidator;

	@RequestMapping(path = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<ProductStock> getStock(@PathVariable int productId) {
		return new ResponseEntity<>(reservationService.getStock(productId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductStock> save(@RequestBody ProductStock bean) {
		return new ResponseEntity<>(reservationService.save(bean), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/reserve", method = RequestMethod.POST)
	public ResponseEntity<ProductReservation> getReservation(@CountEnhancer(99) @RequestBody ProductReservation reservation, BindingResult result) {
		LOG.info("getReservation {} ", reservation);
		prValidator.validate(reservation, result);
		if (result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			reservationService.reserveProduct(reservation);
		}
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
}
