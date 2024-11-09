package org.example.laba1_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class FlagController {

    @FXML
    private RadioButton red1, green1, blue1;
    @FXML
    private RadioButton red2, green2, blue2;
    @FXML
    private RadioButton red3, green3, blue3;

    @FXML
    private Label resultLabel;

    // Метод, который вызывается при нажатии на кнопку "Нарисовать"
    @FXML
    public void onDrawButtonClick() {
        String color1 = getSelectedColor(red1, green1, blue1);
        String color2 = getSelectedColor(red2, green2, blue2);
        String color3 = getSelectedColor(red3, green3, blue3);

        // Проверяем, что для всех полос выбран цвет
        if (color1 != null && color2 != null && color3 != null) {
            resultLabel.setText("Цвета полос: " + color1 + ", " + color2 + ", " + color3);
            disableFlags(true); // Блокируем флаги после выбора
        } else {
            resultLabel.setText("Ошибка: Пожалуйста, выберите цвет для всех полос.");
        }
    }

    // Метод, который вызывается при нажатии на кнопку "Стереть"
    @FXML
    public void onClearButtonClick() {
        // Сбрасываем выбранные RadioButton
        red1.setSelected(false);
        green1.setSelected(false);
        blue1.setSelected(false);
        red2.setSelected(false);
        green2.setSelected(false);
        blue2.setSelected(false);
        red3.setSelected(false);
        green3.setSelected(false);
        blue3.setSelected(false);

        // Очищаем результат и разблокируем флаги
        resultLabel.setText("Здесь будет результат");
        disableFlags(false); // Разблокируем флаги для нового выбора
    }

    // Вспомогательный метод для блокировки или разблокировки флагов
    private void disableFlags(boolean disable) {
        red1.setDisable(disable);
        green1.setDisable(disable);
        blue1.setDisable(disable);
        red2.setDisable(disable);
        green2.setDisable(disable);
        blue2.setDisable(disable);
        red3.setDisable(disable);
        green3.setDisable(disable);
        blue3.setDisable(disable);
    }

    // Вспомогательный метод для получения выбранного цвета из группы RadioButton
    private String getSelectedColor(RadioButton... buttons) {
        for (RadioButton button : buttons) {
            if (button.isSelected()) {
                return button.getText(); // Возвращаем текст выбранной кнопки
            }
        }
        return null;
    }
}
