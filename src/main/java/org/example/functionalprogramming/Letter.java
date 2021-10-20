package org.example.functionalprogramming;

import java.util.function.Function;

public class Letter {
    public static String addHeader(String text) {
        return "From Lee, To Kim and Park : " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("hi", "hello");
    }

    static Function<String, String> addHeader = Letter::addHeader;
    static Function<String, String> transformationPipeline =
            addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);
}
