package org.example.laba1_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class FlagApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Загружаем FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flag-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Текстовый флаг");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
