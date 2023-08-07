package com.berrybytes.osshop.controller;

import com.berrybytes.osshop.dto.OrderRequest;
import com.berrybytes.osshop.model.Orders;
import com.berrybytes.osshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;


    @PostMapping
    public ResponseEntity<Orders> saveOrders(@RequestBody List<OrderRequest> orderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.saveOrders(orderRequest));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable(name = "orderId") long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrdersById(orderId));
    }
}
