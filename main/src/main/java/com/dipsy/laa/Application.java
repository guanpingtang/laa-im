package com.dipsy.laa;

import com.dipsy.laa.im.dispacher.MessageSelector;
import com.dipsy.laa.im.transport.ImServerStart;
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
        MessageSelector messageSelector = new MessageSelector();
        messageSelector.start();
        imServerStart.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}