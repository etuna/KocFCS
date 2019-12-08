package com.kocfcs.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kocfcs.dao.CustomerRepository;
import com.kocfcs.model.Customer;
import com.kocfcs.model.request.CreditRequest;
import com.kocfcs.model.response.CreditResponse;
import com.kocfcs.service.spec.ICreditService;
import com.kocfcs.util.Constant;

@Service
public class CreditService implements ICreditService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CreditResponse handleRequest(CreditRequest creditRequest) {

		Customer customer = customerRepository.findByIdNumber(creditRequest.getIdNumber());

		if (customer.getCreditScore() <= 500) {
			return getFailResponse();
		} else if (customer.getCreditScore() >= 500 && customer.getCreditScore() <= 1000) {
			if(creditRequest.getMonthlyIncome() <= 5000) {
				processCreditRequest(customer, 10000);
				return getSuccessResponse();				
			}else {
				// Else ??
				return getFailResponse();		
			}
		} else {
			processCreditRequest(customer, creditRequest.getMonthlyIncome()*Constant.CREDIT_LIMIT_COEFFICIENT);
			return getSuccessResponse();
		}

	}

	public CreditResponse getFailResponse() {
		return new CreditResponse("FAIL", "Your credit request has not been approved.");

	}

	public CreditResponse getSuccessResponse() {
		return new CreditResponse("SUCCESS", "Your credit request has been approved.");
	}

	public boolean processCreditRequest(Customer customer, int credit) {
		try {
			customer.setCredit(credit);
			customerRepository.save(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
