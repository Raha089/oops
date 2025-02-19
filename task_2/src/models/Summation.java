package models;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Summation {
    public static void summation(StringBuilder input) {
        System.out.println(input);
        BigDecimal result;
        int start, end;
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(0) == '+' || input.charAt(input.length() - 1) == '+') {
                System.out.println("Ошибка в формуле");
                break;
            } else if (input.charAt(i) == '+') {

                start = i - 1;
                end = i + 1;

                while (Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start))) && start > 0) start -= 1;

                if (start > 0 && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start - 1)))) start += 1;

                if (input.charAt(end) == '-' && !Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end - 1)))) end += 1;

                while (end < input.length() && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end)))) end += 1;

                // Используем BigDecimal для точности
                BigDecimal num1 = new BigDecimal(input.substring(start, i));
                BigDecimal num2 = new BigDecimal(input.substring(i + 1, end));

                result = num1.add(num2);

                // Записываем результат как строку без изменения точности
                String resultString = result.toPlainString();
                //System.out.println(resultString);

                // Заменяем результат в строке на отформатированное значение
                input.replace(start, end, resultString);
                i = 0;  // Перезапускаем цикл, чтобы учесть новые изменения в строке
            }
        }
        RecursionPath.recursionPath(input);
    }
}
