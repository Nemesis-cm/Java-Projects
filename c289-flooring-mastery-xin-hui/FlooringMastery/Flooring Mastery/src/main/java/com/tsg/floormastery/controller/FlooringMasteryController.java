package com.tsg.floormastery.controller;

import com.tsg.floormastery.dao.InvalidDateException;
import com.tsg.floormastery.dao.OrderPersistenceException;
import com.tsg.floormastery.model.Order;
import com.tsg.floormastery.service.*;
import com.tsg.floormastery.view.FlooringMasteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 8ba0ce52a1983e141e55bdf165431080ba3d6ce8

@Controller
public class FlooringMasteryController {
    @Autowired
    private FlooringMasteryView view;
    @Autowired
    private FlooringMasteryServiceLayer service;

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getAllOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        //editOrder();
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addOrder() {
        int returnSign;
        Order newOrder;

        try {
            view.displayAddOrderBanner();
            newOrder = view.retrieveOrderInfo(service.getAllProducts());
            service.validateOrder(newOrder);
            view.displayTotalOfOrder(newOrder);
            if (view.retrieveContinueAdd() != view.IS_CONTINUE) {
                return;
            }
            service.addOrder(newOrder);
            view.displayAddSuccessBanner();

        } catch (CustomNameErrorException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (NoSuchStateException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (OrderDateNotInFutureException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (AreaNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void removeOrder()  {

        Order removeOrder;

        view.displayRemoveOrderBanner();
        LocalDate date = ();
        int orderNumber = view.displayTotalOfOrder(addOrder());
        Order toRemove = service.ValidateOrder(removeOrder);
        String confirmRemove = view.confirmRemove(toRemove);

        if (confirmRemove.equalsIgnoreCase("y") || confirmRemove.equalsIgnoreCase("yes")) {
            Order removed = service.removeOrder(orderNumber);
            view.displayRemoveSuccess();
        } else {
            view.displayRemoveCancelled();
        }
    }

    private int getMenuSelection()  {
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void getAllOrders() throws OrderPersistenceException, InvalidDateException {
        //view.getAllOrdersBanner();
        LocalDate orderDate = view.listOfOrdersPrompt();
        List<Order> orderList = service.displayAllOrders(orderDate);
        //view.getAllOrdersSuccessBanner();
        view.listOfOrdersByDate(orderList);
    }

}
