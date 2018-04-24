/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.service.exceps;

/**
 *
 * @author tyler
 */
public class CreditLimitException extends Exception {

    public CreditLimitException(String message) {
        super(message);
    }
}
