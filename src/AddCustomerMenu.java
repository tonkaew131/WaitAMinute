import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerMenu extends JFrame {
    WaitAMinute waitAMinute;

    JTextField nameJT = new JTextField(15);
    JTextField phoneJT = new JTextField(15);
    JTextField amountJT = new JTextField(15);
    JTextField sizeJT = new JTextField(15);

//    public static void main(String args[]) {
//        // testing ui
//        AddCustomerMenu frame = new AddCustomerMenu();
//    }

    public AddCustomerMenu() {
        setTitle("Add new customer");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(3, 2, 4, 4));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        nameJT.setBorder(BorderFactory.createCompoundBorder(nameJT.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        namePanel.add(nameJT);
        add(namePanel);

        JPanel phonePanel = new JPanel();
        phonePanel.add(new JLabel("Phone:"));
        phoneJT.setBorder(BorderFactory.createCompoundBorder(phoneJT.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        phonePanel.add(phoneJT);
        add(phonePanel);

        JPanel amountPanel = new JPanel();
        amountPanel.add(new JLabel("Amount:"));
        amountJT.setBorder(BorderFactory.createCompoundBorder(amountJT.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        amountPanel.add(amountJT);
        add(amountPanel);

        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Size (S,M,L):"));
        sizeJT.setBorder(BorderFactory.createCompoundBorder(sizeJT.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        sizePanel.add(sizeJT);
        add(sizePanel);

        JButton addBtn = new JButton("Add");
        add(addBtn);
        addBtn.addActionListener((e) -> {
            try {
                waitAMinute.addCustommer(nameJT.getText(), phoneJT.getText(), sizeJT.getText(), (int) Integer.parseInt(amountJT.getText()));

                nameJT.setText("");
                phoneJT.setText("");
                sizeJT.setText("");
                amountJT.setText("");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        pack();
    }

    public void setWaitAMinute(WaitAMinute waitAMinute) {
        this.waitAMinute = waitAMinute;
    }
}
