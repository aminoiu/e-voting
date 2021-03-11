package com.electronicvoting.controller;

import com.electronicvoting.ArduinoFingerprint.ArduinoFingerprintRegister;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/evoting/admins/fingerprint")
@RequiredArgsConstructor
public class FingerprintController {


//    Application.launch(ArduinoFingerprintRegister .class,args);
}
