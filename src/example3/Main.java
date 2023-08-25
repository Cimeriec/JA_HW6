package example3;

import java.lang.annotation.*;

public class Main {
}
@MyAnno
class A {
    public void method() {
        System.out.println("A");
    }
}

class B extends A {
    @Override
    public void method() {
        System.out.println("B");
    }
}
class C extends B{
    @Override
    public void method() {
        System.out.println("C");
    }
    @Deprecated
    public void method2 () {
        System.out.println("method2");
    }
}
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.CLASS)
@Documented
@Inherited
@interface MyAnno {

}