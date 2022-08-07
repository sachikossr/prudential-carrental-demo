package com.prudential.carrental.controller;

import com.prudential.carrental.dto.CarDto;
import com.prudential.carrental.service.CarRentalService;
import com.prudential.carrental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@Slf4j
public class CarRentalController {
    @Resource
    private CarRentalService carRentalService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CarDto>> getCarList() {
        try {
            return carRentalService.findCarList();
        } catch (Exception e) {
            log.warn("Get car list failed. {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/rent/{carId}", method = RequestMethod.POST)
    @ResponseBody
    public Result rentCar(@PathVariable int carId, @RequestParam String customer, @RequestParam int days) {
        try {
            return carRentalService.rentCar(carId, customer, days);
        } catch (Exception e) {
            log.warn("Rent car {} failed. {}", carId, e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/return/{carId}", method = RequestMethod.POST)
    @ResponseBody
    public Result returnCar(@PathVariable int carId) {
        try {
            return carRentalService.returnCar(carId);
        } catch (Exception e) {
            log.warn("Return car {} failed. {}", carId, e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
