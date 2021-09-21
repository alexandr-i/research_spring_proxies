package com.study.ivankov.stock.validation;

import com.study.ivankov.common.domain.ProductReservation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Ivankov_A
 */
@Component
public class ProductReservationBeanValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return ProductReservation.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "productId.empty", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "quantity.empty", "field.required");

        ProductReservation theBean = (ProductReservation) target;
        if (theBean.getQuantity() <= 0) {
            errors.rejectValue("quantity", "quantity.wrong", "Can't be <= 0");
        }
    }

}
