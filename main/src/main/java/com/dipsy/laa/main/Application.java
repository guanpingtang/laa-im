package com.dipsy.laa.main;

import com.dipsy.laa.im.ImServerStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ImServerStart imServerStart;

    @Override
    public void run(String... args) throws Exception {
        imServerStart.start();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}