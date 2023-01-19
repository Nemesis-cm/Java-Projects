package com.sg.foundations.flowcontrol.whiles;
import java.util.Scanner;

public class StayPositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Choose a number ");
        int num = sc.nextInt();

        int countdown = 0;

        while (num >= 0) {
            System.out.print(num + " ");
            num--;
            countdown++;
            // for every 10 prints a new line is made
            if (countdown % 10 == 0){
                System.out.print("\n");

            }
        }
    }
}

