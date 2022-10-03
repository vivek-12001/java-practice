package com.example.crudapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;
    private int qty;
    private BigDecimal price;
    private Person person;
}
