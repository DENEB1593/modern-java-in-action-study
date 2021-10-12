package org.example.defaultmethod;

public class MyRemoteControl implements RemoteControl {

    private static final String CONTROL_NAME ="MyRemoteControl";

    @Override
    public void selectMode() {
        System.out.println(CONTROL_NAME + " select mode");
    }

    @Override
    public void channelUp() {
        System.out.println(CONTROL_NAME + " Channel Up");
    }

    @Override
    public void channelDown() {
        System.out.println(CONTROL_NAME + " Channel Down");
    }

    @Override
    public void turnOn() {
        System.out.println(CONTROL_NAME + " Turn On!!");
    }
}
