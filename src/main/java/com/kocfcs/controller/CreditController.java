package com.kocfcs.controller;

import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.kocfcs.model.request.CreditRequest;
import com.kocfcs.model.response.CreditResponse;
import com.kocfcs.service.impl.CreditService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("credit")
public class CreditController {

	@Autowired
	private CreditService creditService;
	
	private static org.jboss.logging.Logger logger = LoggerFactory.logger(CreditController.class);
	
	@PostMapping("request-credit")
	public CreditResponse requestCredit(@RequestBody CreditRequest creditRequest) {
		logger.info("New Request arrived");
		return creditService.handleRequest(creditRequest);
	}
}
