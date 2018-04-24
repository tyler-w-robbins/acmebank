package com.acme.service.inserts;

import com.acme.model.models.CreditApplication;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.CreditLimitException;
import static com.acme.service.inserts.InsertBorrowerObjects.insertBorrowerAndEmployerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertCreditApplicationToDatabase {

    public static CreditApplication newCard(@Valid CreditApplication creditApplication, DataSource postgresDB) throws CreditLimitException, AgeOfMajorityException, ConstraintViolationException, SQLException {

        try (Connection dbConnection = postgresDB.getConnection();
                PreparedStatement insertCredit
                = dbConnection.prepareStatement("INSERT INTO CreditCardApplication"
                        + " VALUES (?,?,?,?);");) {
            insertCredit.setString(1, creditApplication.getId());
            insertCredit.setBoolean(2, creditApplication.isCustomer());
            insertCredit.setString(3, creditApplication.getCardType().toString());
            insertCredit.setLong(4, creditApplication.getRequestedCreditLimit().longValue());
            insertCredit.execute();
            creditApplication.getBorrowers().forEach((borrower) -> {
                insertBorrowerAndEmployerInfo(borrower, creditApplication.getId(), CreditApplication.class.getName(), dbConnection);
            });

        } catch (SQLException e) {
            Logger logger = LoggerFactory.getLogger(InsertCreditApplicationToDatabase.class);
            logger.error("Problem inserting Credit Application to database. ", e);
        }
        return creditApplication;
    }
}
