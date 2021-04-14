package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;

public class Changepassf extends JFrame implements ActionListener
{
	private JLabel pass;
	private JPasswordField passf;
	private JButton conf,logout;
	private JPanel p;
	private User us;
	
	public Changepassf(){}
	
	public Changepassf(User us)
	{
		super("Change Password");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		this.us=us;
		
		pass=new JLabel("New Password:");
		pass.setBounds(50,100,150,30);
		p.add(pass);
		
		passf=new JPasswordField();
		passf.setBounds(150,100,120,30);
		p.add(passf);
		
		conf=new JButton("Confirm");
		conf.setBounds(150,140,80,30);
		conf.addActionListener(this);
		p.add(conf);
		
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
		else if(conf.getText().equals(command))
		{
			UserRepo ur=new UserRepo();
			us.setPassword(passf.getText());
			ur.updateUser(us);
			JOptionPane.showMessageDialog(this,"Password Have Changed");
			Loginf f=new Loginf();
			f.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}