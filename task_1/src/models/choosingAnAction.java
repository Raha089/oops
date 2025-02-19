package models;

import java.util.regex.Pattern;

public class choosingAnAction {
    public void choosingAnAction(StringBuilder input) {
        // Выполняем операции с самым высоким приоритетом
        while (input.indexOf("^") != -1) {
            Degree.degree(input);
        }

        // Выполняем деление и умножение в одном проходе
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case ':' -> {
                    IntegerDivision.integerDivision(input);
                    i = -1; // Перезапускаем проход
                }
                case '/' -> {
                    new Division().division(input);
                    i = -1;
                }
                case '*' -> {
                    Multiplication.multiplication(input);
                    i = -1;
                }
            }
        }

        // Выполняем сложение и вычитание, учитывая отрицательные числа
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '+' -> {
                    Summation.summation(input);
                    i = -1; // Перезапускаем проход
                }
                case '-' -> {
                    if (i > 0 && Character.isDigit(input.charAt(i - 1))) {
                        Subtraction.subtraction(input);
                        i = -1;
                    }
                }
            }
        }
    }

    public static class RemainderDivision {
        public static void remainderDivision(StringBuilder input){

            double result;
            int start, end;
            for (int i = 0; i < input.length(); ++i){
                if(input.charAt(0) == '%' || input.charAt(input.length()-1) == '%') {
                    System.out.println("Ошибка в формуле");
                    break;}
                else if (input.charAt(i) == '%'){
                    start = i - 1;
                    end = i + 1;

                    while (Pattern.matches("[0-9.0-9]" ,Character.toString(input.charAt(start))) && start > 0) start -= 1;

                    if (start > 0 && Pattern.matches("[0-9.0-9]" ,Character.toString(input.charAt(start - 1))))start += 1;

                    if (input.charAt(end) == '-' && !Pattern.matches("[0-9.0-9]" ,Character.toString(input.charAt(end-1)))) end += 1;

                    while (end < input.length() && Pattern.matches("[0-9.0-9]" ,Character.toString(input.charAt(end)))) end += 1;

                    result = Double.parseDouble(input.substring(start, i)) % Double.parseDouble(input.substring(i+1, end));


                    input.replace(start, end, Double.toString(result));
                    i=0;
                }
            }
            RecursionPath.recursionPath(input);
        }
    }
}
