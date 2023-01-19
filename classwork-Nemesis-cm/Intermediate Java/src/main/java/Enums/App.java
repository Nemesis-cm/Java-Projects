package Enums;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class App {



    public static void main(String[] args) {

    }

    public static int DaysUntilFriday(DaysOfTheWeek day){
        switch (day) {
            case MONDAY:
                return 4;
            case TUESDAY:
                return 3;
            case WEDNESDAY:
                return 2;
            case THURSDAY:
                return 1;
            case FRIDAY:
                return 0;
            case SATURDAY:
                return 6;
            case SUNDAY:
                return 5;
            default:
                throw new UnsupportedOperationException();

        }
    }

    public static int calc(MathOperator math, int one, int two) {
        switch (math){
            case PLUS:
                return one + two;
            case MINUS:
                return one - two;
            default:
                throw new UnsupportedOperationException();
        }
    }



}