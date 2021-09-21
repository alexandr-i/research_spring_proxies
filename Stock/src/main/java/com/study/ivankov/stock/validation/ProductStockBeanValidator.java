package com.study.ivankov.stock.validation;

import com.study.ivankov.common.domain.ProductStock;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Ivankov_A
 */
@Component
public class ProductStockBeanValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return ProductStock.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "productId.empty", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "accountName.empty", "field.required");

        ProductStock theBean = (ProductStock) target;
        if (theBean.getQuantity() <= 0) {
            errors.rejectValue("quantity", "quantity.empty", "Can't be <= 0");
        }
    }

}
