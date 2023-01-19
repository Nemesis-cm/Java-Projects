package InterestCalculator;
import java.util.Scanner;

public class InterestCalculator {

        public static void main(String[] args) {

            int intFreq = getInput("Input the number for compound periods choice: " +
                    "\n1. quarterly " +
                    "\n2. monthly " +
                    "\n3. daily  ");
            int timesPerYear = getFrequency(intFreq);
            float compoundInt = getInputf("What is the annual interest rate?") / timesPerYear;

            float initInvest = getInputf("How much will you invest?");

            int yearsInFund = getInput("How many years will you stay in the fund?");

            int currentYear = 1;

            float endInvest = 0;
            float startInvest = initInvest;
            float intGain;

            for (int i = 0; i < yearsInFund; i++){

                System.out.println(" \n Current year is  " + currentYear);

                System.out.printf(" Starting principal: " + "%.2f", initInvest);

                for (int j = 0; j < timesPerYear; j++){

                    endInvest = getNewInvest(startInvest, compoundInt);
                    startInvest = endInvest;
                }



                System.out.printf(" Ending principal: "  +  "%.2f", endInvest);
                intGain = endInvest - initInvest;
                System.out.printf(" Interest earned in year: " + "%.2f", intGain) ;
                initInvest = startInvest;
                currentYear++;
            }


        }
        public static float getInputf( String prompt){
            Scanner sc = new Scanner(System.in);

            System.out.println(prompt);
            float answer = sc.nextInt();
            return answer;
        }
        public static int getInput( String prompt){
            Scanner sc = new Scanner(System.in);

            System.out.println(prompt);
            int answer = sc.nextInt();
            return answer;
        }
        public static float getNewInvest(float start, float interest){
            float calculatedInvest = start * (1+ (interest / 100));
            return calculatedInvest;
        }
        public static int getFrequency(int intChoice){
            int gotFreq = 0;
            switch (intChoice){
                case 1: gotFreq = 4;
                    break;
                case 2: gotFreq = 12;
                    break;
                case 3: gotFreq = 365;
                    break;
            }
            return gotFreq;
        }
    }
