package entity;

import java.lang.*;

public class Customer
{
	private String customerId;//also account number
	private String cName; 
	private double balance;

	public Customer(){}
	public Customer(String customerId, String cName,double balance)
	{
		this.customerId = customerId;
		this.cName = cName;
		this.balance=balance;
		
	}
	
	public void setCustomerId(String userId){this.customerId = customerId;}
	public void setCName(String cName){this.cName = cName;}
    public void setBalance(double balance){this.balance = balance;} 

	
	public String getCustomerId(){return customerId;}
	public String getCName(){return cName;}
	public double getBalance(){return balance;}
}