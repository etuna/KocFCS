package com.kocfcs.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kocfcs.dao.CustomerRepository;
import com.kocfcs.model.request.CreditRequest;
import com.kocfcs.model.response.CreditResponse;
import com.kocfcs.service.spec.ICreditService;

@Service
public class CreditService implements ICreditService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CreditResponse handleRequest(CreditRequest creditRequest) {
		return customerRepository.findById(username, password);
	}

}
