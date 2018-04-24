package com.acme.service.gets;

import com.acme.model.models.Borrower;
import com.acme.model.models.CardType;
import com.acme.model.models.CreditApplication;
import com.acme.model.models.Employer;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCreditApplicationFromDatabase {

    public static CreditApplication getCardApplication(String id, Connection connection
    ) throws SQLException {

        try (PreparedStatement selectStatement
                = connection.prepareStatement("SELECT application.id, application.customer, application.requestedcreditlimit, application.cardtype,"
                        + "borrower.firstName, borrower.lastName, borrower.age, borrower.address, borrower.city, "
                        + "borrower.state, borrower.zip, borrower.ssn, borrower.relationship, "
                        + "employer.startDate, employer.endDate, employer.employerName, employer.employerPhone "
                        + "FROM CreditCardApplication application INNER JOIN Borrower borrower ON application.id=borrower.creditid "
                        + "INNER JOIN Employer employer ON borrower.borrowerid=employer.borrowerid "
                        + "WHERE application.id=?;")) {
            
            selectStatement.setString(1, id);
            selectStatement.execute();
            
            ResultSet resultSet = selectStatement.getResultSet();

            resultSet.next();
            
            CreditApplication creditApplication = new CreditApplication();
            creditApplication.setId(resultSet.getString("id"));
            creditApplication.setCustomer(resultSet.getBoolean("customer"));
            creditApplication.setRequestedCreditLimit(BigInteger.valueOf(resultSet.getLong("requestedcreditlimit")));
            creditApplication.setCardType(CardType.valueOf(resultSet.getString("cardtype")));

            Borrower borrower;
            Employer employer;
            List<Borrower> borrowerList = new ArrayList<>();
            List<Employer> employerList = new ArrayList<>();
            
            borrower = GetBorrowerResultSet.borrowerResultSet(resultSet);
            employer = GetEmployerResultSet.employerResultSet(resultSet);
            employerList.add(employer);
            borrowerList.add(borrower);

            while (resultSet.next()) {
                borrower = GetBorrowerResultSet.borrowerResultSet(resultSet);
                employer = GetEmployerResultSet.employerResultSet(resultSet);
                employerList.add(employer);
                borrowerList.add(borrower);
            }

            borrower.setEmployerList(employerList);
            creditApplication.setBorrowerList(borrowerList);

            return creditApplication;

        } catch (SQLException e) {
            Logger logger = LoggerFactory.getLogger(GetCreditApplicationFromDatabase.class);
            logger.error(e.getMessage());
            return null;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
