package com.prudential.carrental.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Car {
    /**
     * Car id
     */
    private int cid;
    /**
     * Car model
     */
    private String model;
    /**
     * true: car is in stock; otherwise: car is not in stock
     */
    private boolean inStock;
    /**
     * Date for returning the car
     */
    private Date tenancy;
}
