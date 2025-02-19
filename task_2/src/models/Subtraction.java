package models;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Subtraction {
    public static void subtraction(StringBuilder input) {
        System.out.println(input);
        BigDecimal result;
        int start, end;
        for (int i = 0; i < input.length(); ++i) {

            if (input.charAt(input.length() - 1) == '-') {
                System.out.println("Ошибка в формуле");
                break;
            } else if (i != 0 && input.charAt(i) == '-' && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(i - 1)))) {
                start = i - 1;
                end = i + 1;

                // Найдем начало первого числа
                while (Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start))) && start > 0) start -= 1;

                // Пропустим любые знаки, если они есть
                if (start > 0 && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start - 1)))) start += 1;

                // Ищем конец второго числа
                if (input.charAt(end) == '-' && !Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end - 1)))) end += 1;

                while (end < input.length() && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end)))) end += 1;

                // Используем BigDecimal для точности
                BigDecimal num1 = new BigDecimal(input.substring(start, i));
                BigDecimal num2 = new BigDecimal(input.substring(i + 1, end));

                result = num1.subtract(num2);  // Вычитаем второе число из первого

                // Записываем результат как строку без экспоненциальной записи
                String resultString = result.toPlainString();

                // Заменяем результат в строке на отформатированное значение
                input.replace(start, end, resultString);
                i = 0;  // Перезапускаем цикл, чтобы учесть изменения в строке
            }
        }
        RecursionPath.recursionPath(input);
    }
}
