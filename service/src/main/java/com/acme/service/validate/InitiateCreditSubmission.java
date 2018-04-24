package com.acme.service.validate;

import com.acme.model.models.CreditApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.CreditLimitException;
import java.sql.SQLException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

public interface InitiateCreditSubmission {

    CreditApplication insertCreditApplication(@Valid CreditApplication app) throws CreditLimitException, AgeOfMajorityException, ConstraintViolationException, SQLException;

    CreditApplication getCreditApplication(String id) throws SQLException;
}
