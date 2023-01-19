package com.sg.foundations.flowcontrol.fors;
import java.util.Scanner;


public class FizzBuzz {
    public static void main(String[] args) {
        int tally = 0;
        int fizzBuzzStop;
        Scanner sc = new Scanner(System.in);

        System.out.println("How many units of fizzing and buzzing do you need "
                + "in your life?");
        fizzBuzzStop = sc.nextInt();

        for (int i = 0; tally < fizzBuzzStop; i++){

            if (i % 3 == 0 && i % 5 == 0 && i != 0){
                System.out.println("fizz buzz");
                tally++;
            } else if (i % 3 == 0 && i != 0){
                System.out.println("fizz");
                tally++;
            }else if (i %5 ==0 && i !=0) {
                System.out.println("buzz");
                tally++;
            }else {
                System.out.println(i);
            }
        }
        System.out.println("TRADITION!!");
    }
}
