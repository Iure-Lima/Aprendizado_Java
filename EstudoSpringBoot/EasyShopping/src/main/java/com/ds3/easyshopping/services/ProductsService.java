package com.ds3.easyshopping.services;

import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.Products;
import com.ds3.easyshopping.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisCacheManager cacheManager;


    @Transactional
    public ResponseEntity<Object> saveProduct(ProductsDTO productsDTO){
        Products product = new Products();
        BeanUtils.copyProperties(productsDTO, product);

        if (product.getPrice() <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Price");

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Transactional
    @Cacheable(value = "product")
    public Products saveProductAndCache(Products products){
        productRepository.save(products);

        return products;
    }

    public List<Products> getAllProducts(Pageable page){
        List<Products> products = new ArrayList<>();

        productRepository.findAll(page).forEach(product ->{
            products.add(product);
        });

        return products;
    }

    public Optional<Products> getProductByID(UUID id){
        return productRepository.findById(id);
    }


    public ResponseEntity<String> deleteProduct(UUID id){

        Optional<Products> product = productRepository.findById(id);

        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    @CacheEvict(value = "product", key = "#id")
    public Products deleteProductAndCache(UUID id){

        Optional<Products> product = productRepository.findById(id);

        if (product.isEmpty()) return null;

        productRepository.delete(product.get());
        return product.get();
    }

    public ResponseEntity<Object> updateProduct(ProductsDTO productDto, UUID id){
        Optional<Products> product = productRepository.findById(id);

        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        Products newProduct = product.get();
        BeanUtils.copyProperties(productDto, newProduct);
        newProduct.setUpdateAt(new Date());

        productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(newProduct);

    }

    public ResponseEntity<Object> updateStockProduct(UUID id, Integer amount){
        Products product = productRepository.findById(id).get();
        if (product.getStock() <= 0 || product.getStock() < amount) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product don't have stock for this");

        product.setStock(product.getStock() - amount);
        product.setUpdateAt(new Date());

        return ResponseEntity.status(HttpStatus.OK).body("Stock updated");

    }



}
