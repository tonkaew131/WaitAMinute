import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class QueueMenu extends JFrame {
    JLabel frontSmallName = new JLabel("-");
    JLabel frontMediumName = new JLabel("-");
    JLabel frontLargeName = new JLabel("-");
    JLabel totalWaitQueue = new JLabel("0", SwingConstants.CENTER);
    WaitAMinute waitAMinute;

//    public static void main(String args[]) {
//        QueueMenu frame = new QueueMenu();
//    }

    public QueueMenu() {
        setLayout(new GridLayout(1, 2, 0, 0));
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new GridLayout(2, 1, 10, 10));

        Color bgColor = new Color(255, 254, 202);
        amountPanel.setBackground(bgColor);

        JLabel logoLabel = new JLabel(getLogo());
        amountPanel.add(logoLabel);

        JPanel amountDetailPanel = new JPanel();
        amountDetailPanel.setBackground(bgColor);
        amountDetailPanel.setLayout(new BoxLayout(amountDetailPanel, BoxLayout.Y_AXIS));

        JLabel amountText = new JLabel("จำนวนคิว", SwingConstants.CENTER);
        amountText.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        amountDetailPanel.add(amountText);

        totalWaitQueue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
        amountDetailPanel.add(totalWaitQueue);

        amountPanel.add(amountDetailPanel);

        add(amountPanel);
        setTitle("Queue Information");
        setBackground(new Color(255, 254, 202));
        setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel frontPanel = new JPanel();
        frontPanel.setBackground(bgColor);
        frontPanel.setLayout(new GridLayout(3, 2, 0, 10));
        frontPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        JLabel smallLabel = new JLabel("โต๊ะเล็ก");
        smallLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        frontSmallName.setBackground(new Color(244, 200, 244));
        frontSmallName.setOpaque(true);
        smallLabel.setBorder(new EmptyBorder(0, 50, 0, 0));
        smallLabel.setBackground(new Color(244, 200, 244));
        smallLabel.setOpaque(true);
        frontSmallName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
        frontPanel.add(smallLabel);
        frontPanel.add(frontSmallName);

        JLabel mediumLabel = new JLabel("โต๊ะกลาง");
        mediumLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        frontMediumName.setBackground(new Color(198, 199, 255));
        frontMediumName.setOpaque(true);
        mediumLabel.setBorder(new EmptyBorder(0, 50, 0, 0));
        mediumLabel.setBackground(new Color(198, 199, 255));
        mediumLabel.setOpaque(true);
        frontMediumName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
        frontPanel.add(mediumLabel);
        frontPanel.add(frontMediumName);

        JLabel largeLabel = new JLabel("โต๊ะใหญ่");
        largeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        frontLargeName.setBackground(new Color(203, 249, 255));
        frontLargeName.setOpaque(true);
        largeLabel.setBorder(new EmptyBorder(0, 50, 0, 0));
        largeLabel.setBackground(new Color(203, 249, 255));
        largeLabel.setOpaque(true);
        frontLargeName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
        frontPanel.add(largeLabel);
        frontPanel.add(frontLargeName);

        updateQueue("S");
        updateQueue("M");
        updateQueue("L");

        add(frontPanel);
    }

    public void setWaitAMinute(WaitAMinute waitAMinute) {
        this.waitAMinute = waitAMinute;
    }

    public void updateQueue(String s) {
        if (waitAMinute != null)
            totalWaitQueue.setText(String.valueOf(
                            waitAMinute.getQueueSmall().length() +
                                    waitAMinute.getQueueMedium().length() +
                                    waitAMinute.getQueueLarge().length()
                    )
            );

        if (s.equals("S")) {
            try {
                frontSmallName.setText(waitAMinute.front("S").getQueueId());
                System.out.println("S");
            } catch (Exception e) {
                frontSmallName.setText("-");
                System.out.println("S E");
            }
        } else if (s.equals("M")) {
            try {
                frontMediumName.setText(waitAMinute.front("M").getQueueId());
                System.out.println("M");
            } catch (Exception e) {
                frontMediumName.setText("-");
                System.out.println("M E");
            }
        } else if (s.equals("L")) {
            try {
                frontLargeName.setText(waitAMinute.front("L").getQueueId());
                System.out.println("L");
            } catch (Exception e) {
                frontLargeName.setText("-");
                System.out.println("L E");
            }
        }

        revalidate();
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
}
