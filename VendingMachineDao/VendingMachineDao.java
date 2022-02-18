/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author urvax
 */
public interface VendingMachineDao {
    VendingMachineItem getItemByIndex(int index) throws VendingMachinePersistenceException;

    List<VendingMachineItem> getInventory() throws VendingMachinePersistenceException;

    int decrementInventory(int index) throws VendingMachinePersistenceException;

    void insertCoin(Coin currency);

    BigDecimal getCurrentFunds();

    void setCurrentFunds(BigDecimal newValue);
}
