package com.prudential.carrental.dao;

import com.prudential.carrental.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderDao {

    Order findNoReturnedOrderByCid(@Param("cid") int cid);

    List<Order> findOrderList();

    int saveOrder(@Param("cid") int cid, @Param("customer") String customer, @Param("rentTime") Date rentTime, @Param("returnTime") Date returnTime);

    int updateOrder(@Param("oid") int oid, @Param("returnTime") Date returnTime);
}
