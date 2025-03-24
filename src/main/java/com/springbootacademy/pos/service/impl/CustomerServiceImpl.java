package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.exception.NotFoundException;
import com.springbootacademy.pos.repo.CustomerRepo;
import com.springbootacademy.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // in here we want object from this class to controller class
//we do generally using defined that as bean using @component annotation
//but actually we're  using @Service ,because @component already in the @service



public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO){
        Customer customer=new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getContactNumber(),
                customerDTO.getSalary(),
                customerDTO.getNic(),
                customerDTO.isActive()
        );
        customerRepo.save(customer);
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer=customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());

            customerRepo.save(customer);
        }else throw new RuntimeException("not data found");
        return customerUpdateDTO.getCustomerName()+ " Updated Successfully";
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)){
            Customer customer=customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO=new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumber(),
                    customer.getSalary(),
                    customer.getNic(),
                    customer.isActive()
            );
            return customerDTO;
        }else throw new RuntimeException("no valid customer");

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList=customerRepo.findAll();
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        for (Customer customer: customerList){
            CustomerDTO customerDTO=new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumber(),
                    customer.getSalary(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "delete succesfully "+customerId;
        }else throw new NotFoundException("no valid customerId exist for this");
    }

    @Override
    public List<CustomerDTO> getAllCustomersByStatus(boolean customerStatus) {
        List<Customer> customerList=customerRepo.findAllByActive(customerStatus);
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        for (Customer customer: customerList){
            CustomerDTO customerDTO=new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumber(),
                    customer.getSalary(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }


}
