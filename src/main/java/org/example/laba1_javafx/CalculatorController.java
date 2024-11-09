package org.example.laba1_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.Stack;

public class CalculatorController {
    @FXML
    private TextField resultField;

    private StringBuilder currentInput = new StringBuilder();
    private boolean lastInputWasOperator = false;
    private boolean hasError = false;
    private boolean resultDisplayed = false; // Флаг для отслеживания отображения результата

    @FXML
    public void onNumberClick(ActionEvent event) {
        if (hasError || resultDisplayed) {
            // Очищаем поле и текущий ввод, если до этого была ошибка или уже отображен результат
            currentInput.setLength(0);
            resultDisplayed = false;
            hasError = false;
        }
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        currentInput.append(buttonText);
        resultField.setText(currentInput.toString());
        lastInputWasOperator = false;
    }

    @FXML
    public void onOperatorClick(ActionEvent event) {
        if (hasError || resultDisplayed) {
            // Очищаем поле для нового выражения, если была ошибка или результат отображен
            currentInput.setLength(0);
            resultField.clear();
            resultDisplayed = false;
            hasError = false;
        }
        Button button = (Button) event.getSource();
        String operator = button.getText();

        // Разрешить "-" как знак отрицательного числа в начале выражения
        if (operator.equals("-") && (currentInput.length() == 0 || lastInputWasOperator)) {
            currentInput.append(operator);
            resultField.setText(currentInput.toString());
            lastInputWasOperator = false;
        } else if (!lastInputWasOperator && currentInput.length() > 0) {
            currentInput.append(" " + operator + " ");
            resultField.setText(currentInput.toString());
            lastInputWasOperator = true;
        }
    }

    @FXML
    public void onEqualClick(ActionEvent event) {
        String expression = currentInput.toString();
        if (expression.isEmpty()) return;

        try {
            double result = evaluateExpression(expression);
            currentInput.setLength(0);
            currentInput.append(result); // Сохраняем результат как новое выражение
            resultField.setText(String.valueOf(result));
            hasError = false;
            resultDisplayed = true; // Устанавливаем флаг для отображения результата
        } catch (ArithmeticException e) {
            resultField.setText("Ошибка");
            hasError = true;
        } catch (Exception e) {
            resultField.setText("Ошибка");
            hasError = true;
        }
    }

    @FXML
    public void onBackspaceClick(ActionEvent event) {
        if (hasError || resultDisplayed) {
            // Если была ошибка или результат отображен, очищаем поле полностью
            currentInput.setLength(0);
            resultField.clear();
            hasError = false;
            resultDisplayed = false;
            return;
        }
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            resultField.setText(currentInput.toString());
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isNumeric(token)) {
                values.push(Double.parseDouble(token));
            } else if (token.equals("-") && (i == 0 || isOperator(tokens[i - 1]))) {
                values.push(-Double.parseDouble(tokens[++i]));
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }

    private double applyOperator(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) return a / b;
                throw new ArithmeticException("Невозможно делить на ноль");
        }
        return 0;
    }
}
