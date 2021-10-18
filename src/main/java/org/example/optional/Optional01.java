package org.example.optional;

import org.example.optional.domain.Car;
import org.example.optional.domain.Insurance;
import org.example.optional.domain.Person;

/*
    Java 1.8 Optional
    자바를 개발함에 있어서 NullPointerException은 가장 많이 접하는 예외이다.
    null은 1965년 토니 호어라는 과학자가 값이 없는 값을 표현하기 위해 null이라는 개념을 도입한 것이 시초다.
    자바를 개발함에 있어 null을 처리함에 있어 불편한 점들은 아래와 같다.

    1) 에러의 근원이다 : NullPointerException은 자바에서 가장 흔히 발생하는 에러다
    2) 코드드를 어지럽힌다 : null 확인 코드를 추가해야 하므로 과도한 체크 로직으로 가독성이 떨어진다.
    3) 아무 의미가 없다 : 정적 형식 언어에서 값이 없음을 표현하는 방법으로는 적절하지 않다.
    4) 자바 철학에 위배된다 : 애초에 자바는 포인터를 사용하지 않는다. 예외적으로 NPE만 존재한다.
    5) 형식 시스템에 구멍을 만든다 : null은 형식(String, Class<T> emd)에 상관없이 할당가능하기 때문에 null을 할당한 경우
     해당 형식이 표현하고자 하는 표현을 알 수가 없다.

    이에 자바에서는 형식에 값이 존재하지 않는지 판별하기 위해 Optional을 개발하였다.
 */
public class Optional01 {
    public static void main(String[] args) {
        Person peron = new Person();
        // 현재 메소드는 null에 취약
        String carInsuranceName = getCarInsuranceName(peron);

    }

    public static String getCarInsuranceName(Person person) {
        /*
            1) return this.getCar().getInsurance().getName();
            차가 없는 사람이 존재하는 경우 NPE가 발생!
         */

        /*
            2) 보수적으로 if문을 사용하는 경우 코드가 복잡하고 가독성도 좋지 않다
         */
//        if (person != null) {
//            Car car = person.getCar();
//            if (car != null) {
//                Insurance insurance = car.getInsurance();
//                if (insurance != null) {
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unknown";

        /*
            3) if 블록을 1단계로 통일한 코드, 가독성은 좋아졌지만
               Unknown을 return 하는 부분이 많아 Unknown을 변경하는 경우 작업이 증가한다.
         */
        if (person != null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car != null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance != null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
