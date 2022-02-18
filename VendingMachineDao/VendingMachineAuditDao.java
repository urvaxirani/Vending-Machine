package com.sg.vendingmachine.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author urvax
 */
public interface VendingMachineAuditDao{
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
