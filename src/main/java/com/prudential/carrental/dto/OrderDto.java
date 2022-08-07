package com.prudential.carrental.dto;

import com.prudential.carrental.entity.Order;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDto {

    private Integer oid;
    private Integer cid;
    private String customer;
    private String rentTimeStr;
    private String returnTimeStr;
    private Date rentTime;
    private Date returnTime;
    private Boolean returned;
    private Integer days;

    public static OrderDto toDTO(Order order) {
        return OrderDto.builder()
                .oid(order.getOid())
                .cid(order.getCid())
                .customer(order.getCustomer())
                .rentTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getRentTime()))
                .returnTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getReturnTime()))
                .returned(order.isReturned())
                .build();
    }
}
