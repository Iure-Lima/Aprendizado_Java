package com.ds3.easyshopping.services;

import com.ds3.easyshopping.models.DTOS.SalesDTO;
import com.ds3.easyshopping.models.Products;
import com.ds3.easyshopping.models.Sales;
import com.ds3.easyshopping.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductsService productsService;


    @Transactional
    public ResponseEntity<Object> saveSales(SalesDTO salesDTO){
        Sales sale = new Sales();
        BeanUtils.copyProperties(salesDTO, sale);

        if (sale.getAmount() <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid amount");

        Optional<Products> product = productsService.getProductByID(sale.getIdProduct());
        if (product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");



        double discount = 0;
        if (sale.getAmount() > 20) discount = 0.1;
        else if (sale.getAmount() > 10) discount = 0.05;

        sale.setPriceSale((product.get().getPrice() * sale.getAmount()) * (1 - discount));

        ResponseEntity<Object> productUpdateStockResponse = productsService.updateStockProduct(sale.getIdProduct(),sale.getAmount());
        if (productUpdateStockResponse.getStatusCode() != HttpStatus.OK) return productUpdateStockResponse;

        sale.setDateSale(new Date());
        saleRepository.save(sale);

        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    public List<Sales> getAllSales(){
        return saleRepository.findAll();
    }

    public Optional<Sales> getSaleByID(UUID id){
        return saleRepository.findById(id);
    }

    public ResponseEntity<String> deleteSales(UUID id){
        Optional<Sales> sale = saleRepository.findById(id);

        if (sale.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");

        saleRepository.delete(sale.get());

        return ResponseEntity.status(HttpStatus.OK).body("Sale deleted");
    }

}
