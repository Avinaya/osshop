package com.berrybytes.osshop.service;

import com.berrybytes.osshop.dto.OrderRequest;
import com.berrybytes.osshop.model.Orders;

import java.util.List;

public interface OrdersService {

    Orders saveOrders(List<OrderRequest> orderRequests);

    Orders getOrdersById(long id);
}
