package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.Period;

// Metode, som viser aktuelle dato:
public class DateUtils {
    public static void getCurrentDate() {
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        System.out.println(formattedDate);
    }

    //Metoden beregner alder, ud fra ens fødselsdagsdato og den aktuelle dato.
    public static int calculateAge(LocalDate birthDate) {
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }
}