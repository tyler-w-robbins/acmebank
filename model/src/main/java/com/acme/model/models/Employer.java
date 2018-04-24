/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.model.models;

import com.acme.model.deserializers.CustomPhoneDeserializer;
import com.acme.model.deserializers.CustomDateDeserializer;
import com.acme.model.serializers.CustomDateSerializer;
import com.acme.model.serializers.CustomPhoneSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tyler
 */
public class Employer {

    private int employerId;

    @NotNull
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Timestamp startDate;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Timestamp endDate;

    @NotNull
    private String employerName;

    @NotNull
    @JsonDeserialize(using = CustomPhoneDeserializer.class)
    @JsonSerialize(using = CustomPhoneSerializer.class)
    private BigInteger employerPhone;

    public int getEmpoyerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public BigInteger getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(BigInteger employerPhone) {
        this.employerPhone = employerPhone;
    }
}
