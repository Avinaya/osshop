package com.berrybytes.osshop.service;

import com.berrybytes.osshop.dto.OrderRequest;
import com.berrybytes.osshop.exception.ResourceNotFoundException;
import com.berrybytes.osshop.model.OrderItem;
import com.berrybytes.osshop.model.Orders;
import com.berrybytes.osshop.model.Product;
import com.berrybytes.osshop.model.Users;
import com.berrybytes.osshop.repository.OrderItemRepo;
import com.berrybytes.osshop.repository.OrdersRepo;
import com.berrybytes.osshop.repository.ProductRepo;
import com.berrybytes.osshop.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class OrderServiceImpl implements OrdersService{

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Override
    public Orders saveOrders(List<OrderRequest> orderRequests) {

        List<Product> productList = new ArrayList<>();
        Set<OrderItem> orderItemSet = new HashSet<>();
        AtomicReference<Double> totalCost= new AtomicReference<>(0.0);

        orderRequests.forEach(orderRequest -> {
            Optional<Product> productOptional = productRepo.findById(orderRequest.getProductId());
            if (productOptional.isPresent()) {
                totalCost.set(totalCost.get() + productOptional.get().getPrice());
                productList.add(productOptional.get());
            }
        });

        Orders orders = new Orders();
        orders.setOrderNumber("123");
        orders.setOrderStatus("Pending");
        orders.setTotalCosts(totalCost.get());
        orders.setUsers(getUser());

        Orders savedOrder = ordersRepo.save(orders);
        saveOrderItems(productList,savedOrder);
        return savedOrder;
    }

    @Override
    public Orders getOrdersById(long id) {
        Optional<Orders> optionalOrders = ordersRepo.findById(id);
        log.info("Optional Order Status :: {}",optionalOrders);
        if(optionalOrders.isEmpty()){
            log.error("order not found with Id :: {}",id);
            throw new ResourceNotFoundException("order not found with Id "+ id);
        }
        return optionalOrders.get();
    }

    private void saveOrderItems(List<Product> productList,Orders orders){
        productList.forEach(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItem.setProductPrice(product.getPrice());
            orderItemRepo.save(orderItem);
        });
    }

    private Users getUser(){
        Optional<Users> usersOptional = userRepo.findById(1L);
        return usersOptional.orElse(null);

    }
}
