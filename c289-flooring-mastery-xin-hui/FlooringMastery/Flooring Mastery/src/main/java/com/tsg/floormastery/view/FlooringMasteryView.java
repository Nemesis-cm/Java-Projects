package com.tsg.floormastery.view;

import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.model.Product;
import com.tsg.floormastery.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FlooringMasteryView {
    @Autowired
    private UserIO io;
    public final int IS_CONTINUE = 1;  //Indicate user will stay to continue
    public final int IS_CANCEL = 2;  //For cancel delete operation
    private final static String INDENT_FIRST_LEVEL = "      ";
    private final static String INDENT_SECOND_LEVEL = "            ";

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayAddOrderBanner() {
        io.print("Add Order:");
    }
    public void displayRemoveOrderBanner() {
        io.print("Remove Order");}
    public void displayExportAllDataBanner() {
        io.print("=== Export All Data ===");
    }

    public void displayTotalOfOrder(Order order) {
        io.print("The total of Order is: " + order.getTotal());
    }

    public Order retrieveOrderInfo(Map<String, Product> allProducts) {
        String customerName = io.readString(INDENT_FIRST_LEVEL + "Please Enter Customer Name:");

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        do {
            try {
                String orderDate = io.readString(INDENT_FIRST_LEVEL + "Please Enter Order date: (yyyy-MM-dd)");
                currentOrder.setOrderDate(LocalDate.parse(orderDate));
                break;
            } catch (DateTimeParseException e) {
                displayErrorMessage("Date format is (yyyy-MM-dd). Please enter again.");
            }
        } while (true);

        String state = io.readString(INDENT_FIRST_LEVEL + "Please Enter State Abbreviation:");
        currentOrder.setState(state);

        int count = 0, index;
        List<Product> proList = new ArrayList<>();
        for (Product product : allProducts.values()) {
            io.print(++count + ". " + product.getProductType());
            proList.add(product);
        }
        index = io.readInt("Please select from the above choices.", 1, count);
        currentOrder.setProductType(proList.get(index).getProductType());
        currentOrder.setCostPerSquareFoot(proList.get(index).getCostPerSquareFoot());
        currentOrder.setLaborCostPerSquareFoot(proList.get(index).getLaborCostPerSquareFoot());

        String area = io.readString(INDENT_FIRST_LEVEL + "Please Enter Area in Square:");
        currentOrder.setArea(new BigDecimal(area).setScale(2, RoundingMode.HALF_EVEN));
        return currentOrder;
    }

    public int retrieveContinueAdd() {
        io.print(INDENT_FIRST_LEVEL + "Do you want to add this Order? ");
        io.print(INDENT_SECOND_LEVEL + "1-continue");
        io.print(INDENT_SECOND_LEVEL + "2-return");
        return io.readInt(INDENT_FIRST_LEVEL + "Please select from the above choices.", 1, 2);
    }
    public int retrieveContinueEdit() {

        return retrieveContinueSign("edit");
    }
    public int retrieveContinueDelete() {
        return retrieveContinueSign("delete");
    }
    public int retrieveContinueSearch() {
        return retrieveContinueSign("search");
    }
    public int retrieveContinueFind() {
        return retrieveContinueSign("find");
    }
    public int retrieveContinueDisplay() {
        return retrieveContinueSign("display");
    }
    private int retrieveContinueSign(String actionName) {
        io.print(INDENT_FIRST_LEVEL + "Do you want to " + actionName + " this Order? ");
        io.print(INDENT_SECOND_LEVEL + "1-continue");
        io.print(INDENT_SECOND_LEVEL + "2-return");
        return io.readInt(INDENT_FIRST_LEVEL + "Please select from the above choices.", 1, 2);
    }

    public void displayAddSuccessBanner() {
        io.print(INDENT_FIRST_LEVEL + "Order added. ");
    }

    private String enterToContinue() {
        return io.readString("\nPress enter to continue.");
    }

    public String confirmRemove(Order order) {
        retrieveOrderInfo((Map<String, Product>) order);
        return io.readString("Do you want to continue with removal? (Y/N)");
    }
    public String displayRemoveCancelled() {
        io.print(" Remove an Order Cancelled");
        return enterToContinue();
    }

    public String displayRemoveSuccess() {
        io.print("Order successfully removed");
        return enterToContinue();
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");


    }

    public LocalDate listOfOrdersPrompt() {
        return io.readDate("Please input the date you want to view (MM-DD-YYYY)",
                LocalDate.of(2000, 1, 1), LocalDate.of(2100, 1, 1));
    }
    public void listOfOrdersByDate(List<Order> theList) {
        io.print("----Date:"+theList.get(0).getOrderDate()+"----");
        theList.forEach(o->{
            io.print("Order number:"+o.getOrderNumber()+"\n"
                    +"Customer Name:"+o.getCustomerName()+"\n"
                    +"State:"+o.getState()+"\n"
                    +"Tax rate:"+o.getTaxRate()+"\n"
                    +"Product type:"+o.getProductType()+"\n"
                    +"Area:"+o.getArea()+"\n"
                    +"Cost per square foot:"+o.getCustomerName()+"\n"
                    +"Labor cost per square foot:"+o.getCostPerSquareFoot()+"\n"
                    +"Material cost:"+o.getMaterialCost()+"\n"
                    +"Labor cost:"+o.getLaborCost()+"\n"
                    +"Tax:"+o.getTax()+"\n"
                    +"-----TOTAL-----:"+o.getTotal()+"\n");
        });
    }

}
