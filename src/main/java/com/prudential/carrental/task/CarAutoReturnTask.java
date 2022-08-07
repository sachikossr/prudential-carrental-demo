package com.prudential.carrental.task;

import com.prudential.carrental.dao.CarDao;
import com.prudential.carrental.entity.Car;
import com.prudential.carrental.service.CarRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class CarAutoReturnTask {
    @Resource
    CarDao carDao;
    @Resource
    private CarRentalService carRentalService;

    @Scheduled(cron = "0 0 0 * * *")
    public void task() {
        List<Car> carList = carDao.findCarList();
        carList.forEach(car -> {
            if (!car.isInStock() && car.getTenancy().before(new Date())) {
                carRentalService.returnCar(car.getCid());
                log.info("System automatically returned car. {}", car.getCid());
            }
        });
    }
}
