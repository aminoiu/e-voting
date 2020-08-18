package com.electronicvoting.ArduinoFingerprint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Controller {
    private static final String PORT_NAMES[] = {"COM3", // Windows
    };
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    @FXML
    TextArea registerFingerprintMessages;
//    SerialPort serialPort;
//    private BufferedReader input;
//    private BufferedWriter output;

    public void registerAction(ActionEvent actionEvent) throws IOException {
//        CommPortIdentifier portId = null;
//        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
//
//        //First, Find an instance of serial port as set in PORT_NAMES.
//        while (portEnum.hasMoreElements()) {
//            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
//            for (String portName : PORT_NAMES) {
//                if (currPortId.getName().equals(portName)) {
//                    portId = currPortId;
//                    break;
//                }
//            }
//        }
//        if (portId == null) {
//            System.out.println("Could not find COM port.");
//            return;
//        }
//
//        try {
//            serialPort = (SerialPort) portId.open(this.getClass().getName(),
//                    TIME_OUT);
//            serialPort.setSerialPortParams(DATA_RATE,
//                    SerialPort.DATABITS_8,
//                    SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE);
//
//            // open the streams
//            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
//            output = new BufferedWriter(new OutputStreamWriter(serialPort.getOutputStream()));
//
//            serialPort.addEventListener(new SerialPortEventListener() {
//                @Override
//                public void serialEvent(SerialPortEvent serialPortEvent) {
//                    if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
//                        try {
//                            String inputLine = null;
//                            if (input.ready()) {
//                                inputLine = input.readLine();
//                                System.out.println(inputLine);
//                            }
//
//                        } catch (Exception e) {
//                            System.err.println(e.toString());
//                        }
//                    }
//                    // Ignore all the other eventTypes, but you should consider the other ones.
//                }
//            });
//            serialPort.notifyOnDataAvailable(true);
//        } catch (Exception e) {
//            System.err.println(e.toString());
//        }
//
//        String code = "22";
//        output.write(code);
//        System.out.println("Sent code for registration to Arduino: " + code);
//
//    }


}}

