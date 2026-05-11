package ro.uvt.fi.dp.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * MVC — View layer.
 * Defines the Swing UI components. No business logic here.
 */
public class Client_View extends JFrame {

    // Login panel
    public final JTextField nameField   = new JTextField(15);
    public final JButton    loginBtn    = new JButton("Login");

    // Info display
    public final JTextArea  infoArea    = new JTextArea(10, 35);

    // Account selector
    public final JComboBox<String> accountSelector = new JComboBox<>();

    // Operations
    public final JTextField amountField   = new JTextField(10);
    public final JButton    depositBtn    = new JButton("Deposit");
    public final JButton    withdrawBtn   = new JButton("Withdraw");
    public final JButton    undoBtn       = new JButton("Undo Last");
    public final JButton    statementBtn  = new JButton("Monthly Statement");

    public Client_View() {
        setTitle("UVT Bank — Client Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Top: Login bar ---
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        loginPanel.add(new JLabel("Client name:"));
        loginPanel.add(nameField);
        loginPanel.add(loginBtn);

        // --- Center: info area ---
        infoArea.setEditable(false);
        infoArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(infoArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Account Info"));

        // --- Bottom: operations ---
        JPanel opsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        opsPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
        opsPanel.add(new JLabel("Account:"));
        opsPanel.add(accountSelector);
        opsPanel.add(new JLabel("Amount:"));
        opsPanel.add(amountField);
        opsPanel.add(depositBtn);
        opsPanel.add(withdrawBtn);
        opsPanel.add(undoBtn);
        opsPanel.add(statementBtn);

        add(loginPanel, BorderLayout.NORTH);
        add(scroll,     BorderLayout.CENTER);
        add(opsPanel,   BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showMessage(String msg) {
        infoArea.setText(msg);
    }

    public void appendMessage(String msg) {
        infoArea.append(msg + "\n");
    }
}
