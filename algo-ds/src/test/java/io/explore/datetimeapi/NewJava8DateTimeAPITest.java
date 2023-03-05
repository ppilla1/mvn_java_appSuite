package io.explore.datetimeapi;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

@Log4j2
public class NewJava8DateTimeAPITest {

    @Test
    public void localDate() {
        LocalDate date1 = LocalDate.now();
        log.info("Now : {}", date1.toString());

        LocalDate date2 = LocalDate.of(2022, Month.FEBRUARY, 22);
        log.info("{} comes before {} : {}", date2, date1, date2.isBefore(date1));

        log.info("This year is leap year {}, current month length {}", date1.isLeapYear(), date1.lengthOfMonth());
    }

    @Test
    public void localDate_manipulations(){

    }
}
