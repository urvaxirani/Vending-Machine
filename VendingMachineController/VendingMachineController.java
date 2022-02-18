package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.VendingMachineItem;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;



/**
 *
 * @author urvax
 */
public class VendingMachineController {
    private VendingMachineServiceLayer service;
    private VendingMachineView view;

    //@Autowired
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    //Where main method from App sends us first
    public void run() {
        //Boolean to keep program running until we choose to exit
        boolean keepGoing = true;
        //int value which will be set by the user to choose a program function from the menu
        int menuSelection;
        try {
            //Until user presses '3' keep prompting the user for menu choice 
            while (keepGoing) {
                //Display current inventory
                view.displayInventory(service.getInventory());
                //Display current funds
                view.displayCurrentFunds(service.getCurrentFunds());
                //Get menu choice from the user
                menuSelection = view.displayMenuSelection();
                switch (menuSelection) {
                    case 1: //Purchase an item
                        try {
                         view.displayItemNumbers(service.getInventory());
                        //Choice can be between 1 to the size of the inventory
                        int choice = view.getItemChoice(service.getInventory().size());
                        purchaseItem(choice);
                        //Deposit change after purchasing **Per program requirements**
                        view.displayDepositChange(service.depositChange());
                        //Possible errors include not having inserted any coins/insufficient coins
                        //and not having any of a chosen item in stock
                    } catch (VendingMachineInsufficientFundsException
                            | VendingMachinePersistenceException
                            | VendingMachineNoItemInventoryException e) {
                        view.displayErrorMessage(e.getMessage());
                    }
                    break;
                    case 2: //Insert money into the vending machine
                        insertCoins();
                        break;
                    case 3://Exit
                        //and deposit any unused change
                        view.displayDepositChange(service.depositChange());
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Method to purchase a VendingItem
     * @param index - of the item to be vended
     * @throws VendingNoItemInventoryException - In case the item is out of stock
     * @throws VendingPersistenceException - In case there is a problem reading from persistent storage
     * @throws VendingInsufficientFundsException  - In case the user hasn't inserted enough change
     */
    private void purchaseItem(int index)
            throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException, VendingMachineInsufficientFundsException {
        VendingMachineItem depositedItem = service.purchaseItem(index);
        view.displayDepositPurchasedItem(depositedItem);
    }

    /**
     * Method to insert a Coin to be added to total funds
     * @throws VendingPersistenceException - In case there are errors writing to the audit file
     */
    public void insertCoins() throws VendingMachinePersistenceException {
        boolean keepGoing = true;
        int menuChoice;

        view.displayInsertCoinBanner();
        //Keep prompting to add coins until the user has finished
        while (keepGoing) {
            view.displayCurrentFunds(service.getCurrentFunds());
            menuChoice = view.displayInsertCoins();
            switch (menuChoice) {
                case 1:
                    service.insertCoin(Coin.PENNY);
                    break;
                case 2:
                    service.insertCoin(Coin.NICKEL);
                    break;
                case 3:
                    service.insertCoin(Coin.DIME);
                    break;
                case 4:
                    service.insertCoin(Coin.QUARTER);
                    break;
                case 5:
                    keepGoing = false;
                    break;
            }
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
