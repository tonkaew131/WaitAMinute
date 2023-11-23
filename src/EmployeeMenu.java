import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeMenu extends JFrame {
    public static void main(String args[]) {
        EmployeeMenu frame = new EmployeeMenu();
    }

    WaitAMinute waitAMinute;
    CustomerData displayedCustomer = new CustomerData();
    JLabel displayedCusName = new JLabel("ชื่อ: -");
    JLabel displayedCusPhone = new JLabel("เบอร์: -");
    JLabel displayedCusSize = new JLabel("ขนาดโต๊ะ: -");
    JLabel displayedCusAmount = new JLabel("จำนวน: -");
    JPanel queueTable = new JPanel();

    public EmployeeMenu() {
        setLayout(new GridLayout(1, 2, 10, 10));

        queueTable.setBackground(Color.WHITE);
        add(queueTable);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(getLogo());
        logoLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        controlPanel.add(logoLabel, BorderLayout.NORTH);

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        detailPanel.add(Box.createRigidArea(new Dimension(5, 20)));

        detailPanel.add(displayedCusName);
        detailPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        detailPanel.add(displayedCusPhone);
        detailPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        detailPanel.add(displayedCusSize);
        detailPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        detailPanel.add(displayedCusAmount);
        detailPanel.add(Box.createRigidArea(new Dimension(5, 10)));

        controlPanel.add(detailPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        JButton appointmentBtn = new JButton("จอง");
        appointmentBtn.addActionListener((e) -> {
            addNewQueue();
        });
        appointmentBtn.setPreferredSize(new Dimension(50, 50));
        JButton callBtn = new JButton("เรียก");
        buttonPanel.add(appointmentBtn);
        buttonPanel.add(callBtn);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(controlPanel);
    }

    public void drawAllQueues() {
        queueTable.removeAll();
        queueTable.revalidate();
        queueTable.repaint();
        queueTable.setLayout(new GridLayout(12, 1, 10, 10));

//        System.out.println("Drawing!");

        ArrayList<CustomerData> cds = waitAMinute.getAllCustomers();
        for (int i = 0; i < cds.size(); i++) {
            JButton rows = new JButton(cds.get(i).getQueueId());
            rows.setBackground(cds.get(i).getColor());
            rows.setBorderPainted(false);
            rows.setOpaque(true);
            int finalI = i;
            rows.addActionListener((e) -> {
                setDisplayedCustomer(cds.get(finalI));
            });
            queueTable.add(rows);
        }
    }

    public void addNewQueue() {
        AddCustomerMenu frame = new AddCustomerMenu();
        frame.setWaitAMinute(waitAMinute);
    }

    public void setDisplayedCustomer(CustomerData cus) {
        displayedCustomer = cus;

        displayedCusName.setText("ชื่อ: " + cus.getName());
        displayedCusPhone.setText("เบอร์: " + cus.getPhoneNumber());
        displayedCusSize.setText("ขนาดโต๊ะ: " + CustomerData.tableSizeToString(cus.getTableSize()));
        displayedCusAmount.setText("จำนวน: " + cus.getAmount());
    }

    public ImageIcon getLogo() {
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/resources/logo.png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageIcon;
    }

    public void setWaitAMinute(WaitAMinute waitAMinute) {
        this.waitAMinute = waitAMinute;
    }
}

