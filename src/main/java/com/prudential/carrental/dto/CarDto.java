package com.prudential.carrental.dto;

import com.prudential.carrental.entity.Car;
import lombok.*;

import java.text.SimpleDateFormat;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarDto {
    private Integer cid;
    private String model;
    private Boolean inStock;
    private String tenancy;

    public static CarDto toDTO(Car car) {
        return CarDto.builder()
                .cid(car.getCid())
                .model(car.getModel())
                .inStock(car.isInStock())
                .tenancy(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(car.getTenancy()))
                .build();
    }

}
