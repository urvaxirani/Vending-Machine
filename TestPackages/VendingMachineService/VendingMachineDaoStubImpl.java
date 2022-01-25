/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDao.VendingMachineDao;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachineDto.Coin;
import VendingMachineDto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author urvax
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    //Stub has only one item
    public VendingMachineItem onlyItem;
    //Stub still keeps track of change
    public BigDecimal CurrentChange = BigDecimal.ZERO;

    
    //Alternative constructor that takes in a preinitialized VendingItem
    public VendingMachineDaoStubImpl(VendingMachineItem onlyItem) {
        this.onlyItem = onlyItem;
    }

    //Default constructor initializes a VendingItem
    public VendingMachineDaoStubImpl() {
        onlyItem = new VendingMachineItem();
        onlyItem.setItemNumber(1);
        onlyItem.setName("Snickers");
        onlyItem.setPrice("1.25");
        onlyItem.setQuantity(10);
    }

    //Stub get  item by index
    @Override
    public VendingMachineItem getItemByIndex(int index) throws VendingMachinePersistenceException {
        if (onlyItem.getItemNumber() == index) {
            return onlyItem;
        } else {
            return null;
        }
    }
    
    //Stub that adds onlyItem to list and returns it
    @Override
    public List<VendingMachineItem> getInventory() throws VendingMachinePersistenceException {
        List<VendingMachineItem> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    //decrements quantity of an item in inventory 
    @Override
    public int decrementInventory(int index) throws VendingMachinePersistenceException {
        if (onlyItem.getItemNumber() == index) {
            onlyItem.setQuantity(onlyItem.getQuantity() - 1);
            return onlyItem.getQuantity();
        } else {
            return -1;
        }
    }

    //Add coin value to our current total funds
    @Override
    public void insertCoin(Coin currency) {
        CurrentChange = CurrentChange.add(Coin.getCoinValueInBigDecimal(currency));
    }

    //Return our current funds
    @Override
    public BigDecimal getCurrentFunds() {
        return CurrentChange;
    }

    //Set out current funds
    @Override
    public void setCurrentFunds(BigDecimal newValue) {
        CurrentChange = newValue;
    }
}
