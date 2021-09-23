package via.jpe.vsb.tier2.mainServer;

import via.jpe.vsb.common.ITier2;

import via.jpe.vsb.common.ITier2Main;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

import static via.jpe.vsb.common.ITier2.T2_SERVICE_NAME;


public class Tier2MainController extends UnicastRemoteObject implements ITier2Main {


    public Tier2MainController() throws RemoteException {

        try {
            Naming.rebind(T2Main_SERVICE_NAME, this);


        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public ITier2 getServer(String location) throws MalformedURLException, NotBoundException, RemoteException {
        return (ITier2) Naming.lookup(T2_SERVICE_NAME + location);
    }
}
