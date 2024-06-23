package com.ds3.easyshopping.controllers;

import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.Products;
import com.ds3.easyshopping.services.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    @InjectMocks
    ProductsController productsController; //Faz injeção de mocks no service

    @Mock
    private ProductsService productsService; // diz que o service é mocado

    private MockMultipartFile mockMultipartFile; //cria um arquivo de cobertura

    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productsController).alwaysDo(print()).build();
        mockMultipartFile = new MockMultipartFile("Teste1", "Teste1.pdf", MediaType.TEXT_PLAIN_VALUE, "HelloWorld".getBytes());
    }

    @Test
    void saveproduct() {
        ProductsDTO productDTO = new ProductsDTO("Palito", 2.30,20000);
        Products product = new Products();
        BeanUtils.copyProperties(productDTO, product);


        when(productsService.saveProduct(productDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(product));

        mockMvc.perform(multipart("/produtos").file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status()).andReturn();
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductByID() {
    }
}