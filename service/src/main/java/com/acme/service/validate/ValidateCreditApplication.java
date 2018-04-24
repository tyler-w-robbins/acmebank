package com.acme.service.validate;

import com.acme.model.models.Borrower;
import com.acme.model.models.CreditApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.CreditLimitException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidateCreditApplication {

    public static void CheckCreditRequirements(CreditApplication application) throws AgeOfMajorityException, CreditLimitException {
        if (application.getRequestedCreditLimit().intValue() > 10000) {
            throw new CreditLimitException("Requested credit above limit of $10,000.");
        } else if (application.getRequestedCreditLimit().intValue() == 0) {
            throw new CreditLimitException("Requested credit can't be 0 or null.");
        }
        for (Borrower borrower : application.getBorrowers()) {
            if (borrower.getAge() == 0) {
                Set<? extends ConstraintViolation<?>> list = null;
                application = null;
                throw new ConstraintViolationException(list);
            } else if (borrower.getAge() < 18) {
                throw new AgeOfMajorityException("Borrower " + borrower.getFirstName() + " " + borrower.getLastName() + " too young to apply.");
            }
        }
        if (application.getId() == null) {
            Set<? extends ConstraintViolation<?>> violationList = null;
            throw new ConstraintViolationException(violationList);
        }
        checkIfCustomer(application.isCustomer());
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
