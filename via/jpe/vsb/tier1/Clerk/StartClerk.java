package via.jpe.vsb.tier1.Clerk;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartClerk {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        ClerkTier1 clerkTier1=new ClerkTier1();
        clerkTier1.Run();
    }
}
