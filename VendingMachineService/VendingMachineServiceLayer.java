/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDto.Coin;
import VendingMachineDto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachineService.VendingMachineInsufficientFundsException;

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
