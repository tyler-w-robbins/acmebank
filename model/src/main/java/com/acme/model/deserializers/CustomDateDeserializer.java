package com.acme.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Locale;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

    private static final DateFormat DF = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        String date = jp.getText();
        if (date.equals("")) {
            return null;
        }
        try {
            Date dateFormatted = DF.parse(date);
            return new Timestamp(dateFormatted.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
