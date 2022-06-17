package com.metawara.quickscore.model;

/**
 * An implementation of a single-type pair.
 * @param <T> preferred pair type
 */
public class MonoPair<T> {

    private final T a;
    private final T b;

    private MonoPair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public static <T> MonoPair<T> of (T a, T b) {
        return new MonoPair<>(a, b);
    }

    public MonoPair<T> invert() {
        return new MonoPair<>(b, a);
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    @Override
    public String toString() {
        return "MonoPair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
