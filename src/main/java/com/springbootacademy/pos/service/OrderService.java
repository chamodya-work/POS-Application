package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
