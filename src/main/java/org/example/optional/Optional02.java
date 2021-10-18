package org.example.optional;

import org.example.optional.domain.Car;
import org.example.optional.domain.Insurance;
import org.example.optional.domain.Person;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional 적용
 */
public class Optional02 {
    public static void main(String[] args) {
        // 1) 기본적인 Optional 선언
        Optional<Car> optCar = Optional.empty();

        Car nullCar = null;
//        Optional<Car> optCar2 = Optional.of(nullCar);
//        System.out.println(optCar.get());   // Optional이라도 emtpy상태에서 get()을 진행하면 NoSuchElementException 발생
//        System.out.println(optCar2.get());  // Optional에 담긴 객체의 값이 null인 경우 NullPointerException 발생

        //2) Null값으로 Optional 선언하기
        // null이 존재하는 대상의 경우 Optional 선언 시 null이 존재한다고 명시해줘야한다.
        Optional<Car> nullableOptional = Optional.ofNullable(nullCar);

        // 3) Optional에서 값 가져오기
        //  1. get() : 값이 있는 경우 해당 값을 가져오지만, 없는 경우 NoSuchElementException을 발생
        Car getCar = optCar.get();
        //  2. orElse() : 값이 있는 경우 해당 값을, 없는 경우 orElse()에 명시된 값을 가져온다.
        Car orElseCar = optCar.orElse(new Car());
        //  3. orElseGet() : orElse와 동일하게 값이 있는 경우 해당 값을, 없는 경우 orElseGet()에 명시된 값을 가져오지만 차이점이 존재한다,
        // orElseGet()의 경우 인자가 Suppiler이고, 값이 없는 경우에만 실행된다.
        Car orElseGetCar = optCar.orElseGet(() -> new Car());
        // 4. orElseThrow() : Optional의 값이 없는 경우 예외를 발생시킨다.
        Car orElseThrowCar = optCar.orElseThrow(() -> new NoSuchElementException());
        // 5. ifPresent() : 값이 존재하는 경우 인자(Consumer)를 실행시킨다.
        optCar.ifPresent(car -> {
            String insuranceName = car.getInsurance().getName();
            System.out.println(insuranceName);
        });

        // 4) findCheapestInsurance 구현하기 (with Optional)
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        Insurance cheapestCompany = null;
        // 단순하게 Optional을 구현한다면
        //Insurance cheapestCompany = nullSafeFindCheapestInsurance(person, car);
        return cheapestCompany;
    }

    /*
        person, car만으로도 NPE를 방지할 수 있으나, 기존의 if문을 사용하여 null 체크하는 방법과 크게 다르지 않다.
        여러가지 값을 활용하는 법으로는 flatMap을 이용하여 간단한 코드를 구현할 수 있다.
     */
    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        }

        return Optional.empty();
    }
    /*
        flatMap & map을 활용하여 간결한 코드를 구현
     */
    public Insurance nullSafeFindCheapestInsuranceWithFlatMap(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)))
                        .orElseThrow(() -> new NoSuchElementException("No Element"));
    }

    /*
        filtering & optional 활용
        특정 사람의 보험명을 가져온다 (단 보험가입은 20세 이상만 할 수 있다고 가정)
     */
//    public String getCardInsuranceName(Optional<Person> person, int minAge) {
//        return person.filter(p -> p.getAge() >= minAge)
//                .flatMap(Person::getCar)
//                .flatMap(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse( "No Insurance");
//    }
}
