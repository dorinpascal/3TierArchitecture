package via.jpe.vsb.tier1.Administrator;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartAdministrator {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        AdministratorTier1 administratorTier1=new AdministratorTier1();
        administratorTier1.Run();
    }
}
