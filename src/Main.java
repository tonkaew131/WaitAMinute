import javax.swing.*;

public class Main {
    static CustomerData ctm;
    static QueueADT queueSmall = new Queue();
    static QueueADT queueMedium = new Queue();
    static QueueADT queueLarge = new Queue();
    static int totalCustomer = 0;

    public static void main(String[] args) {
        String optionInput = JOptionPane.showInputDialog(null,
                "-----------------------------------\n" +
                        "       Menu\n" +
                        "-----------------------------------\n" +
                        "     1.Enqueue\n" +
                        "     2.Dequeue\n" +
                        "     3.Front\n" +
                        "     4.Length\n" +
                        "     5.Clear\n" +
                        "     6.Exit\n" +
                        "-----------------------------------\n");
        switch (optionInput) {
            case "1":
                try {
                    String tableSize = JOptionPane.showInputDialog(null, "Please input Table Size (S,M,L): ");

                    totalCustomer += 1;
                    ctm = getElement(tableSize);

                    if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.SMALL) {
                        queueSmall.enqueue(ctm);
                    } else if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.MEDIUM) {
                        queueMedium.enqueue(ctm);
                    } else {
                        queueLarge.enqueue(ctm);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                main(new String[]{});
                break;
            case "2":
                try {
                    String tableSize = JOptionPane.showInputDialog(null, "Which table size? (S,M,L): ");

                    QueueADT data;
                    if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.SMALL) {
                        data = queueSmall;
                    } else if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.MEDIUM) {
                        data = queueMedium;
                    } else {
                        data = queueLarge;
                    }

                    if (!data.isEmpty()) {
                        CustomerData dequeuectm = (CustomerData) data.dequeue();
                        JOptionPane.showMessageDialog(null,
                                "The first Queue \n" + "Customer Queue :  " + dequeuectm.getQueueId() +
                                        "\nName:  " + dequeuectm.getName() +
                                        "\nTable Size:  " + dequeuectm.getTableSize() +
                                        "\nNumber of Customers :  " + dequeuectm.getAmount() +
                                        "\nTelephone number:  " + dequeuectm.getPhoneNumber());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    main(new String[]{});
                }
                main(new String[]{});
                break;
            case "3":
                try {
                    String tableSize = JOptionPane.showInputDialog(null, "Which table size? (S,M,L): ");

                    QueueADT data;
                    if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.SMALL) {
                        data = queueSmall;
                    } else if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.MEDIUM) {
                        data = queueMedium;
                    } else {
                        data = queueLarge;
                    }

                    CustomerData Firstctm = (CustomerData) data.front();
                    JOptionPane.showMessageDialog(null, "The first Queue \n" + "ID:  " + Firstctm.getName() +
                            "\nName :  " + Firstctm.getName() +
                            "\nTable Size :  " + Firstctm.getTableSize() +
                            "\nNumber of Customers :  " + Firstctm.getAmount() +
                            "\nTelephone number :  " + Firstctm.getPhoneNumber());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    main(new String[]{});
                }
                main(new String[]{});
                break;
            case "4":
                try {
                    String tableSize = JOptionPane.showInputDialog(null, "Which table size? (S,M,L): ");

                    QueueADT data;
                    if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.SMALL) {
                        data = queueSmall;
                    } else if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.MEDIUM) {
                        data = queueMedium;
                    } else {
                        data = queueLarge;
                    }

                    JOptionPane.showMessageDialog(null, "This queue length is " + data.length());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    main(new String[]{});
                }
                main(new String[]{});
                break;
            case "5":
                String tableSize = JOptionPane.showInputDialog(null, "Which table size? (S,M,L): ");

                QueueADT data;
                if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.SMALL) {
                    data = queueSmall;
                } else if (CustomerData.toTableSize(tableSize) == CustomerData.TableSize.MEDIUM) {
                    data = queueMedium;
                } else {
                    data = queueLarge;
                }

                data.clear();
                JOptionPane.showMessageDialog(null, "Queue is cleared", "Message", JOptionPane.INFORMATION_MESSAGE);
                main(new String[]{});
                break;
            case "6":
                break;

        }

    }

    public static CustomerData getElement(String tableSize) {
        CustomerData temp;
        String names = JOptionPane.showInputDialog(null, "Please input Customer Name: ");
        String queueId = String.format("%03d", totalCustomer);
        int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input Number of Customer: "));
        String phoneNumber = JOptionPane.showInputDialog(null, "Please input Telephone: ");

        temp = new CustomerData(tableSize, names, phoneNumber, queueId, amount);
        // temp.setStudent(idS, nameS, midS, finalS, quizS, prjS, collabS);
        return temp;
    }
    }

