package org.example.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudyDate01 {
    public static void main(String[] args) {
        // 자바 1.0부터 사용되어진 날짜 클래스 Data
        Date date = new Date(2012, 01, 01);
        // year의 경우 1900년을 기준으로 실행되기 때문 3912년으로 출력됨
        System.out.println(date);   //Thu Feb 01 00:00:00 KST 3912


        // DateTimeFormat
        // Date 객체의 형식 문제를 보완하기 위해 고안되었으나, 쓰레드 문제와 Date 객체 기반의 한계성을 벗어나지 못함
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateFormatted = dateFormat.format(date);
        System.out.println(dateFormatted);
    }
}
