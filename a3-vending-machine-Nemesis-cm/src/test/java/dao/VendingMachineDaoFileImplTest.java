package dao;


import java.math.BigDecimal;
import java.util.Map;

import dto.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");

    public VendingMachineDaoFileImplTest() {
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
    public void testGetItem() throws VendingMachinePersistenceException {

        Item twixClone = new Item("Twix");
        twixClone.setCost(new BigDecimal("2.10"));
        twixClone.setInventory(0);

        //ACT
        Item retrievedItem = testDao.getItem("Twix");

        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, twixClone,"The item retrieved should be Twix");

    }
    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Whoppers";


        int inventoryBefore = testDao.getItemInventory(itemName);

        testDao.removeOneItemFromInventory(itemName);

        int inventoryAfter = testDao.getItemInventory(itemName);

        assertTrue(inventoryAfter<inventoryBefore,"The inventory after should be less than before");
        assertEquals(inventoryAfter,inventoryBefore-1,"The inventory after should be one less than it was"
                + "before");

    }
    @Test
    public void testGetItemInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Snickers";

        //ACT
        int retrievedInventory = testDao.getItemInventory(itemName);

        //ASSERT
        assertEquals(retrievedInventory,0,"There are 0 items of Twix left.");
    }

    @Test
    public void testGetMapOfItemNamesInStockWithCosts() throws VendingMachinePersistenceException {

        //ACT
        Map<String,BigDecimal> itemsInStock = testDao.getMapOfItemNamesInStockWithCosts();


        assertFalse(itemsInStock.containsKey("Snickers"));

        assertEquals(itemsInStock.size(),6,"The menu list should contain 6 items.");
        assertTrue(itemsInStock.containsKey("Twix") &&
                itemsInStock.containsKey("Chips") &&
                itemsInStock.containsKey("Crunch") &&
                itemsInStock.containsKey("Whoppers") &&
                itemsInStock.containsKey("Starburst") &&
                itemsInStock.containsKey("Granola Bar"));
    }

}
