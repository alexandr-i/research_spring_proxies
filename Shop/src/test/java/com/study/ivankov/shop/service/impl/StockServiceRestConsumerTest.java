package com.study.ivankov.shop.service.impl;

import com.study.ivankov.shop.app.AppShopConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ivankov_A
 */
@SpringBootTest(classes = {AppShopConfig.class})
public class StockServiceRestConsumerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }
}
