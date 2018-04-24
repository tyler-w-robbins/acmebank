/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author tyler
 */
public class CustomPhoneDeserializer extends JsonDeserializer<BigInteger>{

    @Override
    public BigInteger deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        String phone = jp.getText();
        return BigInteger.valueOf(Long.parseLong(phone.replaceAll("\\D+", "")));
    }

    
}
