package com.acme.service.validate;

import com.acme.model.models.Borrower;
import com.acme.model.models.MortgageApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.MinimumMortgageException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidateMortgageApplication {

    public static void CheckMortgageRequirements(MortgageApplication mortgageApplication) throws MinimumMortgageException, AgeOfMajorityException {
        if (mortgageApplication.getRequestedAmount().intValue() < 30000) {
            throw new MinimumMortgageException("Requested mortgage may not be below minimum of $30,000.");
        } else if (mortgageApplication.getRequestedAmount().intValue() == 0) {
            throw new MinimumMortgageException("Requested mortgage can't be 0 or null.");
        }
        if (mortgageApplication.getId() == null) {
            Set<? extends ConstraintViolation<?>> violationList = null;
            throw new ConstraintViolationException(violationList);
        }
        for (Borrower borrower : mortgageApplication.getBorrowers()) {
            if (borrower.getAge() == 0) {
                Set<? extends ConstraintViolation<?>> violationList = null;
                mortgageApplication = null;
                throw new ConstraintViolationException(violationList);
            } else if (borrower.getAge() < 18) {
                throw new AgeOfMajorityException("Borrower " + borrower.getFirstName() + " " + borrower.getLastName() + " too young to apply.");
            }
        }
        if (mortgageApplication.getId() == null) {
            Set<? extends ConstraintViolation<?>> list = null;
            mortgageApplication = null;
            throw new ConstraintViolationException(list);
        }
        checkIfCustomer(mortgageApplication.isCustomer());
    }

    public static void checkIfCustomer(boolean isCustomer
    ) {
        if (isCustomer == true) {
            System.out.println("Is a customer.");
        } else {
            System.out.println("Is not a customer.");
        }
    }

}
