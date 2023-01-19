package AddressBook;
import java.util.ArrayList;

public interface UserIO {

    String getStringInput(String prompt);
    int getIntInput(String prompt);
    void showAddress(ArrayList<Address> e);
    void showNum(int size);

}