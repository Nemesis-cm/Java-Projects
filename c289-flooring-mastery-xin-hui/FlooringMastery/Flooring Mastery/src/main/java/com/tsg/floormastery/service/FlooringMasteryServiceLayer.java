package com.tsg.floormastery.service;

import com.tsg.floormastery.dao.InvalidDateException;
import com.tsg.floormastery.dao.OrderPersistenceException;
import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.model.Product;
import com.tsg.floormastery.model.Tax;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlooringMasteryServiceLayer {

    void addOrder(Order order);

    void editOrder(Order order);

    void deleteOrder(Integer orderNumber);

    void validateOrder(Order order) throws CustomNameErrorException,
            NoSuchStateException, OrderDateNotInFutureException, AreaNumberException;

    Order removeOrder(Order order);

    Map<String, Tax> getAllTaxes();

    Map<String, Product> getAllProducts();

<<<<<<< HEAD
=======
    List<Order> displayAllOrders(LocalDate date) throws OrderPersistenceException, InvalidDateException;

    void export() throws ExportException;

    Map<Integer, Order> retrieveAllOrders();

    void checkOrderFileExists(String orderFileName);
>>>>>>> 8ba0ce52a1983e141e55bdf165431080ba3d6ce8

}
