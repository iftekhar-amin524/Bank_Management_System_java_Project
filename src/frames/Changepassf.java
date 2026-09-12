package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import repository.*;
import entity.*;

public class Changepassf extends JFrame implements ActionListener {
    private JLabel pass, background;
    private JPasswordField passf;
    private JButton conf, logout, back;
    private JPanel panel;
    private User us;

    public Changepassf(User us) {
        super("Change Password");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.us = us;

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

        pass = new JLabel("Type New Password:");
        pass.setForeground(Color.WHITE);
        pass.setBounds(50, 100, 150, 30);
        panel.add(pass);

        passf = new JPasswordField();
        passf.setBounds(200, 100, 150, 30);
        panel.add(passf);

        conf = new JButton("Confirm");
        conf.setBounds(200, 140, 100, 30);
        conf.addActionListener(this);
        panel.add(conf);

        // 🔹 Logout Button
        logout = new JButton("Logout");
        logout.setBounds(600, 20, 100, 30);
        logout.addActionListener(this);
        panel.add(logout);

        // 🔹 Back Button (Next to Logout)
        back = new JButton("Back");
        back.setBounds(490, 20, 100, 30);
        back.addActionListener(this);
        panel.add(back);

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
        } else if (back.getText().equals(command)) {
            // 🔹 Check User Type and Return to Correct Frame
            if (us.getStatus() == 2) { 
                Customerf f = new Customerf(us); // Return to Customer Home
                f.setVisible(true);
            } else { 
                Employeef f = new Employeef(us); // Return to Employee Home
                f.setVisible(true);
            }
            this.setVisible(false);
        } else if (conf.getText().equals(command)) {
            if (us != null) { // Avoid Null Pointer Exception
                UserRepo ur = new UserRepo();
                us.setPassword(new String(passf.getPassword())); // ✅ Correct way to get password
                ur.updateUser(us);
                JOptionPane.showMessageDialog(this, "Password has been changed successfully!");
                Loginf f = new Loginf();
                f.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Error: No user information available.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Changepassf frame = new Changepassf(new User("12345", "password", 2)); // Test Data
            frame.setVisible(true);
        });
    }
}
