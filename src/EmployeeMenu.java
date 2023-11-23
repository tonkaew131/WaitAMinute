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
        setLayout(new GridLayout(1, 2, 0, 0));

        queueTable.setBackground(new Color(255, 227, 255));
        queueTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(queueTable);

        Color bgColor = new Color(255, 254, 202);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(bgColor);
        controlPanel.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(getLogo());
        logoLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        controlPanel.add(logoLabel, BorderLayout.NORTH);

        JPanel detailPanel = new JPanel();
        detailPanel.setBackground(bgColor);
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
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 0));
        JButton appointmentBtn = new JButton("จอง");
        appointmentBtn.addActionListener((e) -> {
            addNewQueue();
        });
        appointmentBtn.setPreferredSize(new Dimension(50, 50));
        buttonPanel.add(appointmentBtn);

        JButton callSmallBtn = new JButton("S");
        callSmallBtn.setBackground(new Color(244, 200, 244));
        callSmallBtn.setOpaque(true);
        callSmallBtn.setBorderPainted(false);
        callSmallBtn.addActionListener((e) -> {
            try {
                waitAMinute.dequeue("S");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(callSmallBtn);

        JButton callMediumBtn = new JButton("M");
        callMediumBtn.setBackground(new Color(198, 199, 255));
        callMediumBtn.setOpaque(true);
        callMediumBtn.setBorderPainted(false);
        callMediumBtn.addActionListener((e) -> {
            try {
                waitAMinute.dequeue("M");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(callMediumBtn);

        JButton callLargeBtn = new JButton("L");
        callLargeBtn.setBackground(new Color(203, 249, 255));
        callLargeBtn.setOpaque(true);
        callLargeBtn.setBorderPainted(false);
        callLargeBtn.addActionListener((e) -> {
            try {
                waitAMinute.dequeue("L");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(callLargeBtn);


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
        if (cds.size() == 0) {
            JLabel text = new JLabel("คิวว่างละจ้า \uD83D\uDE29", SwingConstants.CENTER);
            text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
            queueTable.setLayout(new BorderLayout());
            queueTable.add(text, BorderLayout.CENTER);
            return;
        }

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
            Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageIcon;
    }

    public void setWaitAMinute(WaitAMinute waitAMinute) {
        this.waitAMinute = waitAMinute;

        drawAllQueues();
    }
}

