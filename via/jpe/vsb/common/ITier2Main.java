package via.jpe.vsb.common;



import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITier2Main extends Remote {
    public static final String T2Main_SERVICE_NAME = "//localhost/T2Main" ;
    public ITier2 getServer(String location)throws MalformedURLException, NotBoundException, RemoteException;
}
