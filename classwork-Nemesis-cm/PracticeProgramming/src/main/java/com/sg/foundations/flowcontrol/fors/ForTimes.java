package com.sg.foundations.flowcontrol.fors;
import java.util.Scanner;

public class ForTimes {

    public static void main(String[] args) {

        int num;
        Scanner sc = new Scanner(System.in);

        System.out.println("Type a number to multiply: ");
        num = sc.nextInt();

        for(int i = 1; i < 16; i++){
            System.out.println(i + " * " + num + " is: " + (i * num));
        }
    }
}
