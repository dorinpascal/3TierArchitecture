/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.tier2.branches;


import via.jpe.vsb.common.ITier2;
import via.jpe.vsb.common.ITier3;
import via.jpe.vsb.model.Account;

import static via.jpe.vsb.common.ITier3.T3_SERVICE_NAME;

import java.rmi.Naming;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;



public class Tier2Controller
        extends UnicastRemoteObject
        implements ITier2 {
    private ITier3 tier3;


    public Tier2Controller(String arg)
            throws RemoteException {
        try {


            Naming.rebind(T2_SERVICE_NAME + arg, this);

            tier3 = (ITier3) Naming.lookup(T3_SERVICE_NAME);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }


    public Account withdraw(int accountNumber, double amount)
            throws RemoteException, SQLException {
        Account account = tier3.getAccount(accountNumber);

        if (account == null)
            return null;
        else if (amount <= 0.0 || amount > account.getBalance())
            return null;
        else {
            account.updateBalance(-amount);
            return tier3.updateAccount(account);


        }
    }

    @Override
    public Account insert(int accountNumber, double amount) throws RemoteException, SQLException {
        Account account = tier3.getAccount(accountNumber);
        if (account == null)
            return null;
        else {
            account.updateBalance(amount);
            return tier3.updateAccount(account);
        }

    }

    @Override
    public Account createAccount(int accountNumber) throws RemoteException, SQLException {
        Account account = new Account(accountNumber, 0.0);
        return tier3.createAccount(account);
    }
}
