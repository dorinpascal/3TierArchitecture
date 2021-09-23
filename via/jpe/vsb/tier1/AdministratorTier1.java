package via.jpe.vsb.tier1;

import via.jpe.vsb.common.ITier2;
import via.jpe.vsb.common.ITier2Main;
import via.jpe.vsb.model.Account;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static via.jpe.vsb.common.ITier2.T2_SERVICE_NAME;
import static via.jpe.vsb.common.ITier2Main.T2Main_SERVICE_NAME;

public class AdministratorTier1 {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        ITier2Main tier2Main = (ITier2Main) Naming.lookup(T2Main_SERVICE_NAME);
        System.out.println("Give me the location : ");
        Scanner scanner=new Scanner(System.in);
        String y=scanner.nextLine();

        try {
            ITier2 tier2 = tier2Main.getServer(y);

            System.out.println("Type the account number");
          scanner = new Scanner(System.in);
            int account = scanner.nextInt();
            Account account1 = tier2.createAccount(account);

            if (account1 == null) {
                System.out.println("Already taken");
            } else System.out.println(account);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
