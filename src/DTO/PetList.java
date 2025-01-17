package DTO;

import DAO.Pet;
import Service.InputService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PetList extends ArrayList<Pet> {

    private final String fName = "src\\Data\\Pet";

    public PetList() {
    }

    private boolean isValidCategory(String input) {
        String[] category = {"Cat", "Dog", "Parrot"};
        for (String s : category) {
            if (input.equalsIgnoreCase(s.trim())) {
                return true;
            }
        }
        return false;
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

            Pet pet;
            while ((pet = (Pet) (fo.readObject())) != null) {
                this.add(pet);
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
                for (Pet thi : this) {
                    fo.writeObject(thi);
                }

                fo.close();
                f.close();
            }
        } catch (IOException ex) {
            System.out.println("None");
        }
    }

    public void printAll() {
        System.out.println("==================================PET LIST==========================================");
        System.out.printf("|%-5s|%-50s|%-10s|%-5s|%-5s|\n", "ID", "Description", "Imp Date", "Price", "Category");
        System.out.println("====================================================================================");
        for (Pet thi : this) {
            System.out.println(thi);
        }
        System.out.println("====================================================================================");
    }

    public Pet search(String id) {
        for (Pet thi : this) {
            if (id.equalsIgnoreCase(thi.getId())) {
                return thi;
            }
        }
        return null;
    }

    public void addPet() {
        String id;
        String description;
        Date importDate;
        double price;
        String category;
        boolean check;
        do {
            try {
                String date;
                do {
                    id = InputService.inputPattern("Enter new pet ID: ", "[pP][0-9]{3}", "Pxxx").trim().toUpperCase();//Input ID
                } while (search(id) != null);
                description = InputService.toTitleCase(InputService.inputPattern("Enter description: ", ".{3,50}", "At least 3 character!!!")); //Input description
                importDate = InputService.inputDate("Enter import date: ");
                price = InputService.inputDouble("Enter pet price: ", 0, Double.MAX_VALUE); //input price
                do {
                    category = InputService.toTitleCase(InputService.inputString("Enter category: ").trim());
                } while (!isValidCategory(category));

                this.add(new Pet(id, description, importDate, price, category));
            } catch (Exception e) {
                System.out.println("ERROR");
            }
            check = InputService.inputBoolean("Do you want to continue add more pet? (Y/N)");
        } while (check);

    }

    public void search() {
        if (this.isEmpty()) {
            System.out.println("Empty list!!");
            return;
        }
        System.out.print("Enter pet ID you wanna find: ");
        String id = InputService.SC.nextLine();
        Pet findPet = search(id);
        if (findPet == null) {
            System.out.println("The pet does not exist");
        } else {
            System.out.println(findPet);
        }
    }

    public void updatePet() {
        this.printAll();
        System.out.print("Enter pet ID you wanna update: ");
        String id = InputService.SC.nextLine();
        Pet findPet = search(id);
        if (findPet == null) {
            System.out.println("The pet does not exist");
            return;
        } else {
            System.out.println("Pet information: ");
            System.out.println(findPet);
        }
        String tmp;
        try {
            System.out.print("Enter new description: ");
            String description = InputService.SC.nextLine();
            if (description.matches(".{3,50}")) {
                findPet.setDescription(InputService.toTitleCase(description).trim());
                System.out.println("SUCCESSFUL!!");
            } else {
                System.out.println("FAILED!!");
            }

            System.out.print("Enter new import date: ");
            String date = InputService.SC.nextLine().trim();
            if (InputService.isDate(date)) {
                Date importDate = new SimpleDateFormat("mm/DD/yyyy").parse(date);
                findPet.setImportDate(importDate);
                System.out.println("SUCCESSFUL!!");
            } else {
                System.out.println("FAILED!!");
            }

            System.out.print("Enter new price: ");
            tmp = InputService.SC.nextLine().trim();
            if (InputService.isNumberic(tmp)) {
                findPet.setPrice(Double.parseDouble(tmp));
                System.out.println("SUCCESSFUL!!");
            } else {
                System.out.println("FAILED!!");
            }

            System.out.print("Enter new category: ");
            String category = InputService.SC.nextLine();
            if (isValidCategory(category)) {
                findPet.setCategory(InputService.toTitleCase(category).trim());
                System.out.println("SUCCESSFUL!!");
            } else {
                System.out.println("FAILED!!");
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public void removePet() {
        this.printAll();
        System.out.print("Enter pet ID you wanna remove: ");
        String id = InputService.SC.nextLine();
        Pet findPet = search(id);
        if (findPet == null) {
            System.out.println("The pet does not exist");
            System.out.println("FAILED!!");
        } else {
            System.out.println("Pet information: ");
            System.out.println(findPet);
            this.remove(findPet);
            System.out.println("SUCCESSFUL!!");
        }
    }

//    public static void main(String[] args) {
//        PetList petList = new PetList();
//        petList.addPet();
////        petList.addPet();
//        petList.printAll();
//        petList.saveToFile();
//    }
}
