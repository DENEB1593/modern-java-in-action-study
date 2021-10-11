package study;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * 날짜 조정, 파싱, 포매팅
 * LoclDate의 날짜를 조정하고
 * 형식에 맞게 문자열을 반환한다
 */
public class StudyDate04 {
    public static void main(String[] args) {
        // 1) 날짜 조정 (절대 방식)
        LocalDate date1 = LocalDate.of(2012, 12, 25);
        LocalDate date2 = date1.withYear(2013);
        LocalDate date3 = date1.with(ChronoField.YEAR, 2014);

        System.out.printf("%s, %s\n", date2, date3);

        // 2) 날짜 조정 (상대 방식)
        LocalDate date4 =  LocalDate.of(2017, 3, 12);
        LocalDate date5 = date4.plusMonths(2);
        LocalDate date6 = date4.plusYears(4);

        System.out.printf("%s, %s\n", date5, date6);

        // 3) TemporalAdjusters 활용
        LocalDate now = LocalDate.now();
        // 다음 일요일 추론
        LocalDate nextSunday = now.with(nextOrSame(DayOfWeek.SUNDAY));
        // 이번달의 마지막 날짜 추촌
        LocalDate lastDayOfMonth = now.with(lastDayOfMonth());

        System.out.printf("NextSunday : %s\n", nextSunday);
        System.out.printf("LastDayOfMonth : %s\n", lastDayOfMonth);

    }
}
