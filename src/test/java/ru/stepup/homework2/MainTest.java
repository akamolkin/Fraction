package ru.stepup.homework2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
    static ByteArrayOutputStream baos;
    static PrintStream ps;

    @BeforeAll
    static void preparing() {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        System.setOut(ps);

    }

    @Test
    void testSout() {
        String res;
        Fraction fr = new Fraction(2,3);
        Fractionable num = (Fractionable) Utils.cache(fr);

        num.doubleValue(); // invoke double value
        res = baos.toString();
        Assertions.assertEquals("invoke double value" + System.lineSeparator(), res);
        baos.reset();

        num.doubleValue(); // use cache
        res = baos.toString();
        Assertions.assertEquals("use cache" + System.lineSeparator(), res);
        baos.reset();

        num.setNum(5);
        num.doubleValue(); // invoke double value
        res = baos.toString();
        Assertions.assertEquals("invoke double value" + System.lineSeparator(), res);
        baos.reset();

        num.doubleValue(); // use cache
        res = baos.toString();
        Assertions.assertEquals("use cache" + System.lineSeparator(), res);
        baos.reset();
    }
}
