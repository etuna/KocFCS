package com.kocfcs.controller;

import java.net.Authenticator.RequestorType;
import java.util.List;

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
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("request-credit")
	public CreditResponse requestCredit(@RequestBody CreditRequest creditRequest) {
		return creditService.handleRequest(creditRequest);
	}
}
