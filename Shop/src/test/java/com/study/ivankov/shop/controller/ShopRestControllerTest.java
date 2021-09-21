package com.study.ivankov.shop.controller;

import com.study.ivankov.shop.app.AppShopConfig;
import com.study.ivankov.shop.jpa.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ivankov_A
 */
@SpringBootTest(classes = {AppShopConfig.class})
@AutoConfigureMockMvc
public class ShopRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void deleteAllBeforeTests() {
        productRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void checkProductRoot() throws Exception {
        mockMvc.perform(get("/product")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].cost").exists());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void checkProductRootWithId() throws Exception {
        mockMvc.perform(get("/product/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.cost").exists());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void shouldCreateEntity() throws Exception {
        mockMvc.perform(post("/product").content("{\"name\": \"Banana\", \"cost\":999}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void shouldQueryEntity() throws Exception {
        shouldCreateEntity();

        mockMvc.perform(get("/product/search/{name}", "Banana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").value("Banana"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void shouldUpdateEntity() throws Exception {
        MvcResult mvcResult = createEntityAndGetResult();

        mockMvc.perform(put("/product").content(mvcResult.getResponse().getContentAsString().replaceAll("Banana", "MegaBanana")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MegaBanana"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void shouldDeleteEntity() throws Exception {
        MvcResult mvcResult = createEntityAndGetResult();

        String content = mvcResult.getResponse().getContentAsString();
        String id = content.substring(content.indexOf("\"id\":")).substring(5, content.indexOf(",") - 1);

        mockMvc.perform(delete("/product/" + id)).andExpect(status().isOk());
    }

    private MvcResult createEntityAndGetResult() throws Exception {
        return mockMvc.perform(post("/product").content("{\"name\": \"Banana\", \"cost\":999}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists()).andReturn();
    }
}
