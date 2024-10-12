package org.example.laba1_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField inputField;
    @FXML
    private TextField outputField;
    @FXML
    private Button switchButton;

    private boolean isFirstToSecond = true; // Переменная для отслеживания направления

    @FXML
    public void initialize() {
        outputField.setEditable(false); // Второе поле только для чтения
    }

    @FXML
    protected void onSwitchButtonClick() {
        if (isFirstToSecond) {
            outputField.setText(inputField.getText());
            inputField.clear();
            switchButton.setText("<-"); // Измените стрелку на влево при первом нажатии
        } else {
            inputField.setText(outputField.getText());
            outputField.clear();
            switchButton.setText("->"); // Вернуть стрелку вправо при втором нажатии
        }
        isFirstToSecond = !isFirstToSecond; // Меняем направление
    }
}
