package service;


import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditDaoFileImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dao.VendingMachinePersistenceException;
import dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VendingMachineServiceLayerImplTest {

    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");
    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);
    VendingMachineServiceLayer testService = new VendingMachineServiceLayerImpl(testAuditDao,testDao);


    public VendingMachineServiceLayerImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {

    }
    @Test
    public void testCheckIfEnoughMoney() {
        //ARRANGE
        Item crunchClone = new Item("Crunch");
        crunchClone.setCost(new BigDecimal("1.60"));
        crunchClone.setInventory(9);

        BigDecimal affordable = new BigDecimal("2.00");
        BigDecimal notAffordable = new BigDecimal("1.25");

        try {
            testService.checkIfEnoughMoney(crunchClone, affordable);
        } catch (InsufficientFundsException e){
            fail("There is sufficient funds, exception should not have been thrown");
        }

        try {
            testService.checkIfEnoughMoney(crunchClone, notAffordable);
            fail("There insufficient funds, exception should have been thrown");
        } catch (InsufficientFundsException e){
        }
    }

    @Test
    public void testGetItemsInStockWithCosts() {

    }

    @Test
    public void testGetChangePerCoin(){
        //ARRANGE
        Item chipsClone = new Item("Chips");
        chipsClone.setCost(new BigDecimal("2.00"));
        chipsClone.setInventory(9);

        BigDecimal money = new BigDecimal("2.50");

        //Change should be 50 cents
        Map<BigDecimal, BigDecimal> expectedChangePerCoin = new HashMap<>();
        expectedChangePerCoin.put(new BigDecimal("0.25"), new BigDecimal("2"));
        expectedChangePerCoin.put(new BigDecimal("0.10"), new BigDecimal("0"));
        expectedChangePerCoin.put(new BigDecimal("0.05"), new BigDecimal("0"));

        //ACT
        Map<BigDecimal, BigDecimal> changePerCoin = testService.getChangePerCoin(chipsClone, money);

    }

    @Test
    public void testGetItem() throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException {
        //ARRANGE
        Item twixClone = new Item("Twix");
        twixClone.setCost(new BigDecimal("2.10"));
        twixClone.setInventory(0);
        BigDecimal money = new BigDecimal("3.00");

        Item whoppersClone = new Item("Whoppers");
        whoppersClone.setCost(new BigDecimal("2.10"));
        whoppersClone.setInventory(testDao.getItemInventory("Whoppers"));

        Item itemWanted = null;

        try {
            itemWanted = testService.getItem("Twix", money);
            fail("The item wanted is out of stock.");
        }catch (NoItemInventoryException e) {
        }
        try {
            itemWanted = testService.getItem("Whoppers", money);
        }catch (NoItemInventoryException e) {
            if (testDao.getItemInventory("Whoppers")>0){
                fail("The item wanted is in stock.");
            }

            //ASSERT
            assertNotNull(itemWanted, "change should not be null");
            assertEquals(itemWanted, whoppersClone,"The item retrieved should be Twix");
        }
    }

    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String name = "Twix";

        try{
            //ACT
            testService.removeOneItemFromInventory(name);
            //ASSERT
            fail("There are no Twix left, exception should be thrown");
        } catch (NoItemInventoryException e) {
        }

        String whoppers = "Whoppers";
        try{
            //ACT
            testService.removeOneItemFromInventory(whoppers);
        } catch (NoItemInventoryException e) {
            if (testDao.getItemInventory(whoppers)>0){
                fail("whoppers are in stock, exception should not be thrown");
            }
        }
    }


}