/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;

/**
 *
 * @author urvax
 */
public interface VendingMachineServiceLayer{
    
    VendingMachineItem purchaseItem(int index) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException, VendingMachineInsufficientFundsException;

    VendingMachineItem getItemByIndex(int index) throws VendingMachinePersistenceException;

    List<VendingMachineItem> getInventory() throws VendingMachinePersistenceException;

    void insertCoin(Coin currency) throws VendingMachinePersistenceException;
    
    BigDecimal getCurrentFunds();
    
    String depositChange();
}
