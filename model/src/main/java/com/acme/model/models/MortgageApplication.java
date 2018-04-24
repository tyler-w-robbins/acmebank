package com.acme.model.models;

import java.math.BigInteger;
import javax.validation.constraints.NotNull;

public class MortgageApplication extends BaseApplication {

    @NotNull
    private BigInteger requestedAmount;
    @NotNull
    private String address;

    public BigInteger getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigInteger requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
