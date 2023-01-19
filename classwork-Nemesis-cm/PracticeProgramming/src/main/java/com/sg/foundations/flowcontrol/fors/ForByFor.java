package com.sg.foundations.flowcontrol.fors;

public class ForByFor {
    public static void main(String[] args) {


        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            //prints 2nd row of i
            if (i == 1) {
                for (int j = 0; j < 3; j++) {

                    if (j == 1) {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("#");
                        }
                    } else {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("@");
                        }
                    }
                    System.out.print("|");
                }


            } //prints i normally
            else {
                for (int j = 0; j < 3; j++) {
                    //k prints $ for the second loop.
                    if (j == 1) {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("$");
                        }
                    } else {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("*");
                        }
                    }


                    System.out.print("|");
                }
            }


            System.out.println("");
        }
    }
}

