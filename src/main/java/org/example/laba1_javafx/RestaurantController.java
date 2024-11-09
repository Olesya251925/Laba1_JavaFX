package org.example.laba1_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class RestaurantController {
    @FXML
    private ListView<HBox> dishListView;
    @FXML
    private Button orderButton;

    private String[] dishNames = {"Паста", "Пицца", "Салат"};
    private double[] dishPrices = {150, 200, 100};
    private CheckBox[] checkBoxes = new CheckBox[dishNames.length];
    private TextField[] quantityFields = new TextField[dishNames.length];

    public void initialize() {
        for (int i = 0; i < dishNames.length; i++) {
            CheckBox checkBox = new CheckBox();
            checkBoxes[i] = checkBox;

            TextField quantityField = new TextField();
            quantityField.setPromptText("Количество");
            quantityFields[i] = quantityField;

            final int index = i;
            quantityField.setOnKeyReleased(event -> {
                String text = quantityField.getText();
                if (!text.isEmpty()) { // Проверка, что поле не пустое
                    try {
                        int quantity = Integer.parseInt(text);
                        if (quantity > 0) {
                            checkBox.setSelected(true);
                        } else {
                            showAlert("Введите положительное число!");
                            quantityField.clear();
                            checkBox.setSelected(false);
                        }
                    } catch (NumberFormatException e) {
                        showAlert("Введите положительное число!");
                        quantityField.clear();
                        checkBox.setSelected(false);
                    }
                } else {
                    checkBox.setSelected(false); // Сбрасываем чекбокс, если поле пустое
                }
            });

            // Обработчик для чекбокса
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    quantityField.setText("1"); // Устанавливаем значение "1"
                } else {
                    quantityField.clear(); // Очищаем поле, если чекбокс снят
                }
            });

            Label label = new Label(dishNames[i] + " - " + dishPrices[i] + " руб.");

            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(checkBox, label, quantityField);

            dishListView.getItems().add(hbox);
        }

        orderButton.setOnAction(event -> handleOrder());
    }

    private void handleOrder() {
        StringBuilder receipt = new StringBuilder("Ваш заказ:\n");
        double totalCost = 0;

        for (int i = 0; i < dishNames.length; i++) {
            if (checkBoxes[i].isSelected()) {
                int quantity;
                try {
                    String text = quantityFields[i].getText();
                    quantity = Integer.parseInt(text);
                    if (quantity < 1) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    quantity = 1;
                }

                receipt.append(dishNames[i]).append(" - ").append(quantity).append(" порций\n");
                totalCost += dishPrices[i] * quantity;
            }
        }

        if (totalCost == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Вы не выбрали ни одного блюда.");
            alert.showAndWait();
            return;
        }

        receipt.append("Общая стоимость: ").append(totalCost).append(" руб.");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Чек");
        alert.setHeaderText(null);
        alert.setContentText(receipt.toString());
        alert.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Неверный ввод");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
