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

    @FXML
    public void onNumberClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals(".") && currentInput.toString().contains(".")) {
            return;
        }

        currentInput.append(buttonText);
        resultField.setText(currentInput.toString());
        lastInputWasOperator = false;
    }

    @FXML
    public void onOperatorClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String operator = button.getText();

        if (!lastInputWasOperator && currentInput.length() > 0) {
            currentInput.append(" " + operator + " ");
            resultField.setText(currentInput.toString());
            lastInputWasOperator = true;
        }
    }

    @FXML
    public void onClearClick(ActionEvent event) {
        currentInput.setLength(0);
        resultField.clear();
        lastInputWasOperator = false;
    }

    @FXML
    public void onEqualClick(ActionEvent event) {
        String expression = currentInput.toString();
        if (expression.isEmpty()) return;

        try {
            double result = evaluateExpression(expression);
            currentInput.setLength(0);
            resultField.setText(String.valueOf(result));
        } catch (Exception e) {
            resultField.setText("Ошибка");
        }
    }

    @FXML
    public void onBackspaceClick(ActionEvent event) {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
        }

        if (resultField.getText().length() > 0) {
            String text = resultField.getText();
            resultField.setText(text.substring(0, text.length() - 1));
        }

        resultField.setText(currentInput.toString());
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                values.push(Double.parseDouble(token));
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
                throw new UnsupportedOperationException("Невозможно делить на ноль");
        }
        return 0;
    }
}
