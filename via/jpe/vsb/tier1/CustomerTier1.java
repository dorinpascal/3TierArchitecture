package via.jpe.vsb.tier1;


import via.jpe.vsb.common.ITier2;
import via.jpe.vsb.common.ITier2Main;
import via.jpe.vsb.model.Account;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static via.jpe.vsb.common.ITier2Main.T2Main_SERVICE_NAME;


public class CustomerTier1 {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {


        ITier2Main tier2Main = (ITier2Main) Naming.lookup(T2Main_SERVICE_NAME);
        System.out.println("Give me the location : ");
        Scanner scanner=new Scanner(System.in);
        String y=scanner.nextLine();
        try {

            ITier2 tier2=tier2Main.getServer(y);

            while (true) {
                System.out.println("What is your account number?");

                String account = scanner.nextLine();

                if (account.equals("exit")) {
                    break;
                } else {

                    System.out.println("Amount to withdraw : ");
                    Scanner scanner1 = new Scanner(System.in);
                    double amount = scanner1.nextDouble();

                    Account temp = tier2.withdraw(Integer.parseInt(account), amount);
                    if (temp == null) {
                        System.out.println("Insufficient funds");
                    } else {
                        System.out.println("The new balance " + temp.getBalance());
                    }

                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
