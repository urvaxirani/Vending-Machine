/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author urvax
 */
public class VendingMachineView{
    private UserIO io;
    
    //@Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayInventory(List<VendingMachineItem> inventory) {
        io.print("            VENDING MACHINE 3000");
        io.print("              CURRENT INVENTORY");
        io.print("*****Item-#******Item******Quantity****Price*****");
        inventory.forEach(currentItem -> {
            io.print("        " + currentItem.getItemNumber() + "      " + currentItem.getName() + "       " + currentItem.getQuantity() + "       " + "$" + currentItem.getPrice());
        });
        io.print("************************************************");
    }

    public int displayMenuSelection() {
        io.print("1. Buy Item");
        io.print("2. Insert Coin");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }

    public void displayItemNumbers(List<VendingMachineItem> inventory){
        io.print("Select the Item number from the list of items below");
        io.print("Item# - Item name");
        inventory.forEach(currentItem -> {
            io.print(currentItem.getItemNumber() + "     - " + currentItem.getName());
        });
    }
    
    public int getItemChoice(int numOfChoices) {
        
                return io.readInt("Enter item-#", 1, numOfChoices);
    }

    public void displayDepositPurchasedItem(VendingMachineItem depositedItem) {
        io.print(depositedItem.getName() + " deposited");
        io.readString("Press Enter to grab your " + depositedItem.getName());
    }

    public void displayInsertCoinBanner() {
        io.print("Enter Coins");
    }

    public int displayInsertCoins() {
        io.print("1) A Penny");
        io.print("2) A Nickel");
        io.print("3) A Dime");
        io.print("4) A Quarter");
        io.print("5) Done adding coins");

        return io.readInt("Select choice from menu", 1, 5);
    }

    public void displayCurrentFunds(BigDecimal coins) {
        io.print("Current funds: " + coins.toString());
    }

    public void displayDepositChange(String change) {
        String[] stringTokens = change.split("::");
        for (String s : stringTokens) {
            io.print(s);
        }
        io.readString("Press enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("Press Enter to continue");
    }
}
