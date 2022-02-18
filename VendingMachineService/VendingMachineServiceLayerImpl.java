/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author urvax
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    //@Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    /**
     * Method that contains the logic to purchase an item if
     * the user has entered the necessary funds and the item is in stock.
     * Exceptions are thrown if the user has insufficient funds or item is out of stock
     *  
     * @param index - the index of the item to be purchased
     * @return - the purchased item if all conditions are satisfied
     * @throws VendingMachineNoItemInventoryException - if not enough stock
     * @throws VendingMachinePersistenceException - if there is an error querying our persistent storage
     * @throws VendingMachineInsufficientFundsException  - If user has insufficient funds
     */
    @Override
    public VendingMachineItem purchaseItem(int index)
            throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException, VendingMachineInsufficientFundsException {

        //If the user hasn't inserted any funds, throw an exception
        if ((dao.getCurrentFunds().compareTo(BigDecimal.ZERO)) == 0) {
            throw new VendingMachineInsufficientFundsException("Insert funds before selecting");
        }

        //If item is out of stock, throw an exception
        if (getItemByIndex(index).getQuantity() == 0) {
            throw new VendingMachineNoItemInventoryException("Sold out");
        }
        
        //Use the item's price to compare to our current funds
        BigDecimal itemPrice = new BigDecimal(dao.getItemByIndex(index).getPrice());
        //int res holds the result of the comparison
        int res = dao.getCurrentFunds().compareTo(itemPrice);
        //If the result of the comparison is:
        switch (res) {
            case 0:  // 0, the current funds are equal to the item price
                dao.setCurrentFunds(BigDecimal.ZERO); //Set current funds to zero
                break;
            case 1: //1, the user had more than enough funds. Subtract itemPrice from current funds
                dao.setCurrentFunds(dao.getCurrentFunds().subtract(itemPrice));
                break;
            case -1: //-1, the user has insufficient funds, throw an exception
                auditDao.writeAuditEntry("Insufficient funds to purchase " + dao.getItemByIndex(index).getName());
                throw new VendingMachineInsufficientFundsException("Insufficient funds\n"
                        + dao.getItemByIndex(index).getName() + " costs: $" + dao.getItemByIndex(index).getPrice()
                        + "\nCurrent funds: $" + dao.getCurrentFunds());
        }
        
        //At this point, if no exception was thrown, only case 0 or 1 should have occured
        //Deposit the item purchased by the user
        VendingMachineItem depositedItem = dao.getItemByIndex(index);
        auditDao.writeAuditEntry("Deposited a " + dao.getItemByIndex(index).getName());
        //Decrement the inventory for that item
        dao.decrementInventory(index);
        auditDao.writeAuditEntry(dao.getItemByIndex(index).getName() + " inventory decremented to "
                                                  + dao.getItemByIndex(index).getQuantity());
        return depositedItem;
    }

    /**
     * Get an item by its index value
     * @param index
     * @return the VendingItem
     * @throws VendingMachinePersistenceException - In case there was an error reading from persistent storage
     */
    @Override
    public VendingMachineItem getItemByIndex(int index) throws VendingMachinePersistenceException {
        return dao.getItemByIndex(index);
    }

    /**
     * Returns a list of all VendingItems
     * @return the list of all items
     * @throws VendingMachinePersistenceException  - In case there was an error reading from persistent storage
     */
    @Override
    public List<VendingMachineItem> getInventory() throws VendingMachinePersistenceException {
        return dao.getInventory();
    }

    /**
     * Method to take in a Coin and tell the Dao to add it to the current funds
     * @param currency Coin to be added
     * @throws VendingMachinePersistenceException - if there is an error writing to the audit file
     */
    @Override
    public void insertCoin(Coin currency) throws VendingMachinePersistenceException {
        dao.insertCoin(currency);
        auditDao.writeAuditEntry("Inserted a " + currency.getCoinValue());
    }

    /**
     * Returns our current total funds
     * @return 
     */
    @Override
    public BigDecimal getCurrentFunds() {
        return dao.getCurrentFunds();
    }

    /**
     * Converts the total current funds into
     * quarters, dimes, nickels, and pennies
     * @return 
     */
    @Override
    public String depositChange() {
        String change = Coin.getChangeDue(dao.getCurrentFunds());
        dao.setCurrentFunds(BigDecimal.ZERO);
        return change;
    }
}
