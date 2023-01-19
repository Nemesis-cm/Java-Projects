package com.sg.foundations.random;
import java.util.Random;

    public class FortuneCookie
    {
        public static void main( String[] args )
        {
            Random r = new Random();

            int quote1 = 1 + r.nextInt();
            int quote2 = 1 + r.nextInt(54);
            int quote3 = 1 + r.nextInt(54);
            int quote4 = 1 + r.nextInt(54);
            int quote5 = 1 + r.nextInt(54);
            int quote6 = 1 + r.nextInt(54);

            int fortune = 1 + r.nextInt(6);
            String msg;

            if ( fortune == 1 )
                msg = "You are going to find lots of money in the parking lot.";
            else if ( fortune == 2 )
                msg = "Don't drive home tonight. Take a walk!";
            else if ( fortune == 3 )
                msg = "Mirrors only lead to vanity. Smash them all.";
            else if ( fortune == 4 )
                msg = "When the wind blows west and the crow flies east. That is when you should head to the bathroom.";
            else if ( fortune == 5 )
                msg = "Stop eating so much Chinese food. It's full of MSG.";
            else if ( fortune == 6 )
                msg = "Help! Trapped in fortune cookie factory!!";
            else
                msg = "You have no fortune...";

            System.out.println( " Fortune cookie says: " + msg );
        }
    }
