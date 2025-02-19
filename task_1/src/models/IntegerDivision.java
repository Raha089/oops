package models;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class IntegerDivision {
    public static void integerDivision(StringBuilder input) {

        BigDecimal result;
        int start, end;
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(0) == ':' || input.charAt(input.length() - 1) == ':') {
                System.out.println("Ошибка в формуле");
                break;
            } else if (input.charAt(i) == ':') {
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

                // Делим и округляем результат до целого числа
                result = num1.divide(num2, 0, BigDecimal.ROUND_DOWN); // Округляем вниз для целочисленного результата

                // Записываем результат как строку без экспоненциальной записи
                String resultString = result.toPlainString();

                // Заменяем результат в строке на отформатированное значение
                input.replace(start, end, resultString);
                i = 0; // Перезапускаем цикл, чтобы учесть изменения в строке
            }
        }
        RecursionPath.recursionPath(input);
    }
}
