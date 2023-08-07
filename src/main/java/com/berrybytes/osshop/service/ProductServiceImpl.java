package com.berrybytes.osshop.service;

import com.berrybytes.osshop.model.Product;
import com.berrybytes.osshop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product saveProduct(Product product) {
       return productRepo.save(product);
    }
}
