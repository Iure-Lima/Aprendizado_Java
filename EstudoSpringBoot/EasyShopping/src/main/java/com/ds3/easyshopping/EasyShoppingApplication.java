package com.ds3.easyshopping;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Parking Control", version = "1.0.0", description = "API Desenvolvida para aprender spring boot"))
public class EasyShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyShoppingApplication.class, args);
    }

}
