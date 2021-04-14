package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;
public class Regf extends JFrame implements ActionListener
{
	private JLabel name,acc_us,pass,balance;
	private JButton Register,back;
	private JTextField fname,facc_us,fbalance;
	private JPasswordField u_pass;
	private JPanel p;
	private JCheckBox t;
	private User us;
	private Customer cs;
	
	public Regf()
	{
		super("Sign up");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		name=new JLabel("Name:");
		name.setBounds(50,50,70,30);
		p.add(name);
		
		fname=new JTextField();
		fname.setBounds(150,50,120,30);
		p.add(fname);
		
		acc_us=new JLabel("A/C no:");
		acc_us.setBounds(50,85,90,30);
		p.add(acc_us);
		
		facc_us=new JTextField();
		facc_us.setBounds(150,85,120,30);
		p.add(facc_us);
		
		pass=new JLabel("Password:");
		pass.setBounds(50,120,90,30);
		p.add(pass);
		
		u_pass=new JPasswordField();
		u_pass.setBounds(150,120,120,30);
		p.add(u_pass);
		
		balance=new JLabel("Balance:");
		balance.setBounds(50,155,90,30);
		p.add(balance);
		
		fbalance=new JTextField();
		fbalance.setBounds(150,155,120,30);
		p.add(fbalance);
		
		t=new JCheckBox("I have read and accept the terms and policy");
		t.setBounds(90,185,300,30);
		t.addActionListener(this);
		p.add(t);
		
		Register=new JButton("Register");
		Register.setBounds(90,220,90,30);
		Register.addActionListener(this);
		p.add(Register);
		
		back=new JButton("Back");
		back.setBounds(600,20,90,30);
		back.addActionListener(this);
		p.add(back);
		
		this.add(p);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		if(t.isSelected())
		{
			Register.setEnabled(true);
		}
		else if(!t.isSelected())
		{
			Register.setEnabled(false);
		}
		else if(Register.getText().equals(command))
		{
			us=new User(facc_us.getText(),u_pass.getText(),2);
			UserRepo ur=new UserRepo();
			ur.insertUser(us);
			
			cs=new Customer(facc_us.getText(),fname.getText(),Double.parseDouble(fbalance.getText()));
			CustomerRepo cr=new CustomerRepo();
			cr.insertInDB(cs);
			JOptionPane.showMessageDialog(this,"Your Account has been created");
		}
		else if(back.getText().equals(command))
		{
			Loginf f=new Loginf();
			f.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}