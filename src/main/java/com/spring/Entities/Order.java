package com.spring.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.Entities.Enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_orders")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "moment", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Column(name = "order_status")
    private int orderStatus;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User cliente = new User();

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {}

    public Order(User cliente, OrderStatus orderStatus) {
        this.cliente = cliente;
        this.moment = Instant.now();
        this.setOrderStatus(orderStatus);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getCliente() {
        return cliente;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getValue();
        }
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Set<OrderItem> getItems() {
        return items;
    }
}
