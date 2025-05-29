public class CalculatorModel {
    private final ExpressionParser parser;

    public CalculatorModel() {
        this.parser = new ExpressionParser();
    }

    public double evaluate(String expression) {
        return parser.parse(expression);
    }
}
