package models;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Division {
    public void division(StringBuilder input) {

        double result;
        int start, end;
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(0) == '/' || input.charAt(input.length() - 1) == '/') {
                System.out.println("Ошибка в формуле");
                break;
            } else if (input.charAt(i) == '/') {
                start = i - 1;
                end = i + 1;

                while (Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start))) && start > 0) start -= 1;

                if (start > 0 && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(start - 1)))) start += 1;


                if (input.charAt(end) == '-' && !Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end - 1))))
                    end += 1;

                while (end < input.length() && Pattern.matches("[0-9.0-9]", Character.toString(input.charAt(end))))
                    end += 1;

                result = Double.parseDouble(input.substring(start, i)) / Double.parseDouble(input.substring(i + 1, end));
                System.out.println(Double.toString(result));
                System.out.println(end);
                input.replace(start, end, Double.toString(result));
                System.out.println(input.replace(start, end, Double.toString(result)));

                i = 0;
            }
        }
        RecursionPath.recursionPath(input);
    }
}
