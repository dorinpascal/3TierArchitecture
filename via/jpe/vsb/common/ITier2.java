/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.common;


import via.jpe.vsb.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


public interface ITier2
        extends Remote {
    public Account withdraw(int accountNumber, double amount) throws RemoteException, SQLException;
    public Account insert(int accountNumber,double amount) throws RemoteException,SQLException;
    public Account createAccount(int accountNumber) throws RemoteException,SQLException;
    public static final String T2_SERVICE_NAME = "//localhost/T2";


}
