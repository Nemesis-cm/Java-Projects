package SimpleCalculator;

import java.util.Scanner;
    public class CommandLineUserIO implements UserIO {
        Scanner sc = new Scanner(System.in);


        @Override
        public float getNum(String prompt){
            System.out.println(prompt);
            return sc.nextInt();
        }

        @Override
        public int getOperation(String prompt){
            System.out.println(prompt);
            return sc.nextInt();
        }

        @Override
        public void print(String prompt){
            System.out.println(prompt);
        }
    }
