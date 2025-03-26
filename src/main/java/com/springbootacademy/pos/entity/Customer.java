package com.springbootacademy.pos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id", length = 55)
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // this is for auto generation id
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "contact_numbers", columnDefinition = "JSON")
    @JdbcTypeCode(SqlTypes.JSON) // Correct Hibernate 6+ JSON mapping
    private ArrayList<String> contactNumber; // Direct ArrayList<String>

    @Column(name = "salary")
    private double salary;

    @Column(name = "nic")
    private String nic;

    @Column(name = "active_status", columnDefinition = "TINYINT default 0")
    private boolean active;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders ;

    // Constructors
    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, ArrayList<String> contactNumber, double salary, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.contactNumber = contactNumber;
        this.salary = salary;
        this.nic = nic;
        this.active = active;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ArrayList<String> getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ArrayList<String> contactNumber) {
        this.contactNumber = contactNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", contactNumber=" + contactNumber +
                ", salary=" + salary +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }
}