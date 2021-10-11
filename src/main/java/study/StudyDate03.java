package study;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/**
 * Duration, Period 클래스
 * Duration은 두 LocalTime, Instant 객체의 지속시간을 표현하는데 사용하며,
 * 초(sec), 나노초(nsec)으로 간격을 표현한다.
 *
 * Period는 LocalDate의 간격을 구하기 위해 사용한다
 * 표현은 년(year), 월(month), 일(day), 시(hour)
 *
 * Duration, Period 차이점?
 * > https://docs.oracle.com/javase/tutorial/datetime/iso/period.html
 */
public class StudyDate03 {
    public static void main(String[] args) {
        // Duration
        LocalTime from = LocalTime.of(8, 0);
        LocalTime to = LocalTime.now();
        Duration duration = Duration.between(from, to);
        long diff = duration.getSeconds();
        System.out.printf("between : %d sec\n", diff);

        // Period
        LocalDate dateFrom = LocalDate.of(2010, 10, 9);
        LocalDate dateTo = LocalDate.of(2010, 10, 19);
        Period period = Period.between(dateFrom, dateTo);
        long between = period.getDays();
        System.out.printf("date between : %d days\n", between);

        // Period를 활용하여, 년/월/일의 단위를 Days로 자유롭게 변환 가능
        Period twoWeeks = Period.ofWeeks(2);
        System.out.printf("Days of 2 Month : %d\n", twoWeeks.getDays());
    }
}
