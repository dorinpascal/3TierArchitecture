package via.jpe.vsb.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITier1 extends Remote {
    void reply(String result) throws RemoteException;


}
