package com.kocfcs.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kocfcs.controller.CreditController;
import com.kocfcs.dao.CreditApplicationRepository;
import com.kocfcs.dao.CustomerRepository;
import com.kocfcs.model.CreditApplication;
import com.kocfcs.model.Customer;
import com.kocfcs.model.request.CreditRequest;
import com.kocfcs.model.response.CreditResponse;
import com.kocfcs.service.spec.ICreditService;
import com.kocfcs.util.Constant;

@Service
public class CreditService implements ICreditService {


	private static org.jboss.logging.Logger logger = LoggerFactory.logger(CreditService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CreditApplicationRepository creditApplicationRepository;

	@Override
	public CreditResponse handleRequest(CreditRequest creditRequest) {

		logger.info("Handling request...");
		Customer customer = customerRepository.findByIdNumber(creditRequest.getIdNumber());
		
		if(customer == null) {
			logger.info("No such customer...");
			return getCustomerNotFoundResponse();
		}
		
		logger.info("Customer found");
		if (customer.getCreditScore() <= 500) {
			processCreditRequestFail(creditRequest, customer, 0);
			return getFailResponse();
		} else if (customer.getCreditScore() >= 500 && customer.getCreditScore() <= 1000) {
			if(creditRequest.getMonthlyIncome() <= 5000) {
				processCreditRequestSuccess(creditRequest, customer, 10000);
				return getSuccessResponse(10000);				
			}else {
				// Else ??
				processCreditRequestFail(creditRequest, customer, 0);
				return getFailResponse();		
			}
		} else {
			int amount=creditRequest.getMonthlyIncome()*Constant.CREDIT_LIMIT_COEFFICIENT;
			processCreditRequestSuccess(creditRequest, customer, amount);
			return getSuccessResponse(amount);
		}

	}
	public CreditResponse getCustomerNotFoundResponse() {
		return new CreditResponse("FAIL", "Customer not found", 0);

	}

	public CreditResponse getFailResponse() {
		return new CreditResponse("FAIL", "Your credit request has not been approved.", 0);

	}

	public CreditResponse getSuccessResponse(int amount) {
		return new CreditResponse("SUCCESS", "Your credit request has been approved.", amount);
	}

	public boolean processCreditRequestSuccess(CreditRequest creditRequest, Customer customer, int credit) {
		try {

			CreditApplication creditApplication = new CreditApplication();
			UUID uuid = UUID.randomUUID();
			creditApplication.setId(uuid.toString());
			creditApplication.setCredit(credit);
			creditApplication.setCreditScore(customer.getCreditScore());
			creditApplication.setIdNumber(customer.getIdNumber());
			creditApplication.setMonthlyIncome(creditRequest.getMonthlyIncome());
			creditApplication.setMsisdn(creditRequest.getMsisdn());
			creditApplication.setName(creditRequest.getName());
			creditApplication.setSurname(creditRequest.getSurname());
			creditApplication.setRequestResult("SUCCESS");
			creditApplicationRepository.save(creditApplication);
			
			customer.setCredit(credit);
			customerRepository.save(customer);
			sendSMS(creditRequest.getMsisdn());

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean processCreditRequestFail(CreditRequest creditRequest, Customer customer, int credit) {
		try {
			CreditApplication creditApplication= new CreditApplication();
			UUID uuid = UUID.randomUUID();
			creditApplication.setId(uuid.toString());
			creditApplication.setCredit(credit);
			creditApplication.setCreditScore(customer.getCreditScore());
			creditApplication.setIdNumber(customer.getIdNumber());
			creditApplication.setMonthlyIncome(creditRequest.getMonthlyIncome());
			creditApplication.setMsisdn(creditRequest.getMsisdn());
			creditApplication.setName(creditRequest.getName());
			creditApplication.setSurname(creditRequest.getSurname());
			creditApplication.setRequestResult("FAIL");
			creditApplicationRepository.save(creditApplication);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean sendSMS(String msisdn) {
		/**
		 * sending sms...
		 */
		return true;
	}

}
