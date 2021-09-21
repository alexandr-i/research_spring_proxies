package com.study.ivankov.shop.controller;

import com.study.ivankov.shop.app.AppShopConfig;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ivankov_A
 */
@SpringBootTest(classes = {AppShopConfig.class})
@AutoConfigureMockMvc
public class ShopRestControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void accessSecuredResourceUsingBaseAuthThenOk() throws Exception {
//        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("admin:admin").getBytes()));
//        mockMvc.perform(get("/product").header("Authorization", basicDigestHeaderValue)).andExpect(status().isOk());
//    }

    @Test
    public void accessSecuredResourceUsingBaseAuthWithInvalidUserThenUnauthorized() throws Exception {
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("invalid:invalid").getBytes()));
        mockMvc.perform(get("/product").header("Authorization", basicDigestHeaderValue)).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void accessSecuredResourceAuthenticatedWithInvalidUser() throws Exception {
        mockMvc.perform(get("/product")).andExpect(status().isOk());
    }
}
