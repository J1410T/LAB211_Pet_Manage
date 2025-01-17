package DTO;

import DTO.PetList;
import DAO.OrderDetail;
import DAO.Pet;
import Service.InputService;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OrderDetailList extends ArrayList<OrderDetail> {

    private String fName = "src\\Data\\OrderDetail";
    private PetList petList;

    public OrderDetailList(PetList petList) {
        this.petList = petList;

    }
//    
//    private void loadFromFile() {
//        File file = new File(fName);
//        if (!file.exists()) {
//            System.out.println("File is not existed!!");
//            System.exit(0);
//        }
//        try {
//            FileInputStream fi = new FileInputStream(file);
//            ObjectInputStream fo = new ObjectInputStream(fi);
//            
//            OrderDetail od;
//            while ((od = (OrderDetail)(fo.readObject())) != null){
//                this.add(od);
//            }
//            
//            fo.close();
//            fi.close();
//        } catch (Exception e) {
//        }
//    }

//    public void saveToFile() {
//        try {
//            if (this.isEmpty()){
//                System.out.println("Empty list!!");
//            } else{
//                FileOutputStream f = new FileOutputStream(fName);
//                ObjectOutputStream fo = new ObjectOutputStream(f);
//                for (OrderDetail thi : this) {
//                    fo.writeObject(thi);
//                }
//                fo.close();
//                f.close();
//            }
//        } catch (IOException ex) {
//            System.out.println("None");
//        }
//    }
    public OrderDetail search(String id) {
        for (OrderDetail thi : this) {
            if (thi.getIdDetail().equals(id)) {
                return thi;
            }
        }
        return null;
    }

    public OrderDetailList addOrderDetail() {
        String idDetail;
        String petId;
        Pet pet;
        int quantity;
        boolean check = true;
        do {
            do {
                idDetail = InputService.inputPattern("Enter detail id: ", "[dD][0-9]{3}", "Dxxx").trim().toUpperCase();
            } while (this.search(idDetail) != null);

            do {
                petList.printAll();
                petId = InputService.inputPattern("Enter pet id: ", "[pP][0-9]{3}", "Dxxx").trim().toUpperCase();
            } while (petList.search(petId) == null);
            pet = petList.search(petId);

            quantity = InputService.inputInt("Enter quantity: ", 0, Integer.MAX_VALUE);
            OrderDetail od = new OrderDetail(idDetail, pet, quantity);
            this.add(od);
            check = InputService.inputBoolean("Do you wanna continue add order detail? (Y/N): ");
        } while (check);
        return this;
    }

    public void printAll() {
        for (OrderDetail thi : this) {
            System.out.println(thi.toString());
        }
    }

//    public static void main(String[] args) {
//        PetList petList = new PetList();
//        OrderDetailList odList = new OrderDetailList(petList);
//        
//        odList.addOrderDetail();
//        odList.printAll();
//    }
}
