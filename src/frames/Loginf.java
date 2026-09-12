package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import repository.*;
import entity.*;

public class Loginf extends JFrame implements ActionListener, MouseListener {
    private JLabel user, pass, regl, wlc, background;
    private JTextField userfl;
    private JPasswordField passfl;
    private JButton login, exit, reg, showpass;
    
    public Loginf() {
        super("Memorial Bank - A Trusted Partner");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); // Required for absolute positioning
        setLocationRelativeTo(null); // Center window on screen

        // 🔹 Set System Icon
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        // 🔹 Set Background Image
        background = new JLabel(new ImageIcon(getClass().getResource("/background.jpg")));
        background.setBounds(0, 0, 800, 450);

        // 🔹 Panel for Components (Transparent)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false); // Makes the panel transparent so background is visible
        panel.setBounds(0, 0, 800, 450);

        // 🔹 UI Components
        wlc = new JLabel("LOGIN");
        wlc.setFont(new Font("Arial", Font.BOLD, 24));
        wlc.setForeground(Color.WHITE); // Ensures text is visible over background
        wlc.setBounds(320, 10, 200, 40);
        panel.add(wlc);

        user = new JLabel("User ID:");
        user.setForeground(Color.WHITE);
        user.setBounds(250, 100, 80, 30);
        panel.add(user);

        pass = new JLabel("Password:");
        pass.setForeground(Color.WHITE);
        pass.setBounds(250, 140, 80, 30);
        panel.add(pass);

        userfl = new JTextField();
        userfl.setBounds(330, 100, 200, 30);
        panel.add(userfl);

        passfl = new JPasswordField();
        passfl.setBounds(330, 140, 200, 30);
        passfl.setEchoChar('*');
        panel.add(passfl);

        showpass = new JButton("Show");
        showpass.setBounds(540, 140, 70, 30);
        showpass.addMouseListener(this);
        panel.add(showpass);

        login = new JButton("Login");
        login.setBounds(330, 180, 90, 30);
        login.addActionListener(this);
        panel.add(login);

        exit = new JButton("Exit");
        exit.setBounds(440, 180, 90, 30);
        exit.addActionListener(this);
        panel.add(exit);

        regl = new JLabel("Don't have an account?");
        regl.setForeground(Color.WHITE);
        regl.setBounds(290, 230, 150, 30);
        panel.add(regl);

        reg = new JButton("Register");
        reg.setBounds(430, 230, 100, 30);
        reg.addActionListener(this);
        panel.add(reg);

        // 🔹 Add Components in Correct Order
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/background.jpg")))); // Set background
        add(panel); // Add panel on top of background
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (login.getText().equals(command)) {
            UserRepo ur = new UserRepo();
            User user = ur.getUser(userfl.getText(), new String(passfl.getPassword()));
            if (user != null) {
                if (user.getStatus() == 0 || user.getStatus() == 1) {
                    Employeef ef = new Employeef(user);
                    ef.setVisible(true);
                    this.setVisible(false);
                } else if (user.getStatus() == 2) {
                    Customerf cf = new Customerf(user);
                    cf.setVisible(true);
                    this.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid user ID or password");
            }
        } else if (exit.getText().equals(command)) {
            System.exit(0);
        } else if (reg.getText().equals(command)) {
            Regf rf = new Regf();
            rf.setVisible(true);
            this.setVisible(false);
        }
    }

    public void mouseClicked(MouseEvent me) {}

    public void mousePressed(MouseEvent me) {
        passfl.setEchoChar((char) 0);
    }

    public void mouseReleased(MouseEvent me) {
        passfl.setEchoChar('*');
    }

    public void mouseEntered(MouseEvent me) {}

    public void mouseExited(MouseEvent me) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Loginf frame = new Loginf();
            frame.setVisible(true);
        });
    }
}
