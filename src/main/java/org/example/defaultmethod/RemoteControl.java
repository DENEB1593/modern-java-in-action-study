package org.example.defaultmethod;

public interface RemoteControl {
    public void selectMode();
    public void channelUp();
    public void channelDown();

    // 기존의 방식으로 함수 추가 시 MyRemoteControl, YourRemoteControl에 영향을 줌
//    public void turnOn();
//    public void turnOff();
    default void turnOn() { System.out.println("turn on"); }
    default void turnOff() { System.out.println("turn off"); }
}
