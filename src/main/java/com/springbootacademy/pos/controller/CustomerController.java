package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pos.service.CustomerService;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@RestController //this is combine of @Controller @ResponseBody //@ResponseBody help to convert java object to json object
@RequestMapping("api/v1/customer")


public class CustomerController {
    @Autowired //use to inject bean (simply object in the container )
    private CustomerService customerService;
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved  successfully";
    }
    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String message = customerService.updateCustomer(customerUpdateDTO);
        return message;
    }
    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }
    @GetMapping(
            path = "/get-all-customers"
    )
//    public List<CustomerDTO> getAllCustomers(){
//        List<CustomerDTO> getAllCustomers=customerService.getAllCustomers();
//        return getAllCustomers;
//    }
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> getAllCustomers=customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success",getAllCustomers), HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "deleteCustomer{cusid}"
    )
    public String deleteCustomer( int customerId){
        String delete=customerService.deleteCustomer(customerId);
        return delete;
    }
    @GetMapping(
            path = "get-all-customers-by-active-states{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveStatus(@PathVariable(value = "status") boolean customerStatus){
        List<CustomerDTO> getAllCustomersByStatus=customerService.getAllCustomersByStatus(customerStatus);
        return getAllCustomersByStatus;
    }

}
