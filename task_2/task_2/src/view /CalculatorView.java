package view;

import java.util.Scanner;

public class CalculatorView {
    private final Scanner scanner = new Scanner(System.in);

    public String getExpression() {
        System.out.print("Введите выражение: ");
        return scanner.nextLine();
    }

    public void showResult(double result) {
        System.out.println("Результат: " + result);
    }

    public void showError(String message) {
        System.err.println("Ошибка: " + message);
    }
}
