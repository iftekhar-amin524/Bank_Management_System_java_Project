package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class Loginf extends JFrame implements ActionListener,MouseListener
{
	private JLabel user,pass,regl;
	private JTextField userfl;
	private JPasswordField passfl;
	private JButton login,exit,reg,showpass;
	private JPanel p;
	
	
	public Loginf()
	{
		super("Login");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		
		user=new JLabel("User ID:");
		user.setBounds(100,50,50,30);
		p.add(user);
		
		pass=new JLabel("Password:");
		pass.setBounds(100,90,80,30);
		p.add(pass);
		
		userfl=new JTextField();
		userfl.setBounds(160,50,150,30);
		p.add(userfl);
		
		passfl=new JPasswordField();
		passfl.setBounds(160,90,150,30);
		passfl.setEchoChar('*');
		p.add(passfl);
		
		showpass=new JButton("show");
		showpass.setBounds(320,90,70,30);
		showpass.addMouseListener(this);
		p.add(showpass);
		
		login=new JButton("Login");
		login.setBounds(160,128,70,30);
		login.addActionListener(this);
		p.add(login);
		
		exit=new JButton("Exit");
		exit.setBounds(250,128,70,30);
		exit.addActionListener(this);
		p.add(exit);
		
		regl=new JLabel("Don't have account?");
		regl.setBounds(100,165,130,30);
		p.add(regl);
		
		reg=new JButton("Register");
		reg.setBounds(220,165,100,30);
		reg.addActionListener(this);
		p.add(reg);
		
		this.add(p);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		
		if(login.getText().equals(command))
		{
			UserRepo ur=new UserRepo();
			User user=ur.getUser(userfl.getText(),passfl.getText());
			if(user!=null)
			{
				if(user.getStatus()==0 || user.getStatus()==1)
				{
					Employeef ef=new Employeef(user);
					ef.setVisible(true);
					this.setVisible(false);		
				}
				else if(user.getStatus()==2)
				{
					Customerf cf=new Customerf(user);
					cf.setVisible(true);
					this.setVisible(false);
				}
				else
				{
					
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invalid user id or password");
			}
		}
		else if(exit.getText().equals(command))
		{
			System.exit(0);
		}
		else if(reg.getText().equals(command))
		{
			Regf rf=new Regf();
			rf.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		passfl.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		passfl.setEchoChar('*');
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
}