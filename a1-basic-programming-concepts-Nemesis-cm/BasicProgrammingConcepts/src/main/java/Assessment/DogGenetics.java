package Assessment;
import java.util.Scanner;
import java.util.Random;

public class DogGenetics {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int doxy, chihuahua, jackRussell, retriever, pug;

        int numRemaining = 95;
        String name;

        System.out.print("What is your dog's name? ");
        name = sc.nextLine();
        System.out.println("I have this highly reliable DNA genetics report for " + name + ". \n");

        System.out.println(name + " is: \n");

        //100 / num of categories - only work if divisible,
        doxy = rand.nextInt(20) + 1;
        chihuahua = rand.nextInt(20) + 1;
        jackRussell = rand.nextInt(20) + 1;
        retriever = rand.nextInt(20) + 1;
        pug = 100 - doxy - chihuahua - jackRussell - retriever;

        System.out.println(doxy + "% Dachshund");
        System.out.println(chihuahua + "% Chihuahua");
        System.out.println(jackRussell + "% Jack Russell Terrier");
        System.out.println(retriever + "% Retriever");
        System.out.println(pug + "% Pug");

    }
}