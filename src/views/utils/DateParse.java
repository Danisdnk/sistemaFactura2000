package views.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParse {
    public static LocalDate parse(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/uuuu");

            return LocalDate.parse(date,formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
