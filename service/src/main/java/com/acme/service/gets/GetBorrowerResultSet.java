package com.acme.service.gets;

import com.acme.model.models.Borrower;
import java.sql.ResultSet;
import java.sql.SQLException;

class GetBorrowerResultSet {

    public static Borrower borrowerResultSet(ResultSet resultSet) throws SQLException {
        Borrower borrower = new Borrower();
        borrower.setFirstName(resultSet.getString("firstname"));
        borrower.setLastName(resultSet.getString("lastname"));
        borrower.setAge(resultSet.getInt("age"));
        borrower.setAddress(resultSet.getString("address"));
        borrower.setCity(resultSet.getString("city"));
        borrower.setState(resultSet.getString("state"));
        borrower.setZip(resultSet.getInt("zip"));
        borrower.setSsn(resultSet.getInt("ssn"));
        borrower.setRelationship(resultSet.getString("relationship"));
        return borrower;
    }

}
