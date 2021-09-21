package com.study.ivankov.stock.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ivankov.common.domain.ProductReservation;
import com.study.ivankov.common.domain.ProductStock;
import com.study.ivankov.stock.dao.ProductReservationDao;
import com.study.ivankov.stock.dao.ProductStockDao;
import com.study.ivankov.stock.exception.ProductStockNotFoundException;
import com.study.ivankov.stock.service.ProductReservationService;

/**
 * @author Ivankov_A
 *
 */
@Service
public class ProductReservationServiceProdImpl implements ProductReservationService {

	@Autowired
	private ProductReservationDao reservationDao;
	@Autowired
	private ProductStockDao stockDao;

	@Override
	@Transactional
	public void reserveProduct(ProductReservation reservation) {
		ProductStock productStock = stockDao.getStock(reservation.getProductId());
		if (productStock.getQuantity() < reservation.getQuantity()) {
			throw new ProductStockNotFoundException("No such good yet. Id:" + reservation.getProductId());
		}
		reservation.setReservationDate(new Timestamp(System.currentTimeMillis()));
		reservation.setUserName("TEST_USER");
		reservationDao.insert(reservation);
	}

	@Override
	public ProductStock getStock(int productId) {
		return stockDao.getStock(productId);
	}

	@Override
	public ProductStock save(ProductStock bean) {
		try {
			stockDao.getStock(bean.getProductId());
			stockDao.update(bean);
		} catch (ProductStockNotFoundException e) {
			stockDao.insert(bean);
		}
		return bean;
	}

}
