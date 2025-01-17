package DTO;

import DTO.PetList;
import DAO.Order;
import DAO.OrderHeader;
import Service.InputService;
import Service.MenuService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class OrderList extends ArrayList<Order> {

    private String fName = "src\\Data\\Order";
    private OrderHeaderList ohList = new OrderHeaderList();
    private PetList petList;

    public OrderList(PetList petList) {
        this.petList = petList;
    }

    public void loadFromFile() {
        ohList.loadFromFile();
        File file = new File(fName);
        if (!file.exists()) {
            System.out.println("File is not existed!!");
            System.exit(0);
        }
        try {
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream fo = new ObjectInputStream(fi);

            Order o;
            while ((o = (Order) (fo.readObject())) != null) {
                this.add(o);
            }

            fo.close();
            fi.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveToFile() {
        ohList.saveToFile();
        try {
            if (this.isEmpty()) {
                System.out.println("Empty list!!");
            } else {
                FileOutputStream f = new FileOutputStream(fName);
                ObjectOutputStream fo = new ObjectOutputStream(f);
                for (Order thi : this) {
                    fo.writeObject(thi);
                }
                fo.close();
                f.close();
            }
        } catch (IOException ex) {
            System.out.println("None");
        }
    }

    public void addOrder() {
        boolean check;
        do {
            OrderHeader oh = ohList.addOrderHeader();;
            OrderDetailList odList = new OrderDetailList(petList);
            odList.addOrderDetail();
            this.add(new Order(oh, odList));
            check = InputService.inputBoolean("Do you wanna continue add Order? (Y/N): ");
        } while (check);

    }

    public void printAll() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("========================ORDER LIST============================");
        System.out.printf("|%4s|%10s|%20s|%10s|%12s|\n", "ID", "Order date", "CustomerName", "Quantity", "Total");
        System.out.println("==============================================================");
        for (Order thi : this) {
            System.out.println(thi);
        }
        System.out.println("==============================================================");
    }

    public void printByDate() {
        Date startDate = InputService.inputDate("Enter date start: ");
        Date endDate = InputService.inputDate("Enter end start: ");
        System.out.println("LIST ORDER(S) FROM " + InputService.date(startDate) + " TO " + InputService.date(endDate));
        for (Order thi : this) {
            if (thi.getOh().getOrderDate().after(startDate) && thi.getOh().getOrderDate().before(endDate)) {
                System.out.println(thi);
            }
        }
    }

    public void sortOrder() {
        int choice, choice1;
        MenuService filedMenu = new MenuService("Fieled menu", "Enter your choice: ");
        filedMenu.addOptions("Order ID:",
                "Order date",
                "Customer name",
                "Order total");
        filedMenu.display();
        choice = filedMenu.getUserChoice();

        MenuService sortMenu = new MenuService("Sort order menu", "Enter your choice: ");
        sortMenu.addOptions("ASC", "DESC");
        sortMenu.display();
        choice1 = sortMenu.getUserChoice();

        switch (choice) {
            case 1:
                Collections.sort(this, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if (choice1 == 1) {
                            return o1.getOh().getId().compareToIgnoreCase(o2.getOh().getId());
                        } else {
                            return -o1.getOh().getId().compareToIgnoreCase(o2.getOh().getId());
                        }
                    }
                });
                break;
            case 2:
                Collections.sort(this, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if (choice1 == 1) {
                            return o1.getOh().getOrderDate().compareTo(o2.getOh().getOrderDate());
                        } else {
                            return -o1.getOh().getOrderDate().compareTo(o2.getOh().getOrderDate());
                        }
                    }
                });
                break;
            case 3:
                Collections.sort(this, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if (choice1 == 1) {
                            return o1.getOh().getCustomerName().compareTo(o2.getOh().getCustomerName());
                        } else {
                            return -o1.getOh().getCustomerName().compareTo(o2.getOh().getCustomerName());
                        }
                    }
                });
                break;
            case 4:
                Collections.sort(this, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if (choice1 == 1) {
                            return (int) (o1.orderTotal() - o2.orderTotal());
                        } else {
                            return (int) -(o1.orderTotal() - o2.orderTotal());
                        }
                    }
                });
                break;
        }
        this.printAll();
    }

//    public static void main(String[] args) {
//        PetList petList = new PetList();
//        petList.loadFromFile();
//        OrderList oList = new OrderList(petList);
//        oList.loadFromFile();
////        oList.printAll();
////
////        oList.sortOrder();
////        oList.saveToFile();
//        
//        oList.printAll();
//        oList.addOrder();
//        oList.printAll();
//        oList.saveToFile();
////        oList.printByDate();
//    }
}
