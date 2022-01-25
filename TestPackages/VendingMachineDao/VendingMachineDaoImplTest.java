/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDto.Coin;
import VendingMachineDto.VendingMachineItem;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author urvax
 */
//@Component
public class VendingMachineDaoImplTest {
     VendingMachineDao testDao;
    public VendingMachineDaoImplTest() {
    }
    
     /**
     * Before each test create a test file with two items
     * @throws Exception 
     */
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testInventory.txt";
        String DELIMITER = "::";
        testDao = new VendingMachineDaoImpl(testFile);

        PrintWriter out = new PrintWriter(new FileWriter(testFile));

        String itemAsText1;
        itemAsText1 = "1" + DELIMITER;
        itemAsText1 += "Snickers" + DELIMITER;
        itemAsText1 += "1.25" + DELIMITER;
        itemAsText1 += "2";

        String itemAsText2;
        itemAsText2 = "2" + DELIMITER;
        itemAsText2 += "Dingleberry" + DELIMITER;
        itemAsText2 += "2.00" + DELIMITER;
        itemAsText2 += "5";

        out.println(itemAsText1);
        out.flush();
        out.println(itemAsText2);
        out.flush();
        // Clean up
        out.close();
    }

    @Test
    public void testGetInventory() throws Exception {
        VendingMachineItem testItem1 = new VendingMachineItem();
        testItem1.setItemNumber(1);
        testItem1.setName("Snickers");
        testItem1.setPrice("1.25");
        testItem1.setQuantity(2);

        VendingMachineItem testItem2 = new VendingMachineItem();
        testItem2.setItemNumber(2);
        testItem2.setName("Dingleberry");
        testItem2.setPrice("2.00");
        testItem2.setQuantity(5);

        List<VendingMachineItem> inventory = testDao.getInventory();

        assertNotNull(inventory, "The inventory list must not null");
        assertEquals(2, inventory.size(), "List of VendingItems should be 2");

        assertEquals(testItem1.getItemNumber(),
                1,
                "Checking Item Id");
        assertEquals(testItem1.getName(),
                "Snickers",
                "Checking Item Name");
        assertEquals(testItem1.getPrice(),
                "1.25",
                "Checking Item Price");
        assertEquals(testItem1.getQuantity(),
                2,
                "Checking Item Quantity");

        assertEquals(testItem2.getItemNumber(),
                2,
                "Checking Item Id");
        assertEquals(testItem2.getName(),
                "Dingleberry",
                "Checking Item Name");
        assertEquals(testItem2.getPrice(),
                "2.00",
                "Checking Item Price");
        assertEquals(testItem2.getQuantity(),
                5,
                "Checking Item Quantity");
    }

    @Test
    public void testGetItemByIndex() throws Exception {
        VendingMachineItem testItem1 = new VendingMachineItem();
        testItem1.setItemNumber(1);
        testItem1.setName("Snickers");
        testItem1.setPrice("1.25");
        testItem1.setQuantity(2);

        VendingMachineItem testItem2 = new VendingMachineItem();
        testItem2.setItemNumber(2);
        testItem2.setName("Dingleberry");
        testItem2.setPrice("2.00");
        testItem2.setQuantity(5);

        VendingMachineItem testRetrieveItemByIndex1 = testDao.getItemByIndex(1);
        VendingMachineItem testRetrieveItemByIndex2 = testDao.getItemByIndex(2);

        assertEquals(testItem1.getItemNumber(),
                testRetrieveItemByIndex1.getItemNumber(),
                "Checking Item Id");
        assertEquals(testItem1.getName(),
                testRetrieveItemByIndex1.getName(),
                "Checking Item Name");
        assertEquals(testItem1.getPrice(),
                testRetrieveItemByIndex1.getPrice(),
                "Checking Item Price");
        assertEquals(testItem1.getQuantity(),
                testRetrieveItemByIndex1.getQuantity(),
                "Checking Item Quantity");

        assertEquals(testItem2.getItemNumber(),
                testRetrieveItemByIndex2.getItemNumber(),
                "Checking Item Id");
        assertEquals(testItem2.getName(),
                testRetrieveItemByIndex2.getName(),
                "Checking Item Name");
        assertEquals(testItem2.getPrice(),
                testRetrieveItemByIndex2.getPrice(),
                "Checking Item Price");
    }

    @Test
    public void testdecrementInventory() throws Exception {
        VendingMachineItem testItem1 = testDao.getItemByIndex(1);
        assertEquals(testDao.decrementInventory(testItem1.getItemNumber()), 1, "Item quantity was 2 but should be 1 after decrementing");
    }

    @Test
    public void testCoinFunctionality() throws Exception {
        testDao.insertCoin(Coin.PENNY);
        testDao.insertCoin(Coin.DIME);
        testDao.insertCoin(Coin.NICKEL);
        testDao.insertCoin(Coin.QUARTER);
        assertEquals(testDao.getCurrentFunds().toString(), "0.41", "Adding one of every coin should add to 0.41");
        
        testDao.setCurrentFunds(BigDecimal.ZERO);
        assertEquals(testDao.getCurrentFunds().toString(), "0", "Current funds should be zero");
    }

}
