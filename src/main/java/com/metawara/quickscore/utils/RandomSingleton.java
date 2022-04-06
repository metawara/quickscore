package com.metawara.quickscore.utils;

import java.util.Random;

public class RandomSingleton {
    private static RandomSingleton instance;
    private final Random rnd;

    private RandomSingleton() {
        rnd = new Random();
    }

    public static RandomSingleton getInstance() {
        if(instance == null) {
            instance = new RandomSingleton();
        }
        return instance;
    }

    public Random getRnd() {
        return rnd;
    }
}