package com.electronicvoting;


import com.electronicvoting.ArduinoFingerprint.ArduinoFingerprintRegister;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		Application.launch(ArduinoFingerprintRegister.class,args);
	}
}