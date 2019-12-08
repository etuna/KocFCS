package com.kocfcs.service.spec;

import com.kocfcs.model.*;
import com.kocfcs.model.request.CreditRequest;
import com.kocfcs.model.response.CreditResponse;

public interface ICreditService {
	public CreditResponse handleRequest(CreditRequest creditRequest);
}
