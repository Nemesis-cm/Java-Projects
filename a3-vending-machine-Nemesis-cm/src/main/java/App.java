
import controller.vendingMachineController;
import dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        vendingMachineController controller = ctx.getBean("controller",vendingMachineController.class);

        controller.run();

    }
}
