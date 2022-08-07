package com.prudential.carrental.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class Order {
    /**
     * Order id
     */
    private int oid;
    /**
     * Car id
     */
    private int cid;
    /**
     * customer name
     */
    private String customer;
    /**
     * Date for renting the car
     */
    private Date rentTime;
    /**
     * Date for returning the car
     */
    private Date returnTime;
    /**
     * true: car has been returned; otherwise: car has not been returned
     */
    private boolean returned;
}
