/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.model.models;

import java.math.BigInteger;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tyler
 */
public class CreditApplication extends BaseApplication {

    private CardType cardType;

    @NotNull
    private BigInteger requestedCreditLimit;

    public BigInteger getRequestedCreditLimit() {
        return requestedCreditLimit;
    }

    public void setRequestedCreditLimit(BigInteger requestedCreditLimit) {
        this.requestedCreditLimit = requestedCreditLimit;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
