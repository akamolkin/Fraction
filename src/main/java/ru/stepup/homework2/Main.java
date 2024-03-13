package ru.stepup.homework2;

public class Main {
    public static void main(String[] args) {
        Fraction fr = new Fraction(2,3);
        Fractionable num = (Fractionable) Utils.cache(fr);
        num.doubleValue(); // invoke double value
        num.doubleValue(); // use cache
        num.doubleValue(); // use cache
        num.setNum(1);
        num.doubleValue(); // invoke double value
        num.doubleValue(); // use cache
    }
}
