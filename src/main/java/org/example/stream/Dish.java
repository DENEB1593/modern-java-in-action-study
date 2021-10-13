package org.example.stream;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    @Getter
    public enum Type {
        MEAT, FISH, OTHER
    }
}
