package org.example.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
