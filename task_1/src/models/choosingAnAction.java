package models;


public class choosingAnAction {
    public void choosingAnAction(StringBuilder input) {

        while (input.indexOf("^") != -1) {
            Degree.degree(input);
        }


        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '/' -> {
                    if (i + 1 < input.length() && input.charAt(i + 1) == '/') {
                        input.replace(i, i + 2, ":");
                        IntegerDivision.integerDivision(input);
                        i = -1;
                    } else {
                        new Division().division(input);
                        i = -1;
                    }
                }
                case '*' -> {
                    Multiplication.multiplication(input);
                    i = -1;
                }
            }
        }


        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '+' -> {
                    Summation.summation(input);
                    i = -1;
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
}
