package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class Employeef extends JFrame implements ActionListener {
    private JButton managemp, managecus, logout, chngpass;
    private JPanel panel;
    private JLabel background;
    private User usf;

    public Employeef(User usf) {
        super("Employee Home");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); // Required for absolute positioning
        setLocationRelativeTo(null); // Center window

        this.usf = usf;

        // 🔹 Set System Icon
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        // 🔹 Set Background Image
        background = new JLabel(new ImageIcon(getClass().getResource("/background.jpg")));
        background.setBounds(0, 0, 800, 450);

        // 🔹 Transparent Panel for Buttons
        panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, 800, 450);

        // 🔹 Buttons
        managemp = new JButton("Manage Employees");
        managemp.setBounds(120, 100, 150, 30);
        managemp.addActionListener(this);
        panel.add(managemp);

        managecus = new JButton("Manage Customers");
        managecus.setBounds(120, 140, 150, 30);
        managecus.addActionListener(this);
        panel.add(managecus);

        logout = new JButton("Logout");
        logout.setBounds(500, 10, 90, 30);
        logout.addActionListener(this);
        panel.add(logout);

        chngpass = new JButton("Change Password");
        chngpass.setBounds(600, 10, 180, 30);
        chngpass.addActionListener(this);
        panel.add(chngpass);

        // 🔹 Add Components in Correct Order
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/background.jpg")))); // Background
        add(panel); // Add transparent panel with buttons
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (logout.getText().equals(command)) {
            Loginf lf = new Loginf();
            lf.setVisible(true);
            this.setVisible(false);
        } else if (chngpass.getText().equals(command)) {
            Changepassf cpf = new Changepassf(usf);
            cpf.setVisible(true);
            this.setVisible(false);
        } else if (managecus.getText().equals(command)) {
            ManageCustomerf mcfr = new ManageCustomerf(usf);
            mcfr.setVisible(true);
            this.setVisible(false);
        } else if (managemp.getText().equals(command)) {
            if (usf.getStatus() == 0) {
                managemp.setEnabled(true);
                ManageEmployeef mef = new ManageEmployeef(usf);
                mef.setVisible(true);
                this.setVisible(false);
            } else {
                managemp.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Employeef frame = new Employeef(null); // Pass a valid User object when running
            frame.setVisible(true);
        });
    }
}
