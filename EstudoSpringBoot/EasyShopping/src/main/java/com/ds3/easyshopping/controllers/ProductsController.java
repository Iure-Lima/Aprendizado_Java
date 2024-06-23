package com.ds3.easyshopping.controllers;

import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.Products;
import com.ds3.easyshopping.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping
    public ResponseEntity<Object> saveproduct(@RequestBody @Valid ProductsDTO productDTO){
        Products product = new Products();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.OK).body(productsService.saveProductAndCache(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductsDTO product, @PathVariable(value = "id")UUID id){
        return productsService.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProduct(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.deleteProductAndCache(id));
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(@PageableDefault(page = 0, size = 20, sort = "price",
            direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductByID(@PathVariable(name = "id") UUID id){
        Optional<Products> product = productsService.getProductByID(id);

        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

}
