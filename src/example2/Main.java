package example2;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created Yemshenetskyy Serhii
 */
public class Main {
    /**
     * Main method
     */
    public static void main(String[] args) {
        Class<?> cl = Calculator.class;
        try {
            Method method = cl.getDeclaredMethod("calculate", int.class, int.class);
            if (method.isAnnotationPresent(Calc.class)) {
                Calc calc = method.getAnnotation(Calc.class);
                method.invoke(null, calc.firstNum(), calc.secondNum());
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

class Calculator {
    @Calc(firstNum = 10, secondNum = 2)
    /**
     * Основний метод калькулятора
     * @ param firstNum
     * @ param secondNum
     */
    public static void calculate(int firstNum, int secondNum) {
        /**
         * Об"єкт сканера для взаємодії з користувачем
         */
        Scanner in = new Scanner(System.in);
        System.out.print("Оберіть дію: (+, -, *, /) ");
        /**
         * Змінна sign зберігає в собі знак операції
         */
        String sign = in.nextLine();
        /**
         * Перевіряємо вирогідність ділення на 0
         */
        if (secondNum == 0 && sign.equals("/")){
            System.out.println("На 0 ділити заборонено");
        } else {
            /**
             * Якщо ділення на 0 немає, виконуемо одну з чотирьох операцій, або інформуємо
             * користувача, що він ввів неправильний знак операції
             */
            switch (sign) {
                case "+":
                    System.out.println(firstNum + "+" + secondNum + "=" + (firstNum + secondNum));
                    break;
                case "-":
                    System.out.println(firstNum + "-" + secondNum + "=" + (firstNum - secondNum));
                    break;
                case "*":
                    System.out.println(firstNum + "*" + secondNum + "=" + (firstNum * secondNum));
                    break;
                case "/":
                    System.out.println(firstNum + "/" + secondNum + "=" + (firstNum / secondNum));
                    break;
                default:
                    System.err.println("Невідома операція!");
            }
        }
    }
}
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Calc {
    /**
     * Перше число
     */
    int firstNum();

    /**
     * Друге число
     */
    int secondNum();
}
