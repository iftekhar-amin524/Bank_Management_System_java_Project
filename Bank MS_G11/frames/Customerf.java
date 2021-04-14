package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;

public class Customerf extends JFrame implements ActionListener
{
	private JLabel wc,name,balance,acno;
	private JButton logout;
	private JPanel p;
	private CustomerRepo cr;
	private Customer cus;
	private User us;

	public Customerf(){}
	
	public Customerf(User u)
	{
		super("Customer Home");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);

		this.us=u;
		cr=new CustomerRepo();
		cus=cr.searchCustomer(us.getUserId());
		
		wc=new JLabel("Welcome Sir,");
		wc.setBounds(10,10,100,20);
		p.add(wc);
		
		name=new JLabel("A/C holder Name "+cus.getCName());
		name.setBounds(100,35,350,20);
		p.add(name);
		
		acno=new JLabel("Account number "+us.getUserId());
		acno.setBounds(100,60,350,20);
		p.add(acno);
		
		balance=new JLabel("Current balance "+cus.getBalance()+" tk");
		balance.setBounds(100,85,350,20);
		p.add(balance);
		
		logout=new JButton("Logout");
		logout.setBounds(700,10,90,30);
		logout.addActionListener(this);
		p.add(logout);
		
		
		this.add(p);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		if(logout.getText().equals(command))
		{
			Loginf f=new Loginf();
			f.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}