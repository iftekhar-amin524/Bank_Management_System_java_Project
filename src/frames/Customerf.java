package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import repository.*;
import entity.*;

public class Customerf extends JFrame implements ActionListener {
    private JLabel wc, name, balance, acno, background;
    private JButton logout, changePass;
    private JPanel panel;
    private CustomerRepo cr;
    private Customer cus;
    private User us;

    public Customerf(User u) {
        super("Customer Home");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.us = u;
        cr = new CustomerRepo();
        cus = cr.searchCustomer(us.getUserId());

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

        // 🔹 Labels with White Color for Visibility
        wc = new JLabel("Welcome, Sir!");
        wc.setForeground(Color.WHITE);
        wc.setFont(new Font("Arial", Font.BOLD, 20));
        wc.setBounds(30, 20, 200, 30);
        panel.add(wc);

        name = new JLabel("A/C Holder Name: " + cus.getCName());
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Arial", Font.PLAIN, 16));
        name.setBounds(100, 60, 350, 25);
        panel.add(name);

        acno = new JLabel("Account Number: " + us.getUserId());
        acno.setForeground(Color.WHITE);
        acno.setFont(new Font("Arial", Font.PLAIN, 16));
        acno.setBounds(100, 90, 350, 25);
        panel.add(acno);

        balance = new JLabel("Current Balance: " + cus.getBalance() + " CAD");
        balance.setForeground(Color.WHITE);
        balance.setFont(new Font("Arial", Font.PLAIN, 16));
        balance.setBounds(100, 120, 350, 25);
        panel.add(balance);

        // 🔹 Change Password Button
        changePass = new JButton("Change Password");
        changePass.setBounds(550, 20, 140, 30);
        changePass.addActionListener(this);
        panel.add(changePass);

        // 🔹 Logout Button
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

        if (logout.getText().equals(command)) {
            Loginf f = new Loginf();
            f.setVisible(true);
            this.setVisible(false);
        } else if (changePass.getText().equals(command)) {
            Changepassf f = new Changepassf(us);
            f.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Customerf frame = new Customerf(new User("12345", "password", 2)); // Test Data
            frame.setVisible(true);
        });
    }
}
