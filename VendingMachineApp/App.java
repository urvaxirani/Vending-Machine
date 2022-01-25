/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineApp;

import VendingMachineController.VendingMachineController;
import VendingMachineDao.VendingMachineAuditDao;
import VendingMachineDao.VendingMachineAuditDaoImpl;
import VendingMachineDao.VendingMachineDao;
import VendingMachineDao.VendingMachineDaoImpl;
import VendingMachineService.VendingMachineServiceLayer;
import VendingMachineService.VendingMachineServiceLayerImpl;
import VendingMachineui.UserIO;
import VendingMachineui.UserIOConsoleImpl;
import VendingMachineui.VendingMachineView;

/**
 *
 * @author urvax
 */
public class App {
    public static void main(String[] args) {
    
    UserIO myIo = new UserIOConsoleImpl();
    // Instantiate the View and wire the UserIO implementation into it
    VendingMachineView myView = new VendingMachineView(myIo);
    // Instantiate the DAO
    VendingMachineDao myDao = new VendingMachineDaoImpl();
    // Instantiate the Audit DAO
    VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
    VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
    // Instantiate the Controller and wire the Service Layer into it
    VendingMachineController controller = new VendingMachineController(myService, myView);
    // Kick off the Controller
    controller.run();
   
    }
}
