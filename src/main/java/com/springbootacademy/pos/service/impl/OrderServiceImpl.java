package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.repo.OrderRepo;
import com.springbootacademy.pos.service.OrderService;
import com.springbootacademy.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        return null;
    }
}
