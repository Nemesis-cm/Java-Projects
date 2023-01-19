package StateCapitals2;
import java.util.Scanner;
public class UserIOImplement implements UserIO {
    Scanner sc = new Scanner(System.in);

    @Override
    public Integer getMinimum(){
        System.out.println("Enter a minimum population to view.");
        int minPop = sc.nextInt();
        return minPop;
    }

}
