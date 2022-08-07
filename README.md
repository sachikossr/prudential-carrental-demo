# prudential-carrental-demo

## Technology Selection

| Tech                 | Illustration                | Official                                                         |
| -------------------- | -------------------         | ------------------------------------------------------------     |
| Spring Boot          | Container + MVC Frame       | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| MyBatis              | ORM Frame                   | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| Thymeleaf            | Template Engine             | [https://www.thymeleaf.org/](https://www.thymeleaf.org/) |


## Environment

Tool  | Version | Official
----  |----     |----
JDK   | 1.8     | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 5.7     | https://www.mysql.com/


## Table Description
* car：    Record car stock of the company.
* orders： Record history rental orders.

## API
    1. GET /api/v1/cars          // list all cars of the company
       Response: 
        {
          "cid": 1,               // int: car id  
          "model": "BMW 650",    // string: car model name 
          "inStock": true,        // boolean: whether the car is in stock
          "tenancy": "2022-01-01"  // string: date for the car rental tenancy 
        } 
    2. POST /api/v1/cars/rent/{carId}     // rent a car
        path_variable:
            carId: car id
        Request:
        {
         "customer": "testUser",   // string: customer name
         "days": 4                // int: rent days
        }
    
    3. POST /api/v1/cars/return/{carId}     // return a car
        path_variable:
            carId: car id
       
    4. GET /api/v1/orders  // list history rental orders
       Response: 
        {
          "oid": 1,                       // int: order id  
          "cid": 1,                       // int: car id  
          "customer": "testUser",         // string: customer name
          "rentTimeStr": "2022-01-01",    // string: car rent date
          "returnTimeStr": "2022-01-01"   // string: car return date
          "returned": true                // boolean: whether the car is returned
        } 

## Online Test URL
[https://carrental1.azurewebsites.net](https://carrental1.azurewebsites.net)
