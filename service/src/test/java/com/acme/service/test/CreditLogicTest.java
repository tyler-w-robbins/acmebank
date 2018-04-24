///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.acme.service.test;
//
//import com.acme.model.models.CreditCardApplication;
//import com.acme.model.models.Borrower;
//import com.acme.model.models.CardType;
//import com.acme.model.models.Employer;
//import com.acme.model.models.MortgageApplication;
//import com.acme.service.exceps.AgeOfMajorityException;
//import com.acme.service.exceps.CreditLimitException;
//import com.acme.service.exceps.MinimumMortgageException;
//import com.acme.service.services.ServiceBean;
//import java.math.BigInteger;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Set;
//import javax.sql.DataSource;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import org.junit.Assert;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import static org.mockito.ArgumentMatchers.any;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.junit.MockitoJUnitRunner;
//
///**
// *
// * @author tyler
// */
//@RunWith(MockitoJUnitRunner.class)
//public class CreditLogicTest {
//
//    @Mock
//    private DataSource myDB;
//
//    @Mock
//    private Connection c;
////    
//    @Mock
//    private PreparedStatement stmt;
//
//    @Mock
//    private ResultSet rs;
//
//    private ValidatorFactory factory;
//    private Validator validator;
//    private CreditCardApplication creditApp;
//    private MortgageApplication mortgageApp;
//    private Borrower borr;
//    private Employer emp;
//    List<Borrower> borrowers;
//    List<Employer> employers;
//    ServiceBean sb;
//
//    @Before
//    public void setUp() throws ParseException, SQLException {
////        sb.SetConnection(myDB);
//        assertNotNull(myDB);
//        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
//        when(myDB.getConnection()).thenReturn(c);
//
//        creditApp = new CreditCardApplication();
//        mortgageApp = new MortgageApplication();
//        borr = new Borrower();
//        emp = new Employer();
//        borrowers = new ArrayList<>();
//        employers = new ArrayList<>();
//        final DateFormat DF = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
//        final Date tempDate = DF.parse("Jun 01, 2015");
//        emp.setStartDate(new Timestamp(tempDate.getTime()));
//        emp.setEndDate(null);
//        emp.setEmployerName("ABC Supply Co.");
//        emp.setEmployerPhone(new BigInteger("5555555555"));
//        employers.add(emp);
//
//        borr.setFirstName("Adam");
//        borr.setLastName("Smith");
//        borr.setAge(35);
//        borr.setAddress("123 Main St.");
//        borr.setCity("Concord");
//        borr.setState("NC");
//        borr.setZip(28027);
//        borr.setSsn(555555555);
//        borr.setRelationship("primary");
//        borr.setEmployment(employers);
//        borrowers.add(borr);
//
//        creditApp.setCardType(CardType.GAS);
//        creditApp.setRequestedCreditLimit(new BigInteger("5000"));
//        creditApp.setCustomer(true);
//        creditApp.setBorrowers(borrowers);
//
//        mortgageApp.setAddress("456 Main St.");
//        mortgageApp.setRequestedAmount(new BigInteger("45000"));
//        mortgageApp.setCustomer(true);
//        mortgageApp.setBorrowers(borrowers);
//
//        factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//        
//    }
//
//    @Test
//    public void checkAgeOfMajority() throws SQLException {
//        sb = new ServiceBean();
////        borrowers.forEach((borrower) -> {
////            borrower.setAge(19);
////        });
////        creditApp.setBorrowers(borrowers);
////
////        try {
////            sb.newCard(creditApp);
////        } catch (CreditLimitException ex) {
////        } catch (AgeOfMajorityException ex) {
////            Assert.fail();
////        }
//
//        borrowers.forEach((borrower) -> {
//            borrower.setAge(17);
//        });
//        creditApp.setBorrowers(borrowers);
//        mortgageApp.setBorrowers(borrowers);
//
//        sb = new ServiceBean();
//        boolean notAgeMaj = false;
//        try {
//            sb.newCard(creditApp);
//            sb.newMortgage(mortgageApp);
//        } catch (AgeOfMajorityException ex) {
//            notAgeMaj = true;
//        } catch (CreditLimitException | ConstraintViolationException | MinimumMortgageException ex) {
//        }
//        Assert.assertTrue(notAgeMaj);
//    }
//
//    @Test
//    public void checkCreditLimit() throws SQLException {
//        sb = new ServiceBean();
////        sb.SetConnection(myDB);
//        boolean aboveLimit = false;
//        creditApp.setRequestedCreditLimit(new BigInteger("15000"));
//        try {
//            sb.newCard(creditApp);
//        } catch (CreditLimitException ex) {
//            aboveLimit = true;
//        } catch (AgeOfMajorityException ex) {
//        }
//        Assert.assertTrue(aboveLimit);
//    }
//
//    @Test
//    public void checkMortgageMinimum() throws SQLException {
//        sb = new ServiceBean();
//        boolean belowMinimum = false;
//        mortgageApp.setRequestedAmount(new BigInteger("10000"));
//        try {
//            sb.newMortgage(mortgageApp);
//        } catch (MinimumMortgageException ex) {
//            belowMinimum = true;
//        } catch (AgeOfMajorityException ex) {
//        }
//        Assert.assertTrue(belowMinimum);
//    }
//
//    @Test
//    public void noNullFields() throws SQLException {
//        sb = new ServiceBean();
//        Set<ConstraintViolation<CreditCardApplication>> creditViolations = null;
//        Set<ConstraintViolation<MortgageApplication>> mortgageViolations = null;
//
//        try {
//            creditViolations = validator.validate(sb.newCard(creditApp));
//            for (ConstraintViolation<CreditCardApplication> violation : creditViolations) {
//                System.out.println(violation);
//            }
//            mortgageViolations = validator.validate(sb.newMortgage(mortgageApp));
//            for (ConstraintViolation<MortgageApplication> violation : mortgageViolations) {
//                System.out.println(violation);
//            }
//
//        } catch (CreditLimitException | AgeOfMajorityException | MinimumMortgageException ex) {
////            Logger.getLogger(ServiceBeanTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        int totalViolations = creditViolations.size() + mortgageViolations.size();
//        Assert.assertEquals(0, totalViolations);
//    }
//
//}
