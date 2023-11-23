import javax.swing.*;
import java.util.ArrayList;

public class WaitAMinute {
    QueueADT queueSmall = new Queue();
    QueueADT queueMedium = new Queue();
    QueueADT queueLarge = new Queue();
    int totalQueue = 1;
    ArrayList<CustomerData> allCustomers = new ArrayList<>();
    EmployeeMenu frameEmp;
    QueueMenu frameQueue;

    WaitAMinute() {
        FontManager.loadFont();

        frameEmp = new EmployeeMenu();
        frameEmp.setTitle("Queue Lists");
        frameEmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameEmp.setResizable(true);
        frameEmp.setSize(800, 600);
        frameEmp.setLocationRelativeTo(null);
        frameEmp.setVisible(true);
        frameEmp.setWaitAMinute(this);

        frameQueue = new QueueMenu();
        frameQueue.setWaitAMinute(this);
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
//            System.out.println(queueLarge.length());
        } else if (tableSize == CustomerData.TableSize.MEDIUM) {
            queueMedium.enqueue(cd);
//            System.out.println(queueMedium.length());
        } else {
            queueSmall.enqueue(cd);
//            System.out.println(queueSmall.length());
        }

        frameQueue.updateQueue("");
    }

    public ArrayList<CustomerData> getAllCustomers() {
        return allCustomers;
    }

    public void dequeue(String s) throws Exception {
        frameQueue.updateQueue(s);

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

        frameQueue.updateQueue("");
    }

    public CustomerData front(String s) throws Exception {
        CustomerData.TableSize tableSize = CustomerData.toTableSize(s);
        if (tableSize == CustomerData.TableSize.LARGE) {
            return (CustomerData) queueLarge.front();
        } else if (tableSize == CustomerData.TableSize.MEDIUM) {
            return (CustomerData) queueMedium.front();
        } else {
            return (CustomerData) queueSmall.front();
        }
    }

    public QueueADT getQueueSmall() {
        return queueSmall;
    }

    public QueueADT getQueueMedium() {
        return queueMedium;
    }

    public QueueADT getQueueLarge() {
        return queueLarge;
    }
}
