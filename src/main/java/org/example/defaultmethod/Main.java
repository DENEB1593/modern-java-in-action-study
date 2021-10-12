package org.example.defaultmethod;

/**
 * default method
 * java 1.8 이후 추가된 interface 내에서 사용하는 키워드
 * 기존에 기능명칭만 정의하던 interface 에서 default 키워드는 기능의 내용까지 구현할 수 있다
 */
public class Main {
    public static void main(String[] args) {
        /**
         * default를 사용하는 목적은 interface 내 기능을 추가하는 경우
         * 아래와 같은 이점을 얻는다.
         *  1) 해당 interface를 구현(implements)한 클래스들에 미치는 영향 최소화
         *  2) interface 내 기능 구현
         *
         *  아래와 같이 RemoteControl을 구현한 MyRemoteControl, YourRemoteControl이 존재한다
         */
        RemoteControl myRemoteControl = new MyRemoteControl();
        RemoteControl yourRemoteControl = new YourRemoteControl();
        myRemoteControl.selectMode();
        yourRemoteControl.selectMode();
        /**
         * 이용자들이 구현된 RemoteControl을 사용하고 있는 상황에서
         * RemoteControl에 TurnOff, TurnOn 기능을 추가해야하는 상황이 발생하였다.
         * (RemoteControl 참고)
         */

        myRemoteControl.turnOff();
        myRemoteControl.turnOn();   // default 메소드도 Override가 가능하다
        yourRemoteControl.turnOn();

        /**
         * default 메소드를 통해 기존 구현체에 영향을 주지 않고 interface에 기능 추가가 가능하며,
         * 재정의가 필요한 경우 default 메소드를 재정의 할 수 있기 때문에
         * 코드의 안정성이 높아진다
         */
    }
}
