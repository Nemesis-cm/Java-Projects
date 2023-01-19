package Factorizer;

import java.util.Scanner;

public class Factorizer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int factorIndex = 0;
        int sum = 0;
        int posUserNum;
        int possibleFactor;

        System.out.println("Please choose a number to factorize: ");
        int userNum = sc.nextInt();
        if (userNum < 0){
            posUserNum = userNum * -1;
        }   else {
            posUserNum = userNum;
        }

        // length is written to account for negative factors
        int[] factors = new int [(posUserNum * 2)];

        //finds our factors
        for ( possibleFactor = 1; possibleFactor <= posUserNum; possibleFactor++){
            if (userNum % possibleFactor == 0){
                factors[factorIndex] = possibleFactor;
                factorIndex++;
                factors[factorIndex] = possibleFactor * -1;
                factorIndex++;
            }

        }


        // prints out to leave out user input and blank spaces
        for (int i = 0; i< factors.length ; i ++){
            if (!((0 == factors[i]) || factors[i] == userNum || factors[i] == (userNum * -1))){
                System.out.println("factors are " + factors[i]);
            }
        }

        // prints if user input is a perfect number
        if (userNum >=0){
            for (int j=0; j<factors.length; j+= 2){

                sum  += factors[j];
            }

            if(sum/2 == userNum){
                System.out.println("Perfect number!");
            }
        }

        // Shows whether the number is prime or not
        if (userNum >=0 && sum == 1 + userNum ){
            System.out.println("It's a prime!");
            // if negative, a different loop determines if it's prime
        }
        if (userNum <0){
            for (int k=0; k<factors.length; k+= 2){
                sum += factors[k];
            }
            if (sum == (posUserNum + 1)){
                System.out.println("It's a prime!");
            }

        }
    }

}
