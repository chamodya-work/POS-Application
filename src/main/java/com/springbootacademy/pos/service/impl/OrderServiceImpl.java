package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos.dto.queryInterface.OrderDetailsInterface;
import com.springbootacademy.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pos.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.entity.Order;
import com.springbootacademy.pos.entity.OrderDetails;
import com.springbootacademy.pos.repo.CustomerRepo;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.repo.OrderDetailsRepo;
import com.springbootacademy.pos.repo.OrderRepo;
import com.springbootacademy.pos.service.OrderService;
import com.springbootacademy.pos.util.mappers.ItemMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order=new Order(
                customerRepo.getReferenceById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if (orderRepo.existsById(order.getOrderId())){
            //List<OrderDetails> orderDetails=new ArrayList<>();
            List<OrderDetails> orderDetails=modelMapper.map(requestOrderSaveDTO.getOrderDetail(),new TypeToken<List<OrderDetails>>(){}.getType());
            for (int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrder(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(requestOrderSaveDTO.getOrderDetail().get(i).getItems()));
            }
            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
                return "saved";
            }
        } return "Order creation failed.";
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailsInterface> responseOrderDetailsDTOS =orderRepo.getAllOrderDetail(status, PageRequest.of(page,size));

        List<ResponseOrderDetailsDTO> list=new ArrayList<>();
        for (OrderDetailsInterface o:responseOrderDetailsDTOS){
            ResponseOrderDetailsDTO r=new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails=new PaginatedResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(status)
        );
        return paginatedResponseOrderDetails;
    }
}
