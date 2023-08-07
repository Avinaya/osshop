package com.berrybytes.osshop.repository;

import com.berrybytes.osshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {
}
