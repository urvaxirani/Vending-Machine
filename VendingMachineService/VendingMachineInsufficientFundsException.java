/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

/**
 *
 * @author urvax
 */
public class VendingMachineInsufficientFundsException extends Exception{
    
    public VendingMachineInsufficientFundsException(String message){
        super(message);
    }

    public VendingMachineInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
