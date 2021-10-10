package study;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class StudyData02 {
    public static void main(String[] args) {
        // 자바 1.8 이후 기존의 Date 클래스를 대체할 LocalDate, LocalDateTime, Instant, Duration, Period가 제공

        //1) LocalDate, LocalDateTime
        // LocalDate의 경우 시간대 정보가 포함되지 않는다
        // 인스턴스 생성 방법
        LocalDate localDate = LocalDate.of(2021, 10, 10);
        LocalDate localDate2 = LocalDate.of(2021, Month.OCTOBER, 10);
        System.out.printf("localDate: %s\n", localDate);
        System.out.printf("localDate2: %s\n\n", localDate2);

        // 생성된 인스턴스로 부터 날짜 정보 추출
        int year = localDate.getYear();
        Month month = localDate.getMonth(); //Month 객체의 경우 필드명으로 추출되며, 월의 값을 얻고자 하는 경우 getValue 사용
        int day = localDate.getDayOfMonth();
        System.out.printf("%d %d %d\n", year, month.getValue(), day);

        // 생성된 인스턴스로 부터 날짜 정보 추출 (with TemporalField)
        int year2 = localDate.get(ChronoField.YEAR);
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day2 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.printf("%d %d %d\n\n", year2, month2, day2);

        // LocalTime (hour의 경우 0 ~ 23, minute, second의 경우: 0 ~ 59,
        LocalTime time = LocalTime.of(22, 10, 48);
        System.out.println(time);
        int hour = time.get(ChronoField.HOUR_OF_DAY);
        int minute = time.get(ChronoField.MINUTE_OF_HOUR);
        int second = time.get(ChronoField.SECOND_OF_MINUTE);
        System.out.printf("%d %d %d\n\n", hour, minute, second);

        // LocalDateTime (LocalDate + LocalTime이 혼합된 클래스)
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.toLocalDate());    // LocalDateTime -> LocalDate
        System.out.println(localDateTime.toLocalTime());    // LocalDateTime -> LocalTime
    }
}
