package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flipkart.dto.CustomerDto;
import com.flipkart.entity.CustomerEntity;
import com.flipkart.repository.CustomerRepository;

@Service
public class CustomerService {

	
	@Autowired
	CustomerRepository cr;
 
	public ResponseEntity<CustomerDto> createCust(CustomerDto request) {
	    CustomerEntity ce = new CustomerEntity();

	    ce.setPhone(request.getPhone());
	    ce.setAddress(request.getAddress());
	    ce = cr.save(ce);
	    request.setCustId(ce.getCustId());

	    return ResponseEntity.status(HttpStatus.CREATED).body(request);
	}

	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
	    List<CustomerEntity> customerEntities = cr.findAll();
	    List<CustomerDto> customerDtos = new ArrayList<>();

	    for (CustomerEntity ce : customerEntities) {
	        CustomerDto dto = new CustomerDto();
	        dto.setCustId(ce.getCustId());
	        dto.setPhone(ce.getPhone());
	        dto.setAddress(ce.getAddress());
	        customerDtos.add(dto);
	    }

	    return ResponseEntity.ok(customerDtos);
	}

	public ResponseEntity<CustomerDto> getCustomerById(Long id) {
	    Optional<CustomerEntity> customerEntityOptional = cr.findById(id);

	    if (customerEntityOptional.isPresent()) {
	        CustomerEntity ce = customerEntityOptional.get();
	        CustomerDto dto = new CustomerDto();
	        dto.setCustId(ce.getCustId());
	        dto.setPhone(ce.getPhone());
	        dto.setAddress(ce.getAddress());
	        return ResponseEntity.ok(dto);
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto request) {
	    Optional<CustomerEntity> customerEntity = cr.findById(id);
	    if (customerEntity.isPresent()) {
	        CustomerEntity ce = customerEntity.get();
	        ce.setPhone(request.getPhone());
	        ce.setAddress(request.getAddress());
	        cr.save(ce);

	        // Create a new CustomerDto to return, copying updated details and ID
	        CustomerDto updatedCustomer = new CustomerDto();
	        updatedCustomer.setCustId(ce.getCustId());
	        updatedCustomer.setPhone(ce.getPhone());
	        updatedCustomer.setAddress(ce.getAddress());

	        return ResponseEntity.ok(updatedCustomer);
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

      
	  public ResponseEntity<Void> deleteCustomerById(Long id) {
	        Optional<CustomerEntity> customerEntity = cr.findById(id);

	        if (customerEntity.isPresent()) {
	            cr.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	        }
	    }


	
	
	


}
