package com.electronicvoting.ArduinoFingerprint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArduinoFingerprintRegister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/registerFinger.fxml"));
        primaryStage.setTitle("Fingerprint authenticator");
        primaryStage.setScene(new Scene(root, 321, 287));
        primaryStage.show();
    }

}
