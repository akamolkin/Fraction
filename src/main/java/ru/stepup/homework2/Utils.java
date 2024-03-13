package ru.stepup.homework2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Utils implements InvocationHandler {
    Object obj;
    Object cachedObj;

    public Utils(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        Annotation[] anns = m.getDeclaredAnnotations();
        if (Arrays.stream(anns).filter(x->((Annotation)x).annotationType().equals(Cache.class)).count() > 0){
            if (cachedObj != null) {
                System.out.println("use cache");
                return cachedObj;
            }
            cachedObj = method.invoke(obj, args);
            return cachedObj;
        }
        if (Arrays.stream(anns).filter(x->((Annotation)x).annotationType().equals(Mutator.class)).count() > 0){
            cachedObj = null;
        }
        return method.invoke(obj, args);
    }

    public static Object cache(Object obj){
        Class cls = obj.getClass();
        return  Proxy.newProxyInstance(cls.getClassLoader(),
                cls.getInterfaces(),
                new Utils(obj));
    }
}
