package com.sg.foundations.scanner;

import java.util.Scanner;

public class PassingTheTuringTest {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        String Name, Color, Food;
        int Num;

        System.out.println("Hi there!");
        System.out.println("What is Your name?");
        Name = inputReader.nextLine();

        System.out.println("Hi " + Name + " What is your Favorite Color? ");
        Color = inputReader.nextLine();

        System.out.println("oh " + Color + " Reminds me of my favorite food.");
        System.out.println("What is your favorite food " + Name + " ?");
        Food = inputReader.nextLine();

        System.out.println(Food + "? That's Crazy! What is your Favorite Number then?");
        Num = Integer.parseInt(inputReader.nextLine());

        System.out.println(Num + "? Thanks for your time " + Name + " " +
                "I hope to see you again soon, Goodbye.");

    }

}
