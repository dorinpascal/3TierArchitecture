/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.tier2.branches;


import via.jpe.vsb.common.ITier1;
import via.jpe.vsb.common.ITier2;
import via.jpe.vsb.common.ITier3;
import via.jpe.vsb.model.Account;

import static via.jpe.vsb.common.ITier3.T3_SERVICE_NAME;

import java.rmi.Naming;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tier2Controller
        extends UnicastRemoteObject
        implements ITier2 {
    private ITier3 tier3;
    private HashMap<String, List<ITier1>> users = new HashMap<String, List<ITier1>>();


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
            send(account);
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
            send(account);
            return tier3.updateAccount(account);
        }

    }

    @Override
    public Account createAccount(int accountNumber) throws RemoteException, SQLException {
        Account account = new Account(accountNumber, 0.0);
        send(account);
        return tier3.createAccount(account);
    }

    @Override
    public void saveClient(String msg, ITier1 customer)throws RemoteException {
        ArrayList<ITier1> tier1List = new ArrayList<>();
        tier1List.add(customer);
        if (users.containsKey(msg))
        {
            if (!users.get(msg).contains(customer))
            {
                users.get(msg).add(customer);
            }
        }
        else users.put(msg,tier1List);

    }


    private void send(Account account) {

        for (ITier1 tier1 : users.get(String.valueOf(account.getNumber()))
        ) {
            try {
                tier1.reply(account.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


}
