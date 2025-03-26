package com.springbootacademy.pos.dto.request;

import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.entity.OrderDetails;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data

public class RequestOrderSaveDTO {
    private int customer;
    private Date date;
    private List<RequestOrderDetailsSave> orderDetail;
    private Double total;

}
