package com.prudential.carrental.service;

import com.prudential.carrental.dto.CarDto;
import com.prudential.carrental.dto.OrderDto;
import com.prudential.carrental.utils.Result;

import java.util.List;

public interface CarRentalService {

    Result<List<CarDto>> findCarList();

    Result rentCar(int cid, String customer, int days);

    Result returnCar(int cid);

    Result<List<OrderDto>> findOrderList();
}
