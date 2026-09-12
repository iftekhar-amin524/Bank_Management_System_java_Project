package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import repository.*;
import entity.*;

public class Regf extends JFrame implements ActionListener {
    private JLabel name, acc_us, pass, balance, background;
    private JButton Register, back;
    private JTextField fname, facc_us, fbalance;
    private JPasswordField u_pass;
    private JPanel panel;
    private JCheckBox t;
    private User us;
    private Customer cs;

    public Regf() {
        super("Sign Up");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

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

        name = new JLabel("Name:");
        name.setForeground(Color.WHITE);
        name.setBounds(50, 50, 70, 30);
        panel.add(name);

        fname = new JTextField();
        fname.setBounds(150, 50, 150, 30);
        panel.add(fname);

        acc_us = new JLabel("A/C No:");
        acc_us.setForeground(Color.WHITE);
        acc_us.setBounds(50, 90, 90, 30);
        panel.add(acc_us);

        facc_us = new JTextField();
        facc_us.setBounds(150, 90, 150, 30);
        panel.add(facc_us);

        pass = new JLabel("Password:");
        pass.setForeground(Color.WHITE);
        pass.setBounds(50, 130, 90, 30);
        panel.add(pass);

        u_pass = new JPasswordField();
        u_pass.setBounds(150, 130, 150, 30);
        panel.add(u_pass);

        balance = new JLabel("Balance:");
        balance.setForeground(Color.WHITE);
        balance.setBounds(50, 170, 90, 30);
        panel.add(balance);

        fbalance = new JTextField();
        fbalance.setBounds(150, 170, 150, 30);
        panel.add(fbalance);

        t = new JCheckBox("I accept the terms & policy");
        t.setBounds(100, 210, 250, 30);
        t.setOpaque(false); // Transparent background
        t.setForeground(Color.WHITE);
        t.addActionListener(this);
        panel.add(t);

        Register = new JButton("Register");
        Register.setBounds(100, 250, 100, 30);
        Register.addActionListener(this);
        Register.setEnabled(false); // Initially disabled
        panel.add(Register);

        back = new JButton("Back");
        back.setBounds(600, 20, 90, 30);
        back.addActionListener(this);
        panel.add(back);

        // 🔹 Add Components in Correct Order
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/background.jpg")))); // Background
        add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (back.getText().equals(command)) {
            Loginf f = new Loginf();
            f.setVisible(true);
            this.setVisible(false);
        } else if (Register.getText().equals(command)) {
            UserRepo ur = new UserRepo();
            CustomerRepo cr = new CustomerRepo();

            String userId = facc_us.getText();
            String password = new String(u_pass.getPassword()); // ✅ Correct way to get password
            double balanceAmount = Double.parseDouble(fbalance.getText());

            us = new User(userId, password, 2);
            ur.insertUser(us);

            cs = new Customer(userId, fname.getText(), balanceAmount);
            cr.insertInDB(cs);

            JOptionPane.showMessageDialog(this, "Your Account has been created successfully!");
        } else if (t.isSelected()) {
            Register.setEnabled(true);
        } else {
            Register.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Regf frame = new Regf();
            frame.setVisible(true);
        });
    }
}
