package com.prudential.carrental.service.impl;

import com.prudential.carrental.dao.CarDao;
import com.prudential.carrental.dao.OrderDao;
import com.prudential.carrental.dto.CarDto;
import com.prudential.carrental.dto.OrderDto;
import com.prudential.carrental.entity.Car;
import com.prudential.carrental.entity.Order;
import com.prudential.carrental.service.CarRentalService;
import com.prudential.carrental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CarRentalServiceImpl implements CarRentalService {
    private static final int SUCCESS = 0;
    @Resource
    CarDao carDao;
    @Resource
    OrderDao orderDao;

    @Override
    public Result<List<CarDto>> findCarList() {
        return Result.success(carDao.findCarList().stream().map(CarDto::toDTO).collect(Collectors.toList()), "ok");
    }

    @Override
    public Result rentCar(int cid, String customer, int days) {
        Car car = carDao.find(cid);
        if (car == null || !car.isInStock()) {
            return Result.error("Rent car failed: " + cid);
        }
        Date rentTime = new Date();
        int res = carDao.rentCar(cid, rentTime);
        if (res <= SUCCESS) {
            return Result.error("Rent car failed: " + cid);
        }
        saveOrder(cid, customer, rentTime, days);
        return Result.success("ok");
    }

    @Override
    public Result returnCar(int cid) {
        Car car = carDao.find(cid);
        if (car == null || car.isInStock()) {
            return Result.error("Return car failed: " + cid);
        }
        Date returnTime = new Date();
        int res = carDao.returnCar(cid, returnTime);
        if (res <= SUCCESS) {
            return Result.error("Return car failed: " + cid);
        }
        Order order = orderDao.findNoReturnedOrderByCid(cid);
        if (order == null) {
            return Result.error("Return car failed: " + cid);
        }
        orderDao.updateOrder(order.getOid(), returnTime);
        return Result.success("ok");
    }

    @Override
    public Result<List<OrderDto>> findOrderList() {
        return Result.success(orderDao.findOrderList().stream().map(OrderDto::toDTO).collect(Collectors.toList()), "ok");
    }

    private void saveOrder(int cid, String customer, Date rentTime, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentTime);
        calendar.add(Calendar.DATE, days);
        Date returnTime = calendar.getTime();
        orderDao.saveOrder(cid, customer, rentTime, returnTime);
    }
}
