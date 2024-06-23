package com.ds3.easyshopping.controllers;

import com.ds3.easyshopping.models.DTOS.ProductsDTO;
import com.ds3.easyshopping.models.DTOS.SalesDTO;
import com.ds3.easyshopping.models.Sales;
import com.ds3.easyshopping.services.SalesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping
    public ResponseEntity<Object> saveSales(@RequestBody @Valid SalesDTO salesDTO){
        return salesService.saveSales(salesDTO);
    }

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales(){
        return ResponseEntity.status(HttpStatus.OK).body(salesService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSaleByID(@PathVariable(name = "id") UUID id){
        Optional<Sales> sale = salesService.getSaleByID(id);
        if (sale.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");

        return ResponseEntity.status(HttpStatus.OK).body(sale.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable(value = "id") UUID id){
        return salesService.deleteSales(id);
    }
}
