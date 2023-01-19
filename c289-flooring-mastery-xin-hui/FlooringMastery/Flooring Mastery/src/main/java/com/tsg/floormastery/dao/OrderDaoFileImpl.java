package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Order;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class OrderDaoFileImpl implements OrderDao{
    private Map<Integer, Order> allOrders = new HashMap<>();
    public static final String DELIMITER = ",";
    private final String ORDER_FILE;
    public OrderDaoFileImpl(){
        this.ORDER_FILE = "Orders/";
    }

    public OrderDaoFileImpl(String filePath) {
        this.ORDER_FILE = filePath;
    }

    // File name modifier
//    private String fileNameModifier(String date) throws InvalidDateException{
//        LocalDate fileName = LocalDate.parse(date,formatter);
//        // Order date must be in the future
//        if(fileName.isBefore(LocalDate.now())){
//            throw new InvalidDateException(" Date is invalid ");
//        }else{
//            String newfileName = fileName.format(formatter);
//            return "Orders_"+newfileName+".txt";
//        }
//
//    }


    @Override
    public Map<Integer, Order> getAllOrders() {
        return allOrders;
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) throws OrderPersistenceException,InvalidDateException{
        return loadOrdersFromFile(date);
    }

    private List<Order> loadOrdersFromFile(LocalDate date) throws OrderPersistenceException, InvalidDateException{
        //allOrders = new HashMap<>();
        Scanner loader;
        String chosenDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        File file = new File(this.ORDER_FILE + "Orders_"+chosenDate+".txt");

        if(file.isFile()) {
            try {
                loader = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new OrderPersistenceException("Could not load file", e);
            }

            String line;
            String[] token;
            loader.nextLine(); //Skip header

            while (loader.hasNextLine()) {
                line = loader.nextLine();
                token = line.split(DELIMITER);


                int orderNumber = Integer.parseInt(token[0]);
                String customerName = token[1];
                String state = token[2];
                BigDecimal taxRate = new BigDecimal(token[3]);
                String productType = token[4];
                BigDecimal area = new BigDecimal(token[5]);
                BigDecimal costPerSquareFoot = new BigDecimal(token[6]);
                BigDecimal laborCostPerSquareFoot = new BigDecimal(token[7]);
                BigDecimal materialCost = new BigDecimal(token[8]);
                BigDecimal laborCost = new BigDecimal(token[9]);
                BigDecimal tax = new BigDecimal(token[10]);
                BigDecimal total = new BigDecimal(token[11]);

                Order currentOrder = new Order();

                currentOrder.setOrderDate(LocalDate.parse(chosenDate,DateTimeFormatter.ofPattern("MMddyyyy")));
                currentOrder.setOrderNumber(orderNumber);
                currentOrder.setCustomerName(customerName);
                currentOrder.setState(state);
                currentOrder.setTaxRate(taxRate);
                currentOrder.setProductType(productType);
                currentOrder.setArea(area);
                currentOrder.setCostPerSquareFoot(costPerSquareFoot);
                currentOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
                currentOrder.setMaterialCost(materialCost);
                currentOrder.setLaborCost(laborCost);
                currentOrder.setTax(tax);
                currentOrder.setTotal(total);
                this.allOrders.put(currentOrder.getOrderNumber(), currentOrder);
            }
            loader.close();
           // return new ArrayList<Order>(this.allOrders.values());
            return new ArrayList<Order>(this.allOrders.values());
        }else {
            return new ArrayList<Order>(this.allOrders.values());
        }
    }

    public Order editOrder(Order o) throws OrderPersistenceException, InvalidDateException{
        this.loadOrdersFromFile(o.getOrderDate());
        Order order=this.allOrders.replace(o.getOrderNumber(),o);
        writeOrderToFile(o);
        return order;
    }

    private void writeOrderToFile(Order order) throws OrderPersistenceException{

        PrintWriter out;
        File file;

        try {
            file = new File(this.ORDER_FILE+"Orders_"+order.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt");
            out = new PrintWriter(new FileWriter(file.getAbsolutePath()));
        }
        catch(Exception e) {
            throw new OrderPersistenceException("Could not save Order data", e);
        }

        String line="OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n";
        for(Order eachOrder:orders) {
            line += eachOrder.getOrderNumber() + DELIMITER;
            line += eachOrder.getCustomerName() + DELIMITER;
            line += eachOrder.getState() + DELIMITER;
            line += eachOrder.getTaxRate() + DELIMITER;
            line += eachOrder.getProductType() + DELIMITER;
            line += eachOrder.getArea() + DELIMITER;
            line += eachOrder.getCostPerSquareFoot() + DELIMITER;
            line += eachOrder.getLaborCostPerSquareFoot() + DELIMITER;
            line += eachOrder.getMaterialCost() + DELIMITER;
            line += eachOrder.getLaborCost() + DELIMITER;
            line += eachOrder.getTax() + DELIMITER;
            line += eachOrder.getTotal();
            out.println(line);
            out.flush();
            line="";
        }
        out.close();
    }

    @Override
    public void addOrder(Order order) {
        allOrders.put(order.getOrderNumber(), order);
    }


    @Override
    public Order getOrder(Integer orderNumber) {
        return allOrders.get(orderNumber);
    }

//    @Override
//    public void editOrder(Order order) {
//
//    }

    @Override
    public void deleteOrder(Integer orderNumber) {

    }

    @Override
    public Order removeOrder(int orderNumber) {

        Order toRemove = allOrders.remove(orderNumber);

        boolean removed = allOrders.get(orderNumber).remove(toRemove);
        if (removed) {
            return toRemove;
        } else {
        return null;
    }

        }

    public Map<String,List<Order>> getExportData() throws OrderPersistenceException, InvalidDateException {
        Map<String,List<Order>> exportDataMap = new HashMap<>();
        //Get a list of all the orders from all files
        LocalDate File = null;
        List<Order> allOrderFiles = this.loadOrdersFromFile(File);

        for (Order orderFile: allOrderFiles) {
            //Get the order date from the file name
            String dateString = this.getAllOrdersByDate(File).toString();
            //Get the order list from the file
            List<Order> allOrdersForFile = this.getAllOrdersByDate(orderFile.getOrderDate());
            //Add to the map the date and orders
            exportDataMap.put(dateString,allOrdersForFile);
        }
        return exportDataMap;
    }

    @Override
    public void exportAllData() throws OrderPersistenceException {
        Order File = new Order();
        this.writeOrderToFile(File);
    }



}

=======
}
>>>>>>> 8ba0ce52a1983e141e55bdf165431080ba3d6ce8
