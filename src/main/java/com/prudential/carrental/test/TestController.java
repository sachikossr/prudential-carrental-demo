package com.prudential.carrental.test;

import com.prudential.carrental.dto.CarDto;
import com.prudential.carrental.dto.OrderDto;
import com.prudential.carrental.utils.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<Result<List<CarDto>>> typeReference = new ParameterizedTypeReference<Result<List<CarDto>>>() {
        };
        ResponseEntity<Result<List<CarDto>>> response = restTemplate.exchange("https://carrental1.azurewebsites.net/api/v1/cars", HttpMethod.GET, null, typeReference);
        model.addAttribute("carList", response.getBody().getData());
        ParameterizedTypeReference<Result<List<OrderDto>>> typeReference1 = new ParameterizedTypeReference<Result<List<OrderDto>>>() {
        };
        ResponseEntity<Result<List<OrderDto>>> response1 = restTemplate.exchange("https://carrental1.azurewebsites.net/api/v1/orders", HttpMethod.GET, null, typeReference1);
        model.addAttribute("orderList", response1.getBody().getData());
        model.addAttribute("orderDto", new OrderDto());
        return "index";
    }

    @RequestMapping(value = "/rent/{carId}", method = RequestMethod.POST)
    public String rentCar(@ModelAttribute(value = "orderDto") OrderDto dto, @PathVariable int carId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        requestMap.put("customer", Collections.singletonList(dto.getCustomer()));
        requestMap.put("days", Collections.singletonList(dto.getDays()));
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(requestMap, headers);
        ResponseEntity<Result> response = restTemplate.exchange("https://carrental1.azurewebsites.net/api/v1/cars/rent/" + carId, HttpMethod.POST, entity, Result.class);
        return "redirect:../ ";
    }

    @RequestMapping(value = "/return/{carId}", method = RequestMethod.POST)
    public String returnCar(@ModelAttribute(value = "orderDto") OrderDto dto, @PathVariable int carId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(headers);
        ResponseEntity<Result> response = restTemplate.exchange("https://carrental1.azurewebsites.net/api/v1/cars/return/" + carId, HttpMethod.POST, entity, Result.class);
        return "redirect:../ ";
    }

}
