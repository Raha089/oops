package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {
    public double evaluate(String expr) throws Exception {
        if (!checkBrackets(expr)) {
            throw new Exception("Unbalanced brackets");
        }

        expr = expr.replaceAll("\\s+", "").replace("**", "^");

        expr = processFunctions(expr, "exp", x -> Math.exp(evaluate(x)));
        expr = processFunctions(expr, "log", x -> Math.log(evaluate(x)) / Math.log(2));
        expr = processFactorial(expr);
        expr = processPower(expr);

        return simpleEval(expr);
    }

    private String processFunctions(String expr, String funcName, Function<String, Double> func) throws Exception {
        Pattern pattern = Pattern.compile(funcName + "\\(([^()]+|\\([^()]*\\))\\)");
        Matcher matcher = pattern.matcher(expr);
        while (matcher.find()) {
            String innerExpr = matcher.group(1);
            double val = func.apply(innerExpr);
            expr = expr.replace(matcher.group(0), Double.toString(val));
            matcher = pattern.matcher(expr);
        }
        return expr;
    }

    private String processFactorial(String expr) throws Exception {
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)!");
        Matcher matcher = pattern.matcher(expr);
        while (matcher.find()) {
            int n = Integer.parseInt(matcher.group(1));
            if (n < 0) throw new Exception("Factorial of negative number");
            long fact = factorial(n);
            expr = expr.replace(matcher.group(0), Long.toString(fact));
            matcher = pattern.matcher(expr);
        }
        return expr;
    }

    private String processPower(String expr) throws Exception {
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?|(?:\\([^()]+\\)))\\^(\\d+(?:\\.\\d+)?|(?:\\([^()]+\\)))");
        Matcher matcher = pattern.matcher(expr);
        while (matcher.find()) {
            double base = evaluate(matcher.group(1));
            double exponent = evaluate(matcher.group(2));
            double val = Math.pow(base, exponent);
            expr = expr.replace(matcher.group(0), Double.toString(val));
            matcher = pattern.matcher(expr);
        }
        return expr;
    }

    private long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    private double simpleEval(String expr) throws Exception {
        String[] tokens = expr.split("(?=[-+])|(?<=[-+])");
        double result = 0;
        char lastOp = '+';
        
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            
            if (token.matches("[-+]")) {
                lastOp = token.charAt(0);
            } else {
                double num = parseTerm(token);
                switch (lastOp) {
                    case '+': result += num; break;
                    case '-': result -= num; break;
                }
            }
        }
        return result;
    }

    private double parseTerm(String term) throws Exception {
        String[] parts = term.split("(?=[*/])|(?<=[*/])");
        double res = Double.parseDouble(parts[0]);
        
        for (int i = 1; i < parts.length; i += 2) {
            String op = parts[i];
            double num = Double.parseDouble(parts[i + 1]);
            
            if (op.equals("*")) res *= num;
            else if (op.equals("/")) {
                if (num == 0) throw new ArithmeticException("Division by zero");
                res /= num;
            }
        }
        return res;
    }

    public boolean checkBrackets(String expr) {
        int balance = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    @FunctionalInterface
    private interface Function<T, R> {
        R apply(T t) throws Exception;
    }
}
