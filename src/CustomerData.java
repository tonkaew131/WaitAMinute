import java.awt.*;

public class CustomerData {
    private String name, phoneNumber;
    private TableSize tableSize;
    private String queueId;
    private int amount;

    public enum TableSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public CustomerData() {
        tableSize = TableSize.SMALL;
        name = "";
        phoneNumber = "1234568901";
        queueId = "";
        amount = 1;
    }

    public CustomerData(String tableSize, String name, String phoneNumber, String queueId, int amount) {
        this.tableSize = toTableSize(tableSize);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.queueId = queueId;
        this.amount = amount;
    }

    public void setCustomerData(String tableSize, String name, String phoneNumber, String queueId, int amount) {
        this.tableSize = toTableSize(tableSize);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.queueId = queueId;
        this.amount = amount;
    }

    public CustomerData getCustomerData() {
        return this;
    }

    public TableSize getTableSize() {
        return tableSize;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getQueueId() {
        return String.format("%s-%s", CustomerData.tableSizeToString(tableSize), queueId);
    }

    public int getAmount() {
        return amount;
    }

    public static TableSize toTableSize(String tableSize) {
        if (tableSize.equals("S")) {
            return TableSize.SMALL;
        } else if (tableSize.equals("M")) {
            return TableSize.MEDIUM;
        }

        return TableSize.LARGE;
    }

    public static String tableSizeToString(TableSize tableSize) {
        if (tableSize == TableSize.SMALL) {
            return "S";
        } else if (tableSize == TableSize.MEDIUM) {
            return "M";
        }

        return "L";
    }

    public Color getColor() {
        if (tableSize == TableSize.SMALL) {
            return new Color(244, 200, 244);
        } else if (tableSize == TableSize.MEDIUM) {
            return new Color(198, 199, 255);
        }

        return new Color(203, 249, 255);
    }
}