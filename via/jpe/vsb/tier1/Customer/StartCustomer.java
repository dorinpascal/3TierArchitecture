package via.jpe.vsb.tier1.Customer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartCustomer {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        CustomerTier1 customerTier1=new CustomerTier1();
        customerTier1.Run();
    }
}
