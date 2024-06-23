package com.mongo.productswithmongo.service;

import com.mongo.productswithmongo.dtos.ProductDTORequest;
import com.mongo.productswithmongo.models.ProductEntity;
import com.mongo.productswithmongo.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public ProductEntity saveProduct(ProductDTORequest productDTORequest){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(productDTORequest, productEntity);
        repository.save(productEntity);

        return productEntity;
    }

    public List<ProductEntity> getAllProducts(){
        return repository.findAll();
    }

    public Optional<ProductEntity> getProductByID(String id){
        return repository.findById(id);
    }

    public ResponseEntity<String> deleteProductByID(String id){
        Optional<ProductEntity> product = getProductByID(id);
        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");

        repository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }

    public ResponseEntity<Object> updateProduct(ProductDTORequest productDTORequest, String id){
        Optional<ProductEntity> product = getProductByID(id);
        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");

        ProductEntity newProduct = product.get();
        BeanUtils.copyProperties(productDTORequest, newProduct);

        repository.save(newProduct);

        return ResponseEntity.status(HttpStatus.OK).body(newProduct);

    }

}
