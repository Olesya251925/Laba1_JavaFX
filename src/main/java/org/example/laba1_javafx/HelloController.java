package org.example.laba1_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField inputField;
    @FXML
    private TextField outputField;
    @FXML
    private Button switchButton;

    // Добавляем виджеты
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    // Добавляем чекбоксы
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;

    private boolean isFirstToSecond = true; // Переменная для отслеживания направления

    @FXML
    public void initialize() {
        outputField.setEditable(false); // Второе поле только для чтения

        // Устанавливаем состояние чекбоксов по умолчанию
        checkBox1.setSelected(true);
        checkBox2.setSelected(true);
        checkBox3.setSelected(true);

        // Устанавливаем видимость меток в зависимости от состояния чекбоксов
        label1.setVisible(checkBox1.isSelected());
        label2.setVisible(checkBox2.isSelected());
        label3.setVisible(checkBox3.isSelected());
    }

    @FXML
    protected void onSwitchButtonClick() {
        if (isFirstToSecond) {
            outputField.setText(inputField.getText());
            inputField.clear();
            switchButton.setText("<-");
        } else {
            inputField.setText(outputField.getText());
            outputField.clear();
            switchButton.setText("->");
        }
        isFirstToSecond = !isFirstToSecond;
    }

    // Обработчики чекбоксов
    @FXML
    protected void onCheckBox1Action() {
        label1.setVisible(checkBox1.isSelected());
    }

    @FXML
    protected void onCheckBox2Action() {
        label2.setVisible(checkBox2.isSelected());
    }

    @FXML
    protected void onCheckBox3Action() {
        label3.setVisible(checkBox3.isSelected());
    }
}
