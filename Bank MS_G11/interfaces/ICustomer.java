package interfaces;

import java.lang.*;

import entity.*;

public interface ICustomer
{
	public void insertInDB(Customer c);
	public void deleteFromDB(String cusId);
	public void updateInDB(Customer c);
	public void deposit(Customer c,double balance);
	public void withdraw(Customer c,double balance);
	public Customer searchCustomer(String customerId);
	public String[][] getAllCustomer();
}