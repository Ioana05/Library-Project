package com.myproject.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    private static Audit instance;

    // calea catre fisierul in care se va face scrierea
    private static final String path = "D:\\uni an 2\\SEM II\\POA\\LAB POA\\Library-Project\\.idea\\dataSources\\audit.csv";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // o facem clasa singletone
    private Audit() {

    }

    public static Audit getInstance(){
        if (instance == null){
            instance = new Audit();
        }
        return instance;
    }

    public void Write(String action) {
        // obtinem timpul curent pe care il formatam apoi
        String writenTime = LocalDateTime.now().format(dateFormatter);
        String finalMessage = action + "," + writenTime;

        // in blocul try, FileWriter si PrintWriter sunt initializate cu resurse => aceste resurse sunt deschide in blocul try si inchise automat la terminarea blocului
        // utilizam FileWriter pentru a deschide fisierul si pentru a adauga date la finalul lui(asta indica 'true')
        try (FileWriter openFile = new FileWriter(path, true);
             // initializam un obiect printWriter care ne va permite sa scriem in fisierul deschis
             PrintWriter writtenFile = new PrintWriter(openFile)) {
             writtenFile.println(finalMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



