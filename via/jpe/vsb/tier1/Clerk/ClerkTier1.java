package via.jpe.vsb.tier1.Clerk;

import via.jpe.vsb.common.ITier1;
import via.jpe.vsb.common.ITier2;
import via.jpe.vsb.common.ITier2Main;
import via.jpe.vsb.model.Account;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


import static via.jpe.vsb.common.ITier2Main.T2Main_SERVICE_NAME;

public class ClerkTier1 implements ITier1 {
private ITier1 iTier1;
   public void Run() throws RemoteException, NotBoundException, MalformedURLException {
       iTier1 = (ITier1) UnicastRemoteObject.exportObject(this, 0);
        ITier2Main tier2Main = (ITier2Main) Naming.lookup(T2Main_SERVICE_NAME);
        System.out.println("Give me the location : ");
        Scanner scanner=new Scanner(System.in);
        String y=scanner.nextLine();
        try {
            ITier2 tier2 = tier2Main.getServer(y);

            while (true) {
                System.out.println("What is your account number?");
               scanner = new Scanner(System.in);
                String account = scanner.nextLine();

                if (account.equals("exit")) {
                    break;
                } else {
                    System.out.println("Choose the operation W/I :");
                    Scanner operation = new Scanner(System.in);
                    String typed = operation.nextLine();
                    if (typed.equals("W") || typed.equals("w")) {
                        System.out.println("Amount to withdraw : ");
                        Scanner scanner1 = new Scanner(System.in);
                        double amount = scanner1.nextDouble();

                        tier2.saveClient(account, this);
                        Account temp = tier2.withdraw(Integer.parseInt(account), amount);
                        if (temp == null) {
                            System.out.println("Insufficient funds");
                        } else {
                            System.out.println("The new balance " + temp.getBalance());
                        }

                    }
                    else if (typed.equals("I") || typed.equals("i"))
                    {
                        System.out.println("Amount to insert : ");
                        Scanner scanner1 = new Scanner(System.in);
                        double amount = scanner1.nextDouble();
                        tier2.saveClient(account, this);
                        Account temp=tier2.insert(Integer.parseInt(account),amount);
                        System.out.println("The new balance " + temp.getBalance());
                    }


                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void reply(String result) throws RemoteException {
        System.out.println(result);
    }
}
