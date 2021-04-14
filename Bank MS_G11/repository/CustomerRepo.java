package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class CustomerRepo implements ICustomer
{
	DatabaseConnection dbc;
	
	public CustomerRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertInDB(Customer c)
	{
		String query = "INSERT INTO customers VALUES ('"+c.getCustomerId()+"','"+c.getCName()+"','"+c.getBalance()+"');";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteFromDB(String customerId)
	{
		String query = "DELETE from customers WHERE customerId='"+customerId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void deposit(Customer c,double balance)
	{
		String query = "UPDATE customers SET balance='"+(c.getBalance()+balance)+"' WHERE customerId='"+c.getCustomerId()+"'";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void withdraw(Customer c,double balance)
	{
		String query = "UPDATE customers SET balance='"+(c.getBalance()-balance)+"' WHERE customerId='"+c.getCustomerId()+"'";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateInDB(Customer c)
	{
		//String query = "UPDATE customers SET customerName='"+c.getCName()+"',balance='"+c.getBalance()+"' WHERE customerId='"+c.getCustomerId()+"'";
		String query="UPDATE customers SET customerName='"+c.getCName()+"',balance='"+c.getBalance()+"' WHERE customerId='"+c.getCustomerId()+"'";
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public Customer searchCustomer(String customerId)
	{
		Customer customer = null;
		String query = "SELECT `customerName`,`balance` FROM `customers` WHERE `customerId`='"+customerId+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String name = dbc.result.getString("customerName");
				double balance=dbc.result.getDouble("balance");
			
				
				customer = new Customer();
				customer.setCustomerId(customerId);
				customer.setCName(name);
				customer.setBalance(balance);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return customer;
	}
	public String[][] getAllCustomer()
	{
		ArrayList<Customer> ar = new ArrayList<Customer>();
		String query = "SELECT * FROM customers;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String customerId = dbc.result.getString("customerId");
				String name = dbc.result.getString("CustomerName");
				double balance = dbc.result.getDouble("balance");
				
				Customer c = new Customer(customerId,name,balance);
				ar.add(c);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][3];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Customer)obj[i]).getCustomerId();
			data[i][1] = ((Customer)obj[i]).getCName();
			data[i][2] = (""+((Customer)obj[i]).getBalance());
		}
		return data;
	}
}












































