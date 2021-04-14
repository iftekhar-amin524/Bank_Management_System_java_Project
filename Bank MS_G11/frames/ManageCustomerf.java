package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;
public class ManageCustomerf extends JFrame implements ActionListener
{
	private JLabel id,name,balance,addam;
	private JTextField idf,namef,balancef,addf;
	private JButton dep,wit,ins,upd,del,ref,back,logout,allcust,search;
	private JPanel p;
	private JTable cusTable;
	private JScrollPane cusTableSP;
	private User usf;
	private CustomerRepo csr;
	private Customer cus;
	
	public ManageCustomerf(){}
	
	public ManageCustomerf(User usf)
	{
		super("Manage Customer");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		this.usf=usf;

		String data[][] = {{"", "", ""}};
		
		String head[] = {"Id", "Name", "Balance"};
		
		cusTable = new JTable(data,head);
		cusTableSP = new JScrollPane(cusTable);
		cusTableSP.setBounds(350, 100, 400, 150);
		cusTable.setEnabled(false);
		p.add(cusTableSP);
		
		allcust=new JButton("Show All");
		allcust.setBounds(600,320,90,30);
		allcust.addActionListener(this);
		p.add(allcust);

		id=new JLabel("ID ");
		id.setBounds(10,30,20,30);
		p.add(id);
		
		ref=new JButton("Refresh");
		ref.setBounds(220,170,90,30);
		ref.addActionListener(this);
		p.add(ref);
		
		idf=new JTextField();
		idf.setBounds(35,30,150,30);
		p.add(idf);
		
		search=new JButton("Search");
		search.setBounds(190,30,80,30);
		search.addActionListener(this);
		p.add(search);
		
		name=new JLabel("Name:");
		name.setBounds(10,65,60,30);
		p.add(name);
		
		namef=new JTextField();
		namef.setBounds(90,65,120,30);
		p.add(namef);
		
		balance=new JLabel("Balance:");
		balance.setBounds(10,100,70,30);
		p.add(balance);
		
		balancef=new JTextField();
		balancef.setBounds(90,100,120,30);
		p.add(balancef);
		
		addam=new JLabel("Add:");
		addam.setBounds(10,135,60,30);
		p.add(addam);
		
		addf=new JTextField();
		addf.setBounds(90,135,120,30);
		p.add(addf);
		
		dep=new JButton("Deposit");
		dep.setBounds(40,170,80,30);
		dep.addActionListener(this);
		p.add(dep);
		
		wit=new JButton("Withdraw");
		wit.setBounds(125,170,90,30);
		wit.addActionListener(this);
		p.add(wit);
		
		ins=new JButton("Insert");
		ins.setBounds(40,210,80,30);
		ins.addActionListener(this);
		p.add(ins);
		
		upd=new JButton("Update");
		upd.setBounds(125,210,90,30);
		upd.addActionListener(this);
		p.add(upd);
		
		del=new JButton("Delete");
		del.setBounds(220,210,90,30);
		del.addActionListener(this);
		p.add(del);
		
		back=new JButton("Back");
		back.setBounds(5,320,70,30);
		back.addActionListener(this);
		p.add(back);
		
		logout=new JButton("Logout");
		logout.setBounds(700,20,90,30);
		logout.addActionListener(this);
		p.add(logout);
		
		this.add(p);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command=ae.getActionCommand();
		
		if(search.getText().equals(command))
		{
			if(!idf.getText().equals(null) || !idf.getText().equals(""))
			{
				csr=new CustomerRepo();
				Customer c;
				c=csr.searchCustomer(idf.getText());
				if(c!=null)
				{
					namef.setText(c.getCName());
					balancef.setText(Double.toString(c.getBalance()));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invaild ID");
			}
		}
		else if(dep.getText().equals(command))
		{
			csr=new CustomerRepo();
			Customer c;
			c=csr.searchCustomer(idf.getText());
			if(c!=null)
			{
				c=new Customer(idf.getText(),namef.getText(),Double.parseDouble(balancef.getText()));
				csr.deposit(c,Double.parseDouble(addf.getText()));

				idf.setText("");
				namef.setText("");
				balancef.setText("");
				addf.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invaild ID");
			}
		}
		else if(wit.getText().equals(command))
		{
			csr=new CustomerRepo();
			Customer c;
			c=csr.searchCustomer(idf.getText());
			if(c!=null)
			{
				c=new Customer(idf.getText(),namef.getText(),Double.parseDouble(balancef.getText()));
				csr.withdraw(c,Double.parseDouble(addf.getText()));

				idf.setText("");
				namef.setText("");
				balancef.setText("");
				addf.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invaild ID");
			}
		}
		else if(ref.getText().equals(command))
		{
			idf.setText("");
			namef.setText("");
			balancef.setText("");
			addf.setText("");
		}
		else if(back.getText().equals(command))
		{
			Employeef empf=new Employeef(usf);
			empf.setVisible(true);
			this.setVisible(false);
		}
		else if(logout.getText().equals(command))
		{
			Loginf lgf=new Loginf();
			lgf.setVisible(true);
			this.setVisible(false);
		}
		else if(ins.getText().equals(command))
		{
			Customer ics=new Customer(idf.getText(),namef.getText(),Double.parseDouble(balancef.getText()));
			CustomerRepo rep=new CustomerRepo();
			rep.insertInDB(ics);

			Random rd = new Random();
			int x = rd.nextInt(999)+100;

			UserRepo usr=new UserRepo();
			User y=new User(idf.getText(),x+"",2);
			usr.insertUser(y);

		}
		else if(del.getText().equals(command))
		{
			CustomerRepo dcs=new CustomerRepo();
			dcs.deleteFromDB(idf.getText());

			UserRepo usr=new UserRepo();
			usr.deleteUser(idf.getText());
		}
		else if(upd.getText().equals(command))
		{
			csr=new CustomerRepo();
			Customer c=new Customer(idf.getText(),namef.getText(),Double.parseDouble(balancef.getText()));
			csr.updateInDB(c);

			idf.setText("");
			namef.setText("");
			balancef.setText("");
			addf.setText("");
		}
		else if(allcust.getText().equals(command))
		{
			csr=new CustomerRepo();
			String data[][] = csr.getAllCustomer();
			String head[] = {"Id", "Name", "Balance"};
			
			p.remove(cusTableSP);
			
			cusTable = new JTable(data,head);
			cusTable.setEnabled(false);
			cusTableSP = new JScrollPane(cusTable);
			cusTableSP.setBounds(350, 100, 400, 150);
			p.add(cusTableSP);
			
			p.revalidate();
			p.repaint();
		}
	}
	
}