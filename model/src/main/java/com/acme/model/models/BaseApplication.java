/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.model.models;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tyler
 */
abstract class BaseApplication {

    @NotNull
    boolean customer;
    String id;
    @NotNull
    @Valid
    List<Borrower> borrowers;

    BaseApplication() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowerList(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }
}
