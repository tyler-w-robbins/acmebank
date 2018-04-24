package com.acme.service.inserts;

import com.acme.model.models.Borrower;
import com.acme.model.models.MortgageApplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InsertBorrowerObjects {

    public static void insertBorrowerAndEmployerInfo(Borrower borrower, String id, String applicationType, Connection connection) {

        try (PreparedStatement insertBorrower = connection.prepareStatement("INSERT INTO Borrower VALUES (?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);) {

            if (applicationType.equals(MortgageApplication.class.getName())) {
                insertBorrower.setString(1, id);
                insertBorrower.setNull(2, 0);
            } else {
                insertBorrower.setNull(1, 0);
                insertBorrower.setString(2, id);
            }
            insertBorrower.setString(3, borrower.getFirstName());
            insertBorrower.setString(4, borrower.getLastName());
            insertBorrower.setInt(5, borrower.getAge());
            insertBorrower.setString(6, borrower.getAddress());
            insertBorrower.setString(7, borrower.getCity());
            insertBorrower.setString(8, borrower.getState());
            insertBorrower.setInt(9, borrower.getZip());
            insertBorrower.setInt(10, borrower.getSsn());
            insertBorrower.setString(11, borrower.getRelationship());
            int affectedRows = insertBorrower.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating borrower failed, no rows affected.");
            }

            try (ResultSet generatedBorrowerKey = insertBorrower.getGeneratedKeys();
                    PreparedStatement insertEmployer = connection.prepareStatement("INSERT INTO Employer VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);) {
                if (generatedBorrowerKey.next()) {
                    int borrowerId = generatedBorrowerKey.getInt("borrowerId");
                    borrower.setBorrowerId(borrowerId);
                    InsertEmployerObjects.insertEmployerObject(borrower, borrowerId, insertEmployer);
                } else {
                    throw new SQLException("Creating borrower failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            Logger logger = LoggerFactory.getLogger(InsertBorrowerObjects.class);
            logger.error("Problem inserting Employer into Database. ", ex);

        }
    }
}
