package org.example.laba1_javafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Запускаем первое окно (HelloApplication)
        HelloApplication helloApp = new HelloApplication();
        helloApp.start(primaryStage);

        // Запускаем второе окно (RestaurantApplication)
        RestaurantApplication restaurantApp = new RestaurantApplication();
        restaurantApp.start(new Stage());
    }

    public static void main(String[] args) {
        launch();
    }
}
