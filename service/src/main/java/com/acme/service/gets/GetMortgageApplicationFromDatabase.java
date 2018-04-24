package com.acme.service.gets;

import com.acme.model.models.Borrower;
import com.acme.model.models.Employer;
import com.acme.model.models.MortgageApplication;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetMortgageApplicationFromDatabase extends GetBorrowerResultSet {

    public static MortgageApplication getMortgageApplication(String id, Connection connection) throws SQLException {

        try (PreparedStatement selectStatement
                = connection.prepareStatement("SELECT application.id, application.customer, application.requestedamount, application.address,"
                        + "borrower.firstName, borrower.lastName, borrower.age, borrower.address, borrower.city, "
                        + "borrower.state, borrower.zip, borrower.ssn, borrower.relationship, "
                        + "employer.startDate, employer.endDate, employer.employerName, employer.employerPhone "
                        + "FROM MortgageApplication application INNER JOIN Borrower borrower ON application.id=borrower.mortgageid "
                        + "INNER JOIN Employer employer ON borrower.borrowerid=employer.borrowerid "
                        + "WHERE application.id=?;")) {
            selectStatement.setString(1, id);
            selectStatement.execute();

            ResultSet resultSet = selectStatement.getResultSet();

            resultSet.next();

            MortgageApplication mortgageApplication = new MortgageApplication();
            mortgageApplication.setId(resultSet.getString("id"));
            mortgageApplication.setCustomer(resultSet.getBoolean("customer"));
            mortgageApplication.setRequestedAmount(BigInteger.valueOf(resultSet.getLong("requestedamount")));
            mortgageApplication.setAddress(resultSet.getString("address"));

            Borrower borrower;
            Employer employer;
            List<Borrower> borrowerList = new ArrayList<>();
            List<Employer> employerList = new ArrayList<>();

            borrower = borrowerResultSet(resultSet);
            employer = GetEmployerResultSet.employerResultSet(resultSet);
            employerList.add(employer);
            borrowerList.add(borrower);

            while (resultSet.next()) {
                borrower = borrowerResultSet(resultSet);
                employer = GetEmployerResultSet.employerResultSet(resultSet);
                employerList.add(employer);
                borrowerList.add(borrower);
            }
            
            borrower.setEmployerList(employerList);
            mortgageApplication.setBorrowerList(borrowerList);
            
            return mortgageApplication;
        } catch (SQLException e) {
            Logger logger = LoggerFactory.getLogger(GetMortgageApplicationFromDatabase.class);
            logger.error(e.getMessage());
            return null;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
