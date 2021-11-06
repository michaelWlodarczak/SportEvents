package sportEvents.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidator {

    public static boolean isValidDateTime(final String date) {

        boolean valid = false;

        try {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern("uuuu-M-d")
                    .withResolverStyle(ResolverStyle.STRICT)); // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.

            valid = true;

        }catch (DateTimeParseException e){
            e.printStackTrace();
            valid = false;
        }
        return valid;
    }
}