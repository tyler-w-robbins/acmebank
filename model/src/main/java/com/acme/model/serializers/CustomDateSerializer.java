package com.acme.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Locale;

public class CustomDateSerializer extends JsonSerializer<Timestamp> {
    
    private static final DateFormat DF = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    
    @Override
    public void serialize(Timestamp t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        if (t != null) {
            String dateString = DF.format(t);
            jg.writeString(dateString);
        } else {
            jg.writeString("");
        }
    }
    
}
