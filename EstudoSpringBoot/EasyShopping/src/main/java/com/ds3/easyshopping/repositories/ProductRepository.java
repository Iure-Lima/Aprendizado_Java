package com.ds3.easyshopping.repositories;

import com.ds3.easyshopping.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, UUID> {

    List<Products> findAllByStock(Integer stock);

}
