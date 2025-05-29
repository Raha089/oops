import java.util.Scanner;

class CalculatorView {
    private final Scanner scanner;

    public CalculatorView() {
        this.scanner = new Scanner(System.in);
    }

    public void showResult(double result) {
        System.out.println("Результат: " + result);
    }

    public String getInput() {
        System.out.print("Введите выражение: ");
        return scanner.nextLine();
    }
}
