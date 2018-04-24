package com.acme.service.validate;

import com.acme.model.models.MortgageApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.MinimumMortgageException;
import java.sql.SQLException;
import javax.validation.Valid;

public interface InitiateMortgageSubmission {

    MortgageApplication testMortgageBusinessLogic(@Valid MortgageApplication app) throws MinimumMortgageException, AgeOfMajorityException, SQLException;

    MortgageApplication getMortgageApplication(String id) throws SQLException;
}
