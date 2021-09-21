package com.study.ivankov.shop.service.impl;

import com.study.ivankov.common.annotation.PriceCorrection;
import com.study.ivankov.shop.domain.Product;
import com.study.ivankov.shop.jpa.ProductRepository;
import com.study.ivankov.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivankov_A
 */
@Service
@Lazy
@Qualifier("prodProductService")
public class ProductServiceProdEnv implements ProductService {

    @Autowired
    private ProductRepository prRepository;

    @Override
    public List<Product> findAll() {
        Iterable<Product> source = prRepository.findAll();
        List<Product> target = new ArrayList<>();
        source.iterator().forEachRemaining(target::add);
        return target;
    }

    @Override
    @PriceCorrection
    public Product getOne(int id) {
        return prRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product bean) {
        return prRepository.save(bean);
    }

    @Override
    public void saveAll(List<Product> beans) {
        prRepository.saveAll(beans);
    }

    @Override
    public void delete(int id) {
        prRepository.findById(id).ifPresent(prRepository::delete);
    }

    @Override
    @PriceCorrection
    public List<Product> searchByName(String name) {
        return prRepository.findByName(name);
    }

}
