package com.ds3.easyshopping.services;


import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.Products;
import com.ds3.easyshopping.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @InjectMocks //Faz a injeção dos mock dentro da nossa classe de service
    ProductsService productsService;

    @Mock // Moka nossas classes que estão sendo usadas dentro do service
    ProductRepository productRepository;

    @BeforeEach // Inicia antes dos testes qualque valor que precise
    void setUp(){
        MockitoAnnotations.initMocks(this); //Inicia nossos mocks
    }


    @Test // Diz ao jpa que essa é uma teste a ser executado
    @DisplayName("Get product by id with success") //Define um nome para o teste
    void getProductByID() {
        ProductsDTO productsDTO = new ProductsDTO("Produto1",10.2,20);
        Products product = new Products();
        BeanUtils.copyProperties(productsDTO, product);

        Optional<Products> productsOptional = Optional.of(product);


        //Quando chamarmos o findal all, vai retornar nossa lista acima
        when(productRepository.findById(product.getId())).thenReturn(productsOptional);


        Optional<Products> productsReturn = productsService.getProductByID(product.getId());

        //Verifica se o objeto que retornamos no repository é igual ao retorno do service
        assertEquals(productsOptional, productsReturn);

        //Verifica se o nosso repositorio, no método findByID e chamado uma vez
        verify(productRepository).findById(product.getId());

        //Verifica se o nosso repositorio, no método findByID não é chamado mais de uma vez
        verifyNoMoreInteractions(productRepository);
    }
}