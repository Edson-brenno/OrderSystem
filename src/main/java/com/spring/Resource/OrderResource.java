package com.spring.Resource;

import com.spring.Entities.Enums.OrderStatus;
import com.spring.Entities.Mensage;
import com.spring.Entities.User;
import com.spring.Service.Orderservice;
import com.spring.Entities.Order;
import com.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderResource{
    @Autowired
    private Orderservice orderservice;
    @Autowired
    private UserService userservice;

    @GetMapping
    public ResponseEntity<List<Order>> orders(){
        return ResponseEntity.ok().body(orderservice.getOrders());
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Mensage<Order>> addOrder(@PathVariable int id){
        Optional<User> cliente = userservice.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }

        Order newOrder = new Order(cliente.get(), OrderStatus.WAITING_PAYMENT);
        orderservice.registerOrder(newOrder);
        return ResponseEntity.ok().body(new Mensage<Order>("Pedido registrado!", newOrder));
    }
}
