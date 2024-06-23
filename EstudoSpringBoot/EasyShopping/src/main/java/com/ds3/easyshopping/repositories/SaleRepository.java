package com.ds3.easyshopping.repositories;

import com.ds3.easyshopping.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sales, UUID> {

}
