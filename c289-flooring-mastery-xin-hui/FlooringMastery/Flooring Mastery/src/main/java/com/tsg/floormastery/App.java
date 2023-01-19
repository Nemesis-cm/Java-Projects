package com.tsg.floormastery;

import com.tsg.floormastery.controller.FlooringMasteryController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.scan("com.tsg.floormastery");
        context.refresh();

        FlooringMasteryController controller = context.getBean("flooringMasteryController",
                FlooringMasteryController.class);
        controller.run();
    }
}
