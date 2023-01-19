package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.model.Tax;
import org.springframework.cglib.core.Local;

import java.io.IOException;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    void addOrder(Order order);

    List<Order> getAllOrdersByDate(LocalDate date) throws OrderPersistenceException,InvalidDateException;

    Map<Integer, Order> getAllOrders();

    Order getOrder(Integer orderNumber);

    //void editOrder(Order order);
    Order editOrder(Order o) throws OrderPersistenceException, InvalidDateException;

    void deleteOrder(Integer orderNumber);

    void removeOrder(Order order);

    Map<String,List<Order>> getExportData() throws OrderPersistenceException, InvalidDateException;

}
