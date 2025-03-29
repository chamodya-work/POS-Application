package com.springbootacademy.pos.dto.response;

import com.springbootacademy.pos.dto.request.RequestOrderDetailsSave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseOrderDetailsDTO {
    //customer
    private String customerName;
    private String customerAddress;
    private ArrayList<String> contactNumber;

    //order
    private Date date;
    private Double total;
}
