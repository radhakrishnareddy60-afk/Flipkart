package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flipkart.dto.OrderDto;
import com.flipkart.dto.ProductDto;
import com.flipkart.entity.OrderEntity;
import com.flipkart.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository or;

    @Autowired
    private RestTemplate restTemplate;

    private final String datacenterUrl = "http://localhost:9444/Product/api/";

    public ResponseEntity<OrderDto> createOrder(OrderDto request) {
        OrderEntity newOrder = new OrderEntity();
        newOrder.setOrderLocation(request.getOrderLocation());
        newOrder.setOrderPrice(request.getOrderPrice());

        OrderEntity savedOrder = or.save(newOrder);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(savedOrder.getOrderId());
        orderDto.setOrderLocation(savedOrder.getOrderLocation());
        orderDto.setOrderPrice(savedOrder.getOrderPrice());

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderEntity> orderEntities = or.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (OrderEntity oe : orderEntities) {
            OrderDto dto = new OrderDto();
            dto.setOrderId(oe.getOrderId());
            dto.setOrderLocation(oe.getOrderLocation());
            dto.setOrderPrice(oe.getOrderPrice());
            orderDtos.add(dto);
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> getOrderById(Long id) {
        Optional<OrderEntity> orderEntity = or.findById(id);

        if (orderEntity.isPresent()) {
            OrderEntity oe = orderEntity.get();
            OrderDto dto = new OrderDto();
            dto.setOrderId(oe.getOrderId());
            dto.setOrderLocation(oe.getOrderLocation());
            dto.setOrderPrice(oe.getOrderPrice());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Optional<OrderDto>> updateOrderById(Long id, OrderDto request) {
        Optional<OrderEntity> orderEntity = or.findById(id);

        if (orderEntity.isPresent()) {
            OrderEntity oe = orderEntity.get();
            oe.setOrderLocation(request.getOrderLocation());
            oe.setOrderPrice(request.getOrderPrice());
            or.save(oe);

            OrderDto dto = new OrderDto();
            dto.setOrderId(oe.getOrderId());
            dto.setOrderLocation(oe.getOrderLocation());
            dto.setOrderPrice(oe.getOrderPrice());
            return new ResponseEntity<>(Optional.of(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteOrderById(Long id) {
        Optional<OrderEntity> orderEntity = or.findById(id);

        if (orderEntity.isPresent()) {
            or.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ProductDto> getProductFromDatacenter(Long id) {
        try {
            ProductDto product = restTemplate.getForObject(datacenterUrl + "GetProductById/" + id, ProductDto.class);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
