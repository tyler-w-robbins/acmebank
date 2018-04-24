package com.acme.service.validate;

import com.acme.model.models.MortgageApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.MinimumMortgageException;
import com.acme.service.gets.GetMortgageApplicationFromDatabase;
import com.acme.service.inserts.InsertMortgageApplicationToDatabase;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertAndGetMortgage implements InitiateMortgageSubmission {

    @Resource(lookup = "java:jboss/postgresXADS")
    javax.sql.DataSource myDB;

    @Override
    public MortgageApplication testMortgageBusinessLogic(@Valid MortgageApplication application) throws MinimumMortgageException, AgeOfMajorityException, SQLException {
        try {
            ValidateMortgageApplication.CheckMortgageRequirements(application);
            return InsertMortgageApplicationToDatabase.newMortgage(application, myDB);
        } catch (SQLException | MinimumMortgageException | AgeOfMajorityException e) {
            Logger logger = LoggerFactory.getLogger(ValidateCreditApplication.class);
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public MortgageApplication getMortgageApplication(String id) throws SQLException {
        MortgageApplication app;
        app = GetMortgageApplicationFromDatabase.getMortgageApplication(id, myDB.getConnection());
        return app;
    }

}
