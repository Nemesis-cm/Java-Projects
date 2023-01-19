package com.tsg.floormastery.service;

import com.tsg.floormastery.dao.*;
import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.model.Product;
import com.tsg.floormastery.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private OrderDao orderDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    private static Integer orderNumber;

    @Autowired
    public FlooringMasteryServiceLayerImpl(OrderDao orderDao, ProductDao productDao, TaxDao taxDao)  {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        if (orderDao.getAllOrders().size() == 0) {
            orderNumber = 0;
        } else {
            orderNumber = orderDao.getAllOrders().values().stream()
                    .mapToInt(order -> orderNumber).max().getAsInt();
        }
    }

    private static Integer generateOrderNumber() {
        orderNumber++;
        return orderNumber;
    }

    @Override
    public void addOrder(Order order) {
        order.setOrderNumber(generateOrderNumber());
        orderDao.addOrder(order);

    }

    @Override
    public void validateOrder(Order order) throws CustomNameErrorException,
            NoSuchStateException, OrderDateNotInFutureException, AreaNumberException {

        LocalDate ld = LocalDate.now();
<<<<<<< HEAD
        if (ld.isAfter(order.getOrderDate()))
            throw new OrderDateNotInFutureException("Order Date must be in the future.");
        if (order.getCustomerName().length() < 1 || !order.getCustomerName().matches("^[a-zA-Z0-9[.][,]]+"))
=======
        if (ld.isAfter(order.getOrderDate())) throw new OrderDateNotInFutureException("Order Date must be in the future.");
        if(order.getCustomerName().trim().length() < 1 || !order.getCustomerName().matches("^[a-zA-Z0-9[.][,][ ]]+"))
>>>>>>> 8ba0ce52a1983e141e55bdf165431080ba3d6ce8
            throw new CustomNameErrorException("Customer Name – May not be blank and is limited to characters \n"
                    + "[a-z][0-9] as well as periods and comma characters.");
        if (taxDao.getAllTaxes().get(order.getState()) == null)
            throw new NoSuchStateException("No such state.");
        if (order.getArea().compareTo(new BigDecimal("100")) == -1)
            throw new AreaNumberException(" The area must be a positive decimal. Minimum order size is 100 sq ft.");

        fillOutOrder(order);

    }

    @Override
    public Order removeOrder(Order order) {
        order.setOrderNumber(null);
        orderDao.removeOrder(order);
        return null;
    }

    private void fillOutOrder(Order order) {
        order.setTaxRate(taxDao.getAllTaxes().get(order.getState()).getTaxRate());
        order.setMaterialCost(order.getArea().multiply(order.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_EVEN));
        order.setLaborCost(order.getArea().multiply(order.getLaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_EVEN));
        order.setTax(order.getMaterialCost().add(order.getLaborCost()).multiply(order.getTaxRate()).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_EVEN));
        order.setTotal(order.getMaterialCost().add(order.getLaborCost()).add(order.getTax()));
    }

    @Override
    public void editOrder(Order order) {

    }

    @Override
    public void deleteOrder(Integer orderNumber) {

    }

    @Override
    public Map<String, Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public Map<String, Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<Order> displayAllOrders(LocalDate date)throws OrderPersistenceException, InvalidDateException {
        List<Order> orders  = orderDao.getAllOrdersByDate(date);
        return orders;
    }

    @Override
    public Map<Integer, Order> retrieveAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public void export() throws ExportException {



    }
}


