package com.study.ivankov.shop.converter;

import com.study.ivankov.shop.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Ivankov_A
 */
@Component
public class PriceCorrectionAnnotationCorrector {

    private final Logger LOG = LoggerFactory.getLogger(PriceCorrectionAnnotationCorrector.class);

    public void correctPrice(Product product, String role) {
        int percentageModifier = 100;
        switch (role) {
            case "ROLE_ADMIN":
                percentageModifier = 80;
                break;
            case "ROLE_USER":
                percentageModifier = 130;
                break;
            case "ROLE_OPT":
                percentageModifier = 110;
                break;
            default:
                LOG.debug("No PriceCorrection policy for group: {}", role);
                break;
        }
        product.setCost(product.getCost()
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP)
                .multiply(new BigDecimal(percentageModifier)));
    }

}
