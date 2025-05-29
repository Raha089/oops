import controller.CalculatorController;
import model.ExpressionEvaluator;
import view.CalculatorView;

public class Main {
    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        CalculatorController controller = new CalculatorController(view, evaluator);
        
        System.out.println("Калькулятор запущен. Введите 'exit' для выхода.");
        controller.run();
    }
}
