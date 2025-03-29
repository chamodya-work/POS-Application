package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pos.service.ItemService;
import com.springbootacademy.pos.service.OrderService;
import com.springbootacademy.pos.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//this is combine of @Controller @ResponseBody //@ResponseBody help to convert java object to json object
@RequestMapping("api/v1/order")
@Validated // when is use like @Max(50) int size
//we have to explicitly validated
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String message = orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success",message),
                HttpStatus.CREATED
        );
    }
    @GetMapping(
            path = "/get-order-details",
            params = {"stateType","page","size"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String state,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseOrderDetails paginatedResponseOrderDetails=null;
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")){
            boolean status=state.equalsIgnoreCase("active")? true:false;
            paginatedResponseOrderDetails=orderService.getAllOrderDetails(status,page,size);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseOrderDetails),
                HttpStatus.CREATED
        );
    }
}
