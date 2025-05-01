import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;

public class LoginPage {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginPage window = new LoginPage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 80, 25);
        frame.getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(140, 50, 130, 25);
        frame.getContentPane().add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 90, 80, 25);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 130, 25);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 140, 100, 30);
        frame.getContentPane().add(btnLogin);

        btnLogin.addActionListener(e -> {
            frame.dispose(); // Close login window
            HomePage1 home = new HomePage1();
            home.setVisible(true); // Open home page
        });
    }
}
