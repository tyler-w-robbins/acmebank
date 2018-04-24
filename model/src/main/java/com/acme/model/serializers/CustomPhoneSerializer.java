package com.acme.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigInteger;

public class CustomPhoneSerializer extends JsonSerializer<BigInteger> {

    @Override
    public void serialize(BigInteger phone, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {

        jg.writeString(String.valueOf(phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
    }

}
