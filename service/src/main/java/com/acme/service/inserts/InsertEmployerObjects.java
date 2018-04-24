/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.service.inserts;

import com.acme.model.models.Borrower;
import com.acme.model.models.Employer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tyler
 */
public class InsertEmployerObjects {

    public static void insertEmployerObject(Borrower borrower, int borrowerId, PreparedStatement insertEmployer) throws SQLException {
        for (Employer employer : borrower.getEmployment()) {
            insertEmployer.setTimestamp(1, employer.getStartDate());
            insertEmployer.setTimestamp(2, employer.getEndDate());
            insertEmployer.setString(3, employer.getEmployerName());
            insertEmployer.setLong(4, employer.getEmployerPhone().longValue());
            insertEmployer.setInt(5, borrowerId);
            int affectedRows = insertEmployer.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating employer failed, no rows affected.");
            }

            try (ResultSet generatedEmployerKey = insertEmployer.getGeneratedKeys()) {
                if (generatedEmployerKey.next()) {
                    employer.setEmployerId(generatedEmployerKey.getInt("employerId"));
                } else {
                    throw new SQLException("Creating employer failed, no ID obtained.");
                }
            }
        }
    }
}
