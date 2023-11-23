import javax.swing.*;
import java.util.ArrayList;

public class WaitAMinute {
    QueueADT queueSmall = new Queue();
    QueueADT queueMedium = new Queue();
    QueueADT queueLarge = new Queue();
    int totalQueue = 0;
    ArrayList<CustomerData> allCustomers = new ArrayList<>();
    EmployeeMenu frameEmp;

    WaitAMinute() {
        frameEmp = new EmployeeMenu();
        frameEmp.setTitle("Queue Lists");
        frameEmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameEmp.setResizable(true);
        frameEmp.setSize(800, 600);
        frameEmp.setLocationRelativeTo(null);
        frameEmp.setVisible(true);
        frameEmp.setWaitAMinute(this);
    }

    public static void main(String args[]) {
        WaitAMinute waitAMinute = new WaitAMinute();
    }

    public void addCustomer(String name, String phoneNumber, String tableSizeStr, int amount) throws Exception {
        CustomerData.TableSize tableSize = CustomerData.toTableSize(tableSizeStr);
        CustomerData cd = new CustomerData(
                tableSizeStr, name, phoneNumber, String.valueOf(totalQueue), amount
        );
        totalQueue += 1;
        allCustomers.add(cd);
        frameEmp.drawAllQueues();

        if (tableSize == CustomerData.TableSize.LARGE) {
            queueLarge.enqueue(cd);
            System.out.println(queueLarge.length());
        } else if (tableSize == CustomerData.TableSize.MEDIUM) {
            queueMedium.enqueue(cd);
            System.out.println(queueMedium.length());
        } else {
            queueSmall.enqueue(cd);
            System.out.println(queueSmall.length());
        }
    }

    public ArrayList<CustomerData> getAllCustomers() {
        return allCustomers;
    }

    public void dequeue(String s) throws Exception {
        CustomerData.TableSize tableSize = CustomerData.toTableSize(s);
        CustomerData cd;
        if (tableSize == CustomerData.TableSize.SMALL) {
            cd = (CustomerData) queueSmall.dequeue();
        } else if (tableSize == CustomerData.TableSize.MEDIUM) {
            cd = (CustomerData) queueMedium.dequeue();
        } else {
            cd = (CustomerData) queueLarge.dequeue();
        }

        allCustomers.remove(cd);
        frameEmp.setDisplayedCustomer(cd);
        frameEmp.drawAllQueues();
    }

}
