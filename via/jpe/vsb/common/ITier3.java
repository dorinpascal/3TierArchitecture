/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.common;


import via.jpe.vsb.model.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


public interface ITier3
	extends Remote
{
	public Account getAccount( int accountNumber ) throws RemoteException, SQLException;
	public Account updateAccount( Account account ) throws RemoteException,SQLException;
	public Account createAccount(Account account)  throws  RemoteException,SQLException;
	public static final String T3_SERVICE_NAME = "//localhost/T3";

}
