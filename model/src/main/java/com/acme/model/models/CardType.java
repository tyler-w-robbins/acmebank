/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.model.models;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 *
 * @author tyler
 */
public enum CardType {
    SKYMILES("skymiles"),
    GAS("gas"),
    HOTEL("hotel");

    private final String value;

    @JsonCreator
    public CardType fromValue(String value) {
        return CardType.valueOf(value);
    }

    @JsonValue
    public String toValue() {
        return value;
    }

    private CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
