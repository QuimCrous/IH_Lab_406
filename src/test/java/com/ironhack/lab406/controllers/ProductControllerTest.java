package com.ironhack.lab406.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.DTO.ProductQuantityDTO;
import com.ironhack.lab406.repositories.DepartmentRepository;
import com.ironhack.lab406.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("test product is created and added")
    void post_Product_isCreated() throws Exception{
        ProductDTO product = new ProductDTO(200L,"test_1",2L);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/products/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(productRepository.findByName("test_1").isPresent());
    }

    @Test
    @DisplayName("test decrease quantity ok")
    void patch_Product_DecreaseQuantity() throws Exception{
        ProductQuantityDTO product = new ProductQuantityDTO(10L,2L);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(patch("/products/decrease").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(productRepository.findById(2L).get().getQuantity().equals(140L));
    }

    @Test
    @DisplayName("test get all products ok")
    void get_Products_All_OK() throws Exception{
        String body = objectMapper.writeValueAsString(1L);

        MvcResult mvcResult = mockMvc.perform(get("/products").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("shovel"));
    }

    @Test
    @DisplayName("test get product ok")
    void get_Product_byId() throws Exception{
        String body = objectMapper.writeValueAsString(2L);

        MvcResult mvcResult = mockMvc.perform(get("/productbyid").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("shovel"));
    }

    @Test
    @DisplayName("test delete product ok")
    void delete_Product_byId_works() throws Exception{
        String body = objectMapper.writeValueAsString(1L);

        MvcResult mvcResult = mockMvc.perform(delete("/erase-product").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(!productRepository.findById(1L).isPresent());
    }

    @Test
    @DisplayName("test add department ok")
    void post_add_department_worksOk() throws Exception{
        String body = objectMapper.writeValueAsString("testing_dep");

        MvcResult mvcResult = mockMvc.perform(post("/departments/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(departmentRepository.findById(7L).get().getDepartment().contains("testing_dep"));
    }

    @Test
    @DisplayName("test get product throws exception")
    void get_product_byId_throws_Not_Found() throws Exception{
        String body = objectMapper.writeValueAsString(15L);

        MvcResult mvcResult = mockMvc.perform(get("/productbyid").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("test delete product throws exception")
    void delete_product_byId_throws_Not_Found() throws Exception{
        String body = objectMapper.writeValueAsString(15L);

        MvcResult mvcResult = mockMvc.perform(delete("/erase-product").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("test decrease quantity throws exception")
    void patch_Product_DecreaseQuantity_throws_Exception() throws Exception{
        ProductQuantityDTO product = new ProductQuantityDTO(1000L,2L);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(patch("/products/decrease").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }
}
