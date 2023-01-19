package com.sg.foundations.flowcontrol.arrays;

public class StillPositive {
    public static void main(String[] args) {
        int[] num = { 389, -447, 26, -485, 712, -884, 94, -64, 868, -776, 227,
                -744, 422, -109, 259, -500, 278, -219, 799, -311};

        System.out.println("Gotta stay positive!");

        for(int i=0; i<num.length; i++){
            if(num[i]> 0){
                System.out.print(num[i] + " ");

            }
        }
    }
}
