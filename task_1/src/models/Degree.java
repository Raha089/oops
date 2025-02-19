package models;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Degree {
    public static void degree(StringBuilder input){

        BigInteger result;
        int start, end;
        for (int i = 0; i < input.length(); ++i){
            if(input.charAt(0) == '^' || input.charAt(input.length()-1) == '^') {
                System.out.println("Ошибка в формуле");
                break;
            } else if (input.charAt(i) == '^'){
                start = i - 1;
                end = i + 1;

                // Найдем начало числа перед знаком возведения в степень
                while (Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start))) && start > 0) start -= 1;

                // Пропускаем знаки, если они есть
                if (start > 0 && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start - 1)))) start += 1;

                // Ищем конец числа после знака возведения
                if (input.charAt(end) == '-' && !Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end - 1)))) end += 1;

                while (end < input.length() && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end)))) end += 1;

                // Получаем числа для возведения в степень
                double base = Double.parseDouble(input.substring(start, i));
                double exponent = Double.parseDouble(input.substring(i + 1, end));

                // Выполняем возведение в степень с использованием Math.pow
                result = BigInteger.valueOf((long) Math.pow(base, exponent));

                // Заменяем исходное выражение на результат возведения в степень
                input.replace(start, end, result.toString());

                // Перезапускаем цикл для повторной обработки измененного input
                i = 0;
            }
        }
        RecursionPath.recursionPath(input);
    }
}
