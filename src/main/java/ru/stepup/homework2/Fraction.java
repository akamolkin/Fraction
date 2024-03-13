package ru.stepup.homework2;

import java.lang.reflect.Proxy;
public class Fraction implements Fractionable{
    private int num;
    private int denum;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    public  Object getProxy()
    {
        Class cls = this.getClass();
        return  Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{Fractionable.class},
                new Utils(this));
    }

    @Override
    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    @Cache
    public double doubleValue() {
        System.out.println("invoke double value");
        return (double) num/denum;
    }
}
