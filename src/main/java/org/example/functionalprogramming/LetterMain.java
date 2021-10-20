package org.example.functionalprogramming;

public class LetterMain {
    public static void main(String[] args) {
        String content = Letter.transformationPipeline.apply("hi");
        System.out.println(content);
    }
}
