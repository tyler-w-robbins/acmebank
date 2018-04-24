package com.acme.service.validate;

import com.acme.model.models.CreditApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.CreditLimitException;
import com.acme.service.gets.GetCreditApplicationFromDatabase;
import com.acme.service.inserts.InsertCreditApplicationToDatabase;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertAndGetCredit implements InitiateCreditSubmission {

    @Resource(lookup = "java:jboss/postgresXADS")
    javax.sql.DataSource myDB;

    @Override
    public CreditApplication insertCreditApplication(@Valid CreditApplication creditApplication) throws SQLException, AgeOfMajorityException, CreditLimitException {
        
        try {
            ValidateCreditApplication.CheckCreditRequirements(creditApplication);
            InsertCreditApplicationToDatabase.newCard(creditApplication, myDB);
            return creditApplication;
        } catch (SQLException | AgeOfMajorityException | CreditLimitException e) {
            Logger logger = LoggerFactory.getLogger(InsertAndGetCredit.class);
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public CreditApplication getCreditApplication(String id) throws SQLException {
        System.out.println("help");
        try {
            CreditApplication creditApplication
                    = GetCreditApplicationFromDatabase.getCardApplication(id, myDB.getConnection());
            return creditApplication;
        } catch (SQLException e) {
            Logger logger = LoggerFactory.getLogger(InsertAndGetCredit.class);
            logger.error(e.getMessage());
            return null;
        }

    }
}
