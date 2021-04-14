package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;
public class Employeef extends JFrame implements ActionListener
{
	private JButton managemp,managecus,logout,chngpass;
	private JPanel p;
	private User usf=null;
	
	public Employeef(){}
	
	public Employeef(User usf)
	{
		super("Employee Home");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		this.usf=usf;
		
		managemp=new JButton("Manage Employees");
		managemp.setBounds(120,100,150,30);
		managemp.addActionListener(this);
		p.add(managemp);
		
		managecus=new JButton("Manage Customers");
		managecus.setBounds(120,140,150,30);
		managecus.addActionListener(this);
		p.add(managecus);
		
		logout=new JButton("Logout");
		logout.setBounds(500,10,90,30);
		logout.addActionListener(this);
		p.add(logout);
		
		chngpass=new JButton("Change Password");
		chngpass.setBounds(600,10,180,30);
		chngpass.addActionListener(this);
		p.add(chngpass);
		
		this.add(p);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		if(logout.getText().equals(command))
		{
			Loginf lf=new Loginf();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(chngpass.getText().equals(command))
		{
			Changepassf cpf=new Changepassf(usf);
			cpf.setVisible(true);
			this.setVisible(false);
		}
		else if(managecus.getText().equals(command))
		{
			ManageCustomerf mcfr=new ManageCustomerf(usf);
			mcfr.setVisible(true);
			this.setVisible(false);
		}
		else if(managemp.getText().equals(command))
		{
			if(usf.getStatus()==0)
			{
				managemp.setEnabled(true);
				ManageEmployeef mef=new ManageEmployeef(usf);
				mef.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				managemp.setEnabled(false);
			}
		}
		else{}
	}
}