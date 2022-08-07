package com.prudential.carrental;

import com.prudential.carrental.dto.CarDto;
import com.prudential.carrental.dto.OrderDto;
import com.prudential.carrental.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarRentalServiceTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getCarList() {
        ParameterizedTypeReference<Result<List<CarDto>>> typeReference = new ParameterizedTypeReference<Result<List<CarDto>>>() {
        };
        ResponseEntity<Result<List<CarDto>>> response = template.exchange("/api/v1/cars", HttpMethod.GET, null, typeReference);
        response.getBody().getData().forEach(carDto -> System.out.println(carDto.toString()));
    }

    @Test
    public void getOrderList() {
        ParameterizedTypeReference<Result<List<OrderDto>>> typeReference = new ParameterizedTypeReference<Result<List<OrderDto>>>() {
        };
        ResponseEntity<Result<List<OrderDto>>> response = template.exchange("/api/v1/orders", HttpMethod.GET, null, typeReference);
        response.getBody().getData().forEach(orderDto -> System.out.println(orderDto.toString()));
    }

    @Test
    public void rentCar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        requestMap.put("customer", Collections.singletonList("zjn"));
        requestMap.put("days", Collections.singletonList(3));
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(requestMap, headers);
        ResponseEntity<Result> response = template.exchange("/api/v1/cars/rent/1", HttpMethod.POST, entity, Result.class);
        System.out.println(response.getBody().getMessage());
    }

    @Test
    public void returnCar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(headers);
        ResponseEntity<Result> response = template.exchange("/api/v1/cars/return/1", HttpMethod.POST, entity, Result.class);
        System.out.println(response.getBody().getMessage());
    }
}
