package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {

        String answer1;
        String answer2;
        String answer3;

        int correct = 0;
        int questions = 3;

        Scanner input = new Scanner(System.in);
        // Question 1
        System.out.println("Who was the 3rd president of the United States");
        System.out.println("A: Thomas Jefferson ");
        System.out.println("B: Lyndon B Johnson ");
        System.out.println("C: Samuel Adams ");
        System.out.println("D: Ulysses S Grant ");

        answer1 = input.nextLine();


        if(answer1.equals("A")){
            System.out.println("Nice Job!");
            correct = correct + 1;
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);

        }   else {
            System.out.println("Incorrect");
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);
        }
// End of question 1

        // Question 2
        System.out.println("What is 4 % 2 + 4");
        System.out.println("A:12 ");
        System.out.println("B:2 ");
        System.out.println("C:4 ");
        System.out.println("D:0 ");

        answer2 = input.nextLine();

        if(answer2.equals("C")){
            System.out.println("Nice Job!");
            correct = correct + 1;
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);

        }   else {
            System.out.println("Incorrect");
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);
        }
        // End of Question 2

        //Question 3
        System.out.println("What language are we predominantly using in this class? ");
        System.out.println("A:Javascript ");
        System.out.println("B:Java ");
        System.out.println("C:AWS ");
        System.out.println("D:Spring ");

        answer3 = input.nextLine();

        if(answer3.equals("B")){
            System.out.println("Nice Job!");
            correct = correct + 1;
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);

        }   else {
            System.out.println("Incorrect");
            questions = questions - 1;
            System.out.println("Questions correct "+ correct);
            System.out.println("Questions remaining "+ questions);
        }
        // End of Question 3
        if(correct == 3) {
            System.out.println("Congratulations, You got all questions correct.");
        }
        if(correct == 2){
            System.out.println("Oh you were so close, give it another go.");
        }
        if(correct == 1) {
            System.out.println("I know you can do better.");
        }    else if(correct == 0){
                System.out.println("Better Luck next time.");
        }
    }
}
