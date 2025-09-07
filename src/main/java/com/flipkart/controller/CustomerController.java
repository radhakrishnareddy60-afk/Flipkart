package com.flipkart.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.dto.CustomerDto;
import com.flipkart.service.CustomerService;

@RequestMapping("/Customer/api")
@RestController
public class CustomerController {

	@Autowired
	CustomerService cs;
	
	   @PostMapping("/CreateCustomer")
	    public ResponseEntity<CustomerDto> createCust(@RequestBody CustomerDto request) {
	        
	        return cs.createCust(request);
	    }

	    @GetMapping("/GetAllCustomers")
	    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
	       
	        return cs.getAllCustomers();
	    }

	    @GetMapping("/GetCustomer/{id}")
	    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
	       
	    	return cs.getCustomerById(id);
	    }
		 
		  @PutMapping("/UpdateCustomer/{id}")
		    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto request) {
		     
			  return cs.updateCustomer(id, request);
		    }
		   
		   @DeleteMapping("/DeleteCustomer/{id}")
		   public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
		        return cs.deleteCustomerById(id);
		    }
		   

	

}
