package com.prudential.carrental.controller;

import com.prudential.carrental.dto.OrderDto;
import com.prudential.carrental.service.CarRentalService;
import com.prudential.carrental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    @Resource
    private CarRentalService carRentalService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<OrderDto>> getCarList() {
        try {
            return carRentalService.findOrderList();
        } catch (Exception e) {
            log.warn("Get order list failed. {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

}
