package com.acme.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class CustomSSNSerializer extends JsonSerializer<Integer>{

    @Override
    public void serialize(Integer ssn, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String ssnString=String.valueOf(ssn).replaceFirst("(\\d{3})(\\d{2})(\\d+)", "$1-$2-$3");
        jg.writeString(ssnString);
    }
    
}
