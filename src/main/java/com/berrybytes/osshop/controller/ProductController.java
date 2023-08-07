package com.berrybytes.osshop.controller;

import com.berrybytes.osshop.model.Product;
import com.berrybytes.osshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product product1 = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }

}
