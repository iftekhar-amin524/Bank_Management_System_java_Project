package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;

public class ManageEmployeef extends JFrame implements ActionListener
{
	private JLabel id,name,balance,des;
	private JTextField idf,namef,balancef,desf;
	private JButton ins,upd,del,ref,back,logout,allemp,search;
	private JPanel p;
	private JTable empTable;
	private JScrollPane empTableSP;

	private EmployeeRepo empr,er;
	private Employee emp;
	private UserRepo usr;
	private User usf;
	
	public ManageEmployeef(User usf)
	{
		super("Manage Employee");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		this.usf=usf;
		er=new EmployeeRepo();

		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Designation", "Salary"};
		
		empTable = new JTable(data,head);
		empTableSP = new JScrollPane(empTable);
		empTableSP.setBounds(350, 100, 400, 150);
		empTable.setEnabled(false);
		p.add(empTableSP);

		allemp=new JButton("Show All");
		allemp.setBounds(600,320,90,30);
		allemp.addActionListener(this);
		p.add(allemp);

		id=new JLabel("ID ");
		id.setBounds(10,30,20,30);
		p.add(id);
		
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
		
		balance=new JLabel("Salary:");
		balance.setBounds(10,100,70,30);
		p.add(balance);
		
		balancef=new JTextField();
		balancef.setBounds(90,100,120,30);
		p.add(balancef);
		
		des=new JLabel("Designation:");
		des.setBounds(10,135,60,30);
		p.add(des);
		
		desf=new JTextField();
		desf.setBounds(90,135,120,30);
		p.add(desf);
		
		ref=new JButton("Refresh");
		ref.setBounds(125,170,90,30);
		ref.addActionListener(this);
		p.add(ref);
		
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
		String command = ae.getActionCommand();
		if(search.getText().equals(command))
		{
			empr=new EmployeeRepo();
			emp=empr.searchEmployee(idf.getText());
			if(emp!=null)
			{
				namef.setText(emp.getName());
				balancef.setText(Double.toString(emp.getSalary()));
				desf.setText(emp.getDesignation());
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invaild ID");
			}
		}
		else if(back.getText().equals(command))
		{
			Employeef empf=new Employeef(usf);
			empf.setVisible(true);
			this.setVisible(false);
		}
		else if(logout.getText().equals(command))
		{
			Loginf nf=new Loginf();
			nf.setVisible(true);
			this.setVisible(false);
		}
		else if(ins.getText().equals(command))
		{
			emp.setName(namef.getText());
			emp.setSalary(Double.parseDouble(balancef.getText()));
			emp.setDesignation(desf.getText());
			emp.setEmpId(idf.getText());
			empr.insertInDB(emp);

			Random rd = new Random();
			int x = rd.nextInt(999)+100;

			UserRepo usR=new UserRepo();
			User us;

			if(emp.getDesignation().equals("Manager") || emp.getDesignation().equals("manager"))
			{
				us=new User(idf.getText(),x+"",0);
				usR.insertUser(us);
			}
			else
			{
				us=new User(idf.getText(),x+"",1);
				usR.insertUser(us);	
			}
		}
		else if(del.getText().equals(command))
		{
			empr=new EmployeeRepo();
			usr=new UserRepo();
			emp=empr.searchEmployee(idf.getText());
			if(emp!=null)
			{
				usr.deleteUser(idf.getText());
				empr.deleteFromDB(idf.getText());
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invalid ID");
			}

		}
		else if(upd.getText().equals(command))
		{
			empr=new EmployeeRepo();
			Employee empp=new Employee();

			empp.setEmpId(idf.getText());
			empp.setName(namef.getText());
			empp.setDesignation(desf.getText());
			empp.setSalary(Double.parseDouble(balancef.getText()));
			empr.updateInDB(empp);

			idf.setText("");
			namef.setText("");
			balancef.setText("");
			desf.setText("");
		}
		else if(ref.getText().equals(command))
		{
			idf.setText("");
			namef.setText("");
			balancef.setText("");
			desf.setText("");
		}
		else if(allemp.getText().equals(command))
		{
			String data[][] = er.getAllEmployee();
			String head[] = {"Id", "Name", "Designation", "Salary"};
			
			p.remove(empTableSP);
			
			empTable = new JTable(data,head);
			empTable.setEnabled(false);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(350, 100, 400, 150);
			p.add(empTableSP);
			
			p.revalidate();
			p.repaint();
		}
	}
	
}