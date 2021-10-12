/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.model;


import java.io.Serializable;


public class Account
	implements Serializable
{
	private int number;
	private double balance;
	
	
	public Account( int number, double balance )
	{
		this.number = number;
		this.balance = balance;
	}
	
	
	public int getNumber()
	{
		return number;
	}
	
	
	public double getBalance()
	{
		return balance;
	}
	
	
	public void updateBalance( double amount )
	{
		balance += amount;
	}

	@Override
	public String toString() {
		return "Account{" +
				"number=" + number +
				", balance=" + balance +
				'}';
	}
}
