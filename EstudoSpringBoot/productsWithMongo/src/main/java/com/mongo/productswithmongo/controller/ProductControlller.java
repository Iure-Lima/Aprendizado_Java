package com.mongo.productswithmongo.controller;

import com.mongo.productswithmongo.dtos.ProductDTORequest;
import com.mongo.productswithmongo.models.ProductEntity;
import com.mongo.productswithmongo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductControlller {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductEntity> addProduct(@Valid @RequestBody ProductDTORequest productDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductByID(@PathVariable(value = "id") String id){
        Optional<ProductEntity> product = productService.getProductByID(id);
        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") String id){
        return productService.deleteProductByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") String id, @Valid @RequestBody ProductDTORequest prd){
        return productService.updateProduct(prd,id);
    }
}
