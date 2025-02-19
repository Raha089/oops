package models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class Factorial {
    public static void faсtorial(StringBuilder input) {
        System.out.println(input);
        int start;
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == '!') {
                start = i - 1;

                // Найдем начало числа
                while (start > 0 && Pattern.matches("[0-9]", Character.toString(input.charAt(start - 1)))) {
                    start--;
                }

                // Получаем число
                String numberStr = input.substring(start, i);
                BigDecimal number = new BigDecimal(numberStr);

                // Проверяем, является ли число целым и неотрицательным
                if (number.scale() > 0) {
                    throw new IllegalArgumentException("Факториал определен только для целых чисел.");
                }
                BigInteger intNumber = number.toBigInteger();
                if (intNumber.compareTo(BigInteger.ZERO) < 0) {
                    throw new IllegalArgumentException("Факториал определен только для неотрицательных чисел.");
                }

                // Вычисляем факториал
                BigInteger factorial = BigInteger.ONE;
                for (BigInteger j = BigInteger.ONE; j.compareTo(intNumber) <= 0; j = j.add(BigInteger.ONE)) {
                    factorial = factorial.multiply(j);
                }

                // Заменяем в строке
                input.replace(start, i + 1, factorial.toString());
                i = 0; // Перезапускаем цикл
            }
        }
        RecursionPath.recursionPath(input);
    }
}
