/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.tier3;


import via.jpe.vsb.common.ITier3;
import via.jpe.vsb.model.Account;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;


public class Tier3Controller
        extends UnicastRemoteObject
        implements ITier3 {

    private final Statement statement = getConnection().createStatement();

    public Tier3Controller()
            throws RemoteException, SQLException {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind(T3_SERVICE_NAME, this);

            DriverManager.registerDriver(new org.postgresql.Driver());


        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }


    public Account getAccount(int accountNumber)
            throws RemoteException, SQLException {
        Account temp = null;


        ResultSet resultSet = statement.executeQuery("SELECT * from Account where account_number=" + accountNumber + ";");

        while (resultSet.next()) {
            temp = new Account(resultSet.getInt("account_number"), resultSet.getDouble("balance"));
        }
        System.out.println(temp.getNumber());

        return temp;
    }


    public Account updateAccount(Account account)
            throws RemoteException, SQLException {
        statement.execute("UPDATE account SET balance=" + account.getBalance() + " WHERE account_number=" + account.getNumber() + ";");
        return getAccount(account.getNumber());
    }

    @Override
    public Account createAccount(Account account) throws RemoteException, SQLException {

        try {
            statement.execute("INSERT INTO Account(account_number, balance) VALUES (" + account.getNumber() + "," + 0.0 + ") ;");
            return getAccount(account.getNumber());
        } catch (SQLException e) {
            return null;
        }


    }


    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "455946DP23";
        return DriverManager.getConnection(url, username, password);
    }
}
