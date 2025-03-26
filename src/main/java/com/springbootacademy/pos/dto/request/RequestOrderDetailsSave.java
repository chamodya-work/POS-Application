package com.springbootacademy.pos.dto.request;

import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.entity.Order;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RequestOrderDetailsSave {
    private String itemName;
    private double qty;
    private Double amount;
    private int items;
}
