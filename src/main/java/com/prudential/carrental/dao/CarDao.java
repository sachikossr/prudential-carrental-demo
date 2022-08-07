package com.prudential.carrental.dao;

import com.prudential.carrental.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarDao {
    Car find(@Param("cid") int cid);

    List<Car> findCarList();

    int rentCar(@Param("cid") int cid, @Param("tenancy") Date tenancy);

    int returnCar(@Param("cid") int cid, @Param("tenancy") Date tenancy);
}
