package com.mongo.productswithmongo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity
{
    @MongoId
    private String id;
    @Field(name = "Nome")
    private String name;
    @Field(name = "Estoque")
    private int stock;
    @Field(name = "Preco")
    private double price;
}
