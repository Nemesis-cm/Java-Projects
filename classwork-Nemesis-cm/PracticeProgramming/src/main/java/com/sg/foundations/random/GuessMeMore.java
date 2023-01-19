package com.sg.foundations.random;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {

    public static void main(String[] args)
    {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int rNum = rand.nextInt(101);
        Boolean posOrNeg = rand.nextBoolean();
        int answer;
        int guess;
        String strGuess;

        if (posOrNeg == true) {
            answer = rNum;
        } else {
            answer = rNum * -1;
        }

        do {
            System.out.println("Guess a number between -100 and 100");
            strGuess = sc.nextLine();
            guess = Integer.parseInt(strGuess);
            System.out.println("Your guess: " + guess + "\n");

            if (guess > answer) {
                System.out.println("Ha, nice try - too low! I chose # " + answer);
            } else if (guess < answer ){
                System.out.println("Too bad, way too high. I chose # " + answer);
            }

        }
        while (guess != answer);

        System.out.println("Wow, nice guess! That was it!");





    }


}