package com.springbootacademy.pos.dto.queryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {
    String getCustomerName();
    String getCustomerAddress();
    ArrayList<String> getContactNumber();
    Date getDate();
    Double getTotal();
}