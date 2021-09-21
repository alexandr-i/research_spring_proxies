package com.study.ivankov.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ivankov_A
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Element Found") // 404
public class NoSuchElementFoundException extends ShopRuntimeException {

    private static final long serialVersionUID = 4276536134579346524L;

    public NoSuchElementFoundException(String message) {
        super(message);
    }


}
