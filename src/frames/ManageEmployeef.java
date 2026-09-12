package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; // ✅ Import Random
import repository.*;
import entity.*;

public class ManageEmployeef extends JFrame implements ActionListener {
    private JLabel id, name, balance, des, background;
    private JTextField idf, namef, balancef, desf;
    private JButton ins, upd, del, ref, back, logout, allemp, search;
    private JPanel panel;
    private JTable empTable;
    private JScrollPane empTableSP;

    private EmployeeRepo empr;
    private UserRepo usr;
    private Employee emp;
    private User usf;

    public ManageEmployeef(User usf) {
        super("Manage Employee");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.usf = usf;
        empr = new EmployeeRepo();

        // 🔹 Set System Icon
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        // 🔹 Set Background Image
        background = new JLabel(new ImageIcon(getClass().getResource("/background.jpg")));
        background.setBounds(0, 0, 800, 450);

        // 🔹 Transparent Panel for Components
        panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, 800, 450);

        // 🔹 Table for Employee Data
        String[][] data = {{"", "", "", ""}};
        String[] head = {"Id", "Name", "Designation", "Salary"};
        empTable = new JTable(data, head);
        empTableSP = new JScrollPane(empTable);
        empTableSP.setBounds(350, 100, 400, 150);
        empTable.setEnabled(false);
        panel.add(empTableSP);

        allemp = new JButton("Show All");
        allemp.setBounds(600, 320, 90, 30);
        allemp.addActionListener(this);
        panel.add(allemp);

        id = new JLabel("ID:");
        id.setForeground(Color.WHITE);
        id.setBounds(10, 30, 60, 30);
        panel.add(id);

        idf = new JTextField();
        idf.setBounds(90, 30, 150, 30);
        panel.add(idf);

        search = new JButton("Search");
        search.setBounds(250, 30, 80, 30);
        search.addActionListener(this);
        panel.add(search);

        name = new JLabel("Name:");
        name.setForeground(Color.WHITE);
        name.setBounds(10, 65, 60, 30);
        panel.add(name);

        namef = new JTextField();
        namef.setBounds(90, 65, 150, 30);
        panel.add(namef);

        balance = new JLabel("Salary:");
        balance.setForeground(Color.WHITE);
        balance.setBounds(10, 100, 60, 30);
        panel.add(balance);

        balancef = new JTextField();
        balancef.setBounds(90, 100, 150, 30);
        panel.add(balancef);

        des = new JLabel("Designation:");
        des.setForeground(Color.WHITE);
        des.setBounds(10, 135, 80, 30);
        panel.add(des);

        desf = new JTextField();
        desf.setBounds(90, 135, 150, 30);
        panel.add(desf);

        ins = new JButton("Insert");
        ins.setBounds(140, 170, 80, 30);
        ins.addActionListener(this);
        panel.add(ins);

        ref = new JButton("Refresh");
        ref.setBounds(40, 170, 90, 30);
        ref.addActionListener(this);
        panel.add(ref);

        upd = new JButton("Update");
        upd.setBounds(40, 210, 90, 30);
        upd.addActionListener(this);
        panel.add(upd);

        del = new JButton("Delete");
        del.setBounds(140, 210, 80, 30);
        del.addActionListener(this);
        panel.add(del);

        back = new JButton("Back");
        back.setBounds(5, 320, 70, 30);
        back.addActionListener(this);
        panel.add(back);

        logout = new JButton("Logout");
        logout.setBounds(700, 20, 90, 30);
        logout.addActionListener(this);
        panel.add(logout);

        // 🔹 Add Components in Correct Order
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/background.jpg")))); // Background
        add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (search.getText().equals(command)) {
            emp = empr.searchEmployee(idf.getText());
            if (emp != null) {
                namef.setText(emp.getName());
                balancef.setText(Double.toString(emp.getSalary()));
                desf.setText(emp.getDesignation());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        } else if (back.getText().equals(command)) {
            Employeef empf = new Employeef(usf);
            empf.setVisible(true);
            this.setVisible(false);
        } else if (logout.getText().equals(command)) {
            Loginf nf = new Loginf();
            nf.setVisible(true);
            this.setVisible(false);
        } else if (ins.getText().equals(command)) {
            emp = new Employee();
            emp.setEmpId(idf.getText());
            emp.setName(namef.getText());
            emp.setSalary(Double.parseDouble(balancef.getText()));
            emp.setDesignation(desf.getText());
            empr.insertInDB(emp);

            // ✅ Generate Random Password
            Random rd = new Random();
            int x = rd.nextInt(999) + 100;

            usr = new UserRepo();
            User us;
            if (emp.getDesignation().equalsIgnoreCase("Manager")) {
                us = new User(idf.getText(), String.valueOf(x), 0);
            } else {
                us = new User(idf.getText(), String.valueOf(x), 1);
            }
            usr.insertUser(us);
        } else if (del.getText().equals(command)) {
            emp = empr.searchEmployee(idf.getText());
            if (emp != null) {
                usr.deleteUser(idf.getText());
                empr.deleteFromDB(idf.getText());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        } else if (upd.getText().equals(command)) {
            emp = new Employee();
            emp.setEmpId(idf.getText());
            emp.setName(namef.getText());
            emp.setDesignation(desf.getText());
            emp.setSalary(Double.parseDouble(balancef.getText()));
            empr.updateInDB(emp);
        } else if (ref.getText().equals(command)) {
            idf.setText("");
            namef.setText("");
            balancef.setText("");
            desf.setText("");
        } else if (allemp.getText().equals(command)) {
            String[][] data = empr.getAllEmployee();
            String[] head = {"Id", "Name", "Designation", "Salary"};

            panel.remove(empTableSP);
            empTable = new JTable(data, head);
            empTable.setEnabled(false);
            empTableSP = new JScrollPane(empTable);
            empTableSP.setBounds(350, 100, 400, 150);
            panel.add(empTableSP);

            panel.revalidate();
            panel.repaint();
        }
    }
}
