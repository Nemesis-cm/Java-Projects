package com.sg.foundations.flowcontrol.whiles;

import java.util.Random;

public class LazyTeenager {
    public static void main(String[] args) {
        Random rand = new Random();

        Boolean CleanRoom = false;
        int teenagerResponse;
        int Asked = 0;
        int chance = 5;

        do {
            System.out.println("Clean your room!");
            teenagerResponse = rand.nextInt(100)+1;
            System.out.println("chance: " + chance + " Response: " + teenagerResponse);


            if ( teenagerResponse >= 1 && teenagerResponse <= chance){
                CleanRoom = true;
                System.out.println("Fine, I'll clean my room!");
            }

            if (Asked == 15) {
                System.out.println("You're grounded!");
                break;
            }
            Asked++;
            chance +=5;

        }while (!CleanRoom);


    }
}
