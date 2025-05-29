import java.util.*;

class ExpressionParser {
    public double parse(String expression) {
        validateExpression(expression);
        return evaluatePostfix(infixToPostfix(expression));
    }

    private void validateExpression(String expression) {
        expression = expression.trim();

        if (!Character.isDigit(expression.charAt(0)) && expression.charAt(0) != '-') {
            throw new IllegalArgumentException("Выражение должно начинаться с числа.");
        }
        if (!Character.isDigit(expression.charAt(expression.length() - 1))) {
            throw new IllegalArgumentException("Выражение должно заканчиваться числом.");
        }

        String[] tokens = expression.split("(?<=[-+*/^//()])|(?=[-+*/^//()])");
        if (tokens.length > 100) {
            throw new IllegalArgumentException("Выражение содержит более 100 элементов.");
        }
    }

    private Queue<String> infixToPostfix(String expression) {
        Queue<String> output = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        StringBuilder numberBuffer = new StringBuilder();
        boolean lastWasOperator = true;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                numberBuffer.append(c);
                lastWasOperator = false;
            } else {
                if (numberBuffer.length() > 0) {
                    output.add(numberBuffer.toString());
                    numberBuffer.setLength(0);
                }

                if (c == '/' && i + 1 < expression.length() && expression.charAt(i + 1) == '/') {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence("//")) {
                        output.add(stack.pop());
                    }
                    stack.push("//");
                    i++;
                    lastWasOperator = true;
                } else if (c == '(') {
                    stack.push(String.valueOf(c));
                    lastWasOperator = true;
                } else if (c == ')') {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    lastWasOperator = false;
                } else if (isOperator(String.valueOf(c))) {
                    if (c == '-' && lastWasOperator) {
                        numberBuffer.append(c);
                    } else {
                        while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(String.valueOf(c))) {
                            output.add(stack.pop());
                        }
                        stack.push(String.valueOf(c));
                        lastWasOperator = true;
                    }
                }
            }
        }

        if (numberBuffer.length() > 0) {
            output.add(numberBuffer.toString());
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private double evaluatePostfix(Queue<String> postfix) {
        Stack<Double> stack = new Stack<>();
        while (!postfix.isEmpty()) {
            String token = postfix.poll();
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        return "+-*/^//".contains(str);
    }

    private int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/", "//" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    private double applyOperator(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            case "//" -> (int) (a / b);
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + op);
        };
    }
}
