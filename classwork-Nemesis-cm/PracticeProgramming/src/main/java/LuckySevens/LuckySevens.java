package LuckySevens;


import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many dollars do you have?");
        int dollars = sc.nextInt();
        int roll;
        int countRoll = 0;
        int moneyMax = 0;
        int rollWithMax = 0;


        while(dollars > 0){
            roll = rollDice();
            countRoll++;

            //Win/ loss
            if (roll == 7){
                dollars += 4;
            } else {
                dollars -= 1;
            }

            // Sets max dollars and counts rolls
            if (dollars > moneyMax){
                moneyMax = dollars;
                rollWithMax = countRoll;
            }
        }
        System.out.println("You are broke after " + countRoll + " rolls.");
        System.out.println("You should have quit after " + rollWithMax + " when you had $" + moneyMax);

    }
    public static int rollDice(){
        Random rand = new Random();
        int diceOne = rand.nextInt(6) + 1;
        int diceTwo = rand.nextInt(6) + 1;
        int sum = diceOne + diceTwo;
        return sum;


    }
}
