package com.sg.foundations.flowcontrol.ifs;

// Java Program to guess a Random Number Generation

import java.util.Random;
import java.util.Scanner;

public class GuessMe {

    public static void main(String[] args)
    {
        // User guess and actual number
        int answer, guess;

        // maximum value is 100
        int MAX = 100;

        // takes input using scanner
        Scanner in = new Scanner(System.in);

        // Random instance
        Random rand = new Random();

        boolean correct = false;

        // correct answer
        answer = rand.nextInt(MAX) + 1;

            System.out.println("Guess a number between 1 and 100: ");

            // guess value
            guess = in.nextInt();

            // if guess is greater than actual
            if (guess > answer) {
                System.out.println("Too bad, way too high. I chose # " + answer);
            }

            // if guess is less than actual
            else if (guess < answer) {
                System.out.println("Ha, nice try - too low! I chose # " + answer);
            }

            // guess is equal to actual value
            else {

                System.out.println("Wow, nice guess! That was it!");

                correct = true;
            }

    }
}

