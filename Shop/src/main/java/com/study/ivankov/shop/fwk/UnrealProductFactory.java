package com.study.ivankov.shop.fwk;

import com.study.ivankov.shop.domain.Product;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author Ivankov_A
 *
 */
@Component
public interface UnrealProductFactory {

	@Lookup
    Product getProduct();
}
