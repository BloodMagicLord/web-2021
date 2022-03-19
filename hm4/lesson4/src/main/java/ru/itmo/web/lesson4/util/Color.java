package ru.itmo.web.lesson4.util;

import java.util.Random;

public enum Color {
    RED,
    BLUE,
    GREEN;

    private static final Random RANDOM = new Random();

    public static Color randomColor()  {
        return values()[RANDOM.nextInt(values().length)];
    }
}
