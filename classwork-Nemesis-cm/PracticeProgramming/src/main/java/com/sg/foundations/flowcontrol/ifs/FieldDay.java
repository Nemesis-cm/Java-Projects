package com.sg.foundations.flowcontrol.ifs;
import java.util.Scanner;

public class FieldDay {

    public static void main(String[] args) {

        String rd, dm, mc, gs, ng, bh, lastName;
        rd = "red dragons";
        dm = "dark wizards";
        mc = "moving castles";
        gs = "golden snitches";
        ng = "night guards";
        bh = "black holes";


        Scanner sc = new Scanner(System.in);
        //for statement to run many times.

        for (int i = 0; i < 20; i++) {
            System.out.println("What's your Last name?");
            lastName = sc.nextLine();

            // replaces baggins before dresdin
            if (lastName.compareToIgnoreCase("Baggins") < 0) {
                System.out.println("you're on team " + rd);
            } else if (lastName.compareToIgnoreCase("Dresden") < 0) {
                System.out.println("you're on team " + dm);
            } else if (lastName.compareToIgnoreCase("Howl") < 0) {
                System.out.println("you're on team " + mc);
            } else if (lastName.compareToIgnoreCase("Potter") < 0) {
                System.out.println("you're on team " + gs);
            } else if (lastName.compareToIgnoreCase("Vimes") < 0) {
                System.out.println("you're on team " + ng);
            } else if (lastName.compareToIgnoreCase("Vimes") > 0) {
                System.out.println("you're on team " + bh);
            }
            System.out.println("Good Luck in the games");
        }

    }
}
