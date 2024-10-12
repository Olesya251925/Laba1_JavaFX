package org.example.laba1_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calculator-view.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 480, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
