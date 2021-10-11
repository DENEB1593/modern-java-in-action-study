package study;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeFormatter
 * 날짜 객체를 형식에 맞게 문자열화 시켜준다.
 *
 */
public class StudyDate05 {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String formattedNow = now.format(formatter);
        System.out.printf("format : %s\n", formattedNow);
    }
}
