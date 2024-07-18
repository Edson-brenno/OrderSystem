package com.spring.Entities.Enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public OrderStatus getStatus(int code){
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == code) {
                return status;
            }
        }

        throw new IllegalArgumentException("No OrderStatus with value " + code + " found");
    }
}
