package views.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParse {
    public static LocalDate parse(String date) {
        try {
            var datecomps = date.split("/");

            date = addCeros(4, datecomps[2]) + "-" + addCeros(2, datecomps[1]) + "-" + addCeros(2, datecomps[0]);

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

    private static String addCeros(int digitos, String f) {
        return "0".repeat(digitos-f.length()) + f;
    }
}
