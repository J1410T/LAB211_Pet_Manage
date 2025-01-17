package DTO;

import DAO.OrderHeader;
import Service.InputService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class OrderHeaderList extends ArrayList<OrderHeader> {

    private final String fName = "src\\Data\\OrderHeader";

    public OrderHeaderList() {
        this.loadFromFile();
    }

    public void loadFromFile() {
        File file = new File(fName);
        if (!file.exists()) {
            System.out.println("File is not existed!!");
            System.exit(0);
        }
        try {
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream fo = new ObjectInputStream(fi);

            OrderHeader oh;
            while ((oh = (OrderHeader) (fo.readObject())) != null) {
                this.add(oh);
            }

            fo.close();
            fi.close();
        } catch (Exception e) {
        }
    }

    public void saveToFile() {
        try {
            if (this.isEmpty()) {
                System.out.println("Empty list!!");
            } else {
                FileOutputStream f = new FileOutputStream(fName);
                ObjectOutputStream fo = new ObjectOutputStream(f);
                for (OrderHeader thi : this) {
                    fo.writeObject(thi);
                }
                fo.close();
                f.close();
            }
        } catch (IOException ex) {
            System.out.println("None");
        }
    }

    public OrderHeader search(String id) {
        for (OrderHeader oh : this) {
            if (oh.getId().equalsIgnoreCase(id.trim())) {
                return oh;
            }
        }
        return null;
    }

    public void printAll() {
        for (OrderHeader thi : this) {
            System.out.println(thi);
        }
    }

    public OrderHeader addOrderHeader() {
        String id;
        Date orderDate;
        String customerName;
        do {
            id = InputService.inputPattern("Enter order ID: ", "[oO][0-9]{3}", "Oxxx, x is digit").trim().toUpperCase();
        } while (search(id) != null);

        orderDate = InputService.inputDate("Enter order date ");
        customerName = InputService.inputString("Enter customer name: ").trim().toUpperCase();
        OrderHeader newOH = new OrderHeader(id, orderDate, customerName);
        this.add(newOH);
        return newOH;

    }

//    public static void main(String[] args) {
//        OrderHeaderList ohList = new OrderHeaderList();
//        ohList.printAll();
//        ohList.addOrderHeader();
//        ohList.saveToFile();
//    }
}
