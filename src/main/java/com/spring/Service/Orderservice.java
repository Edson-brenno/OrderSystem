package com.spring.Service;

import com.spring.Entities.User;
import com.spring.Repository.OrderRepository;
import com.spring.Resource.OrderResource;
import com.spring.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Orderservice {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public void registerOrder(Order pedido){
        orderRepository.save(pedido);
    }
}
