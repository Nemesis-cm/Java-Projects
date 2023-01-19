
import dto.Item;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class Practice {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();

        Item twix = new Item("twix", "1.50", 10);
        Item snickers = new Item("snickers", "2.10", 10);
        Item Chips = new Item("chips", "1.30", 10);
        Item haribo = new Item("haribo", "1.60", 10);
        Item crunch = new Item("crunch", "2.50", 10);
        Item jerky = new Item("jerky", "1.20", 10);


        boolean keepGoing = true;
        int itemSelection = 0;
        while(keepGoing) {
            io.print("Vending Machine Selection: ");
            io.print("1. twix, $1.50");
            io.print("2. Snickers, $2.10");
            io.print("3. chips, $1.30");
            io.print("4. Haribo, $1.60");
            io.print("5. crunch, $2.50");
            io.print("6. Exit");

            itemSelection = io.readInt("Please select an item from the above list", 1,7);
            switch (itemSelection) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                case 6:
                    System.out.println("exit");
                    keepGoing = false;
                    break;
                default:
                    io.print("unknown command");
            }
        }
        io.print("Good bye");
    }

}






