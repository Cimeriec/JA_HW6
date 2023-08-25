package example4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Class<?> cl = Calc.class;
        try {
            Method method = cl.getMethod("addMethod", int.class, int.class);
            if (method.isAnnotationPresent(Math.class)) {
                Math math = method.getAnnotation(Math.class);
                method.invoke(new Calc(), math.num1(), math.num2());
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@interface Math {
    int num1();

    int num2();
}

class Calc {
    @Math(num1 = 100, num2 = 200)
    public void addMethod(int num1, int num2) {
        System.out.println(num1 + num2);
    }
}