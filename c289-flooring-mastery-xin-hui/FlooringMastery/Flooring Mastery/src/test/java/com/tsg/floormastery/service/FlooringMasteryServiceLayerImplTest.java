package com.tsg.floormastery.service;

import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.dao.OrderPersistenceException;
import com.tsg.floormastery.model.Tax;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class FlooringMasteryServiceLayerImplTest {

    private static FlooringMasteryServiceLayer service;

    @BeforeAll
    static void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("test");
        context.scan("com.tsg.floormastery");
        context.refresh();

        service = context.getBean(FlooringMasteryServiceLayer.class);
    }

    @Test
    void addOrder() {
        Map<Integer, Order> allOrders = service.retrieveAllOrders();
//        1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Ada Lovelace");
        order.setState("CA");
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("249.00"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order.setMaterialCost(new BigDecimal("871.50"));
        order.setLaborCost(new BigDecimal("1033.35"));
        order.setTax(new BigDecimal("476.21"));
        order.setTotal(new BigDecimal("2381.06"));

        int count = allOrders.size();
        service.addOrder(order);
        allOrders = service.retrieveAllOrders();
        assertNotNull(allOrders);
        assertEquals(count + 1, allOrders.size());
        assertTrue(allOrders.containsValue(order));

    }

    @Test
    void validateOrder() throws Exception{
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Ada Lovelace");
        order.setState("CA");
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("249.00"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order.setMaterialCost(new BigDecimal("871.50"));
        order.setLaborCost(new BigDecimal("1033.35"));
        order.setTax(new BigDecimal("476.21"));
        order.setTotal(new BigDecimal("2381.06"));
        order.setOrderDate(LocalDate.parse("2023-01-02"));

        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Ada Lovelace");
        newOrder.setState("CA");
        newOrder.setProductType("Tile");
        newOrder.setArea(new BigDecimal("249.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("3.50"));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        newOrder.setOrderDate(LocalDate.parse("2023-01-02"));
        assertDoesNotThrow(() -> service.validateOrder(newOrder));
        assertEquals(order, newOrder);

        newOrder.setOrderDate(LocalDate.parse("2019-12-09"));
        assertThrows(OrderDateNotInFutureException.class, () -> {
            service.validateOrder(newOrder);
        });
        newOrder.setOrderDate(LocalDate.parse("2023-01-02"));

        newOrder.setCustomerName("asw@");
        assertThrows(CustomNameErrorException.class, () -> {
            service.validateOrder(newOrder);
        });
        newOrder.setCustomerName("Ada Lovelace");

        newOrder.setState("PA");
        assertThrows(NoSuchStateException.class, () -> {
            service.validateOrder(newOrder);
        });
        newOrder.setState("CA");

        newOrder.setArea(new BigDecimal("-249.00"));
        assertThrows(AreaNumberException.class, () -> {
            service.validateOrder(newOrder);
        });

        newOrder.setArea(new BigDecimal("85.00"));
        assertThrows(AreaNumberException.class, () -> {
            service.validateOrder(newOrder);
        });
    }

    @Test
    void getAllTaxes() {
        Tax tax = new Tax();
        tax.setStateAbbreviation("TX");
        tax.setStateName("Texas");
        tax.setTaxRate(new BigDecimal("4.45"));

        Map<String, Tax> allTaxes = service.getAllTaxes();
        assertNotNull(allTaxes);
        assertEquals(4, allTaxes.size());
        assertTrue(allTaxes.containsValue(tax));
        assertEquals(allTaxes.get("TX"), tax);

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void displayAllOrders() {
    }

    @Test
    public void testCheckOrderFileExists() throws NoSuchOrderException {
        //ARRANGE
        int orderNum = 3;
        Map<Integer, Order> allOrders = service.retrieveAllOrders();
        Order order = new Order();
        order.setCustomerName("Alan Turing");
        order.setState("CA");
        order.setProductType("Wood");
        order.setTaxRate(new BigDecimal("9.25"));
        order.setArea(new BigDecimal("243.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("871.50"));
        order.setLaborCost(new BigDecimal("1033.33"));
        order.setTax(new BigDecimal("476.21"));
        order.setTotal(new BigDecimal("2381.06"));
        order.setOrderDate(LocalDate.parse("2023-01-02"));

        //ACT - add order to new file
        int count = allOrders.size();
        service.addOrder(order);
        allOrders = service.retrieveAllOrders();
        assertNotNull(allOrders);
        assertEquals(count + 1, allOrders.size());
        try {
            service.addOrder(order);
        } catch (NoSuchOrderException e) {
            //If an exception was thrown,the test will fail, if not then it passed.
            fail("Order file did exist. No Exception have been thrown");
        }
    }

        @Test
        public void testCheckOrderFileDoesNotExist() {
            //ACT - add order to new file
            String orderFileName = "Orders_06032030.txt";

            try {
                service.checkOrderFileExists(orderFileName);
                //the file does not exist so an exception should be thrown.
                //if the below line is reached, then an exception was not thrown and the test should fail.
                fail("Order file did not exist. Exception shuld have been thrown");
            } catch (NoSuchOrderException e) {
            }
        }

    }


    }
}