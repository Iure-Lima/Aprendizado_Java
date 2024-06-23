package com.ds3.easyshopping.repositories;

import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.Products;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest //Diz para o spring que essa é uma classe de teste
@ActiveProfiles("test") //Diz ao spring para usar o banco de dados de teste
class ProductRepositoryTest {

    @Autowired
    EntityManager entityManager; //necessario para trabalhar com os teste do banco de dados

    @Autowired
    ProductRepository productRepository;//Usado para fazer os testes

    @Test //Diz que vamos fazer um teste nesse método
    @DisplayName("Should get product sucessfully from DB") //Coloca uma descrição no caso de teste
    void findAllByStockCase1() {
        Integer stock = 500;
        ProductsDTO dto = new ProductsDTO("Lapis",20.0, stock);

        this.createProduct(dto);
        List<Products> result = this.productRepository.findAllByStock(stock);
        assertThat(result.isEmpty()).isFalse(); //faz a verificação se o produto existe


    }

    @Test //Diz que vamos fazer um teste nesse método
    @DisplayName("Shouldn't get product from DB when product not exist") //Coloca uma descrição no caso de teste
    void findAllByStockCase2() {
        Integer stock = 500;
        ProductsDTO dto = new ProductsDTO("Lapis",20.0, stock);
        List<Products> result = this.productRepository.findAllByStock(stock);
        assertThat(result.isEmpty()).isTrue(); //faz a verificação se o produto não existe;

    }

    //Método auxiliar para os casos de teste onde cria um produto
    private Products createProduct(ProductsDTO productsDTO){
        Products product = new Products();
        BeanUtils.copyProperties(productsDTO, product);
        this.entityManager.persist(product); //Salva o produto no banco de dados
        return product;
    }
}