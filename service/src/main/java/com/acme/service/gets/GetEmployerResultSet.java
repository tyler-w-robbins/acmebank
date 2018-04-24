package com.acme.service.gets;

import com.acme.model.models.Employer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetEmployerResultSet {

    public static Employer employerResultSet(ResultSet resultSet) throws SQLException {
        Employer employer = new Employer();
        employer.setStartDate(resultSet.getTimestamp("startdate"));
        employer.setEndDate(resultSet.getTimestamp("enddate"));
        employer.setEmployerName(resultSet.getString("employername"));
        employer.setEmployerPhone(BigInteger.valueOf(resultSet.getLong("employerphone")));
        return employer;
    }

}
