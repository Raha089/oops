package controller;

import model.ExpressionEvaluator;
import view.CalculatorView;

public class CalculatorController {
    private final CalculatorView view;
    private final ExpressionEvaluator evaluator;

    public CalculatorController(CalculatorView view, ExpressionEvaluator evaluator) {
        this.view = view;
        this.evaluator = evaluator;
    }

    public void run() {
        while (true) {
            try {
                String expr = view.getExpression();
                if (expr.equalsIgnoreCase("exit")) {
                    break;
                }
                
                if (!evaluator.checkBrackets(expr)) {
                    view.showError("Неправильное количество скобок!");
                    continue;
                }
                
                double result = evaluator.evaluate(expr);
                view.showResult(result);
            } catch (Exception e) {
                view.showError(e.getMessage());
            }
        }
    }
}
