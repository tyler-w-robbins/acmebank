package com.acme.service.inserts;

import com.acme.model.models.MortgageApplication;
import static com.acme.service.inserts.InsertBorrowerObjects.insertBorrowerAndEmployerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertMortgageApplicationToDatabase {

    public static MortgageApplication newMortgage(@Valid MortgageApplication mortgageApplication, DataSource myDB) throws SQLException {
        try (Connection dbConnection = myDB.getConnection();
                PreparedStatement insertMortgage = 
                        dbConnection.prepareStatement("INSERT INTO MortgageApplication "
                                + "VALUES (?,?,?,?);");) {
            insertMortgage.setString(1, mortgageApplication.getId());
            insertMortgage.setBoolean(2, mortgageApplication.isCustomer());
            insertMortgage.setLong(3, mortgageApplication.getRequestedAmount().longValue());
            insertMortgage.setString(4, mortgageApplication.getAddress());
            insertMortgage.execute();

            mortgageApplication.getBorrowers().forEach((borrower) -> {
                insertBorrowerAndEmployerInfo(borrower, mortgageApplication.getId(), MortgageApplication.class.getName(), dbConnection);
            });
        } catch (SQLException ex) {
            Logger logger = (Logger) LoggerFactory.getLogger(InsertMortgageApplicationToDatabase.class);
            logger.error("Problem inserting Employer into Database. ", ex);
        }
        return mortgageApplication;
    }
}
