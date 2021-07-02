package views.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParse {
    public static LocalDate parse(String date) {
        try {
            var datecomps = date.split("/");

            date = datecomps[2] + "-" + datecomps[1] + "-" + datecomps[0];

            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public static String unparse(LocalDate date) {
        try {
            return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
