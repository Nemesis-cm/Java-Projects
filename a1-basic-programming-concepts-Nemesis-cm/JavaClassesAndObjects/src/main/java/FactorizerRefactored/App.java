package FactorizerRefactored;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Refactorized myFact = new Refactorized();

        System.out.println("Choose a number to factorize:");
        int userNum = sc.nextInt();

        myFact.factorizeNum(userNum);

        if (myFact.checkPerfect(userNum)) {
            System.out.println("It's a perfect number!");
        }

        if (myFact.checkPrime(userNum)) {
            System.out.println("It's a prime number!");
        }







    }
}
