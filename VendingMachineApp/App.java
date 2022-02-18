/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author urvax
 */
public class App {

    public static void main(String[] args) {

        //UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        //VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        //VendingMachineDao myDao = new VendingMachineDaoImpl();
        // Instantiate the Audit DAO
        //VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        //VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        //VendingMachineController controller = new VendingMachineController(myService, myView);
        // Kick off the Controller
        //controller.run();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller
                = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
