package com.study.ivankov.stock.fwk;

import com.study.ivankov.common.annotation.CountEnhancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Ivankov_A
 */
public class CountEnhancerAnnotationMethodResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOG = LoggerFactory.getLogger(CountEnhancerAnnotationMethodResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        LOG.debug("CountEnhancerAnnotationMethodResolver supportsParameter has: {}", parameter.hasParameterAnnotation(CountEnhancer.class));
        return parameter.hasParameterAnnotation(CountEnhancer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        LOG.debug("CountEnhancerAnnotationMethodResolver resolveArgument");
        return mavContainer != null ? mavContainer.getModel() : null;
    }

}