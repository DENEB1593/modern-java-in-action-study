package org.example.defaultmethod;

public class YourRemoteControl implements RemoteControl {

    private static final String CONTROL_NAME ="YourRemoteControl";

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
}
