package com.flipkart.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flipkart.dto.OrderDto;
import com.flipkart.dto.ProductDto;
import com.flipkart.service.OrderService;

@RequestMapping("/Order/api")
@RestController
public class OrderController {

    @Autowired
    OrderService os;

    @PostMapping("/CreateOrder")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto request) {
        return os.createOrder(request);
    }

    @GetMapping("/GetAllOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return os.getAllOrders();
    }

    @GetMapping("/GetOrderById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return os.getOrderById(id);
    }

    @PutMapping("/UpdateOrderById/{id}")
    public ResponseEntity<Optional<OrderDto>> updateOrderById(@PathVariable Long id, @RequestBody OrderDto request) {
        return os.updateOrderById(id, request);
    }

    @DeleteMapping("/DeleteOrderById/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return os.deleteOrderById(id);
    }

    @GetMapping("/GetProduct/{id}")
    public ResponseEntity<ProductDto> getProductFromDatacenter(@PathVariable Long id) {
        return os.getProductFromDatacenter(id);
    }
}
