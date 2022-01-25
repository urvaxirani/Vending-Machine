/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDao.VendingMachineAuditDao;
import VendingMachineDao.VendingMachinePersistenceException;

/**
 *
 * @author urvax
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException{
        //Does nothing
    }

}   
