package GUI;

import DAO.OrderHeader;
import DTO.OrderHeaderList;
import DTO.OrderList;
import DTO.PetList;
import Service.MenuService;

public class Main {

    public static void main(String[] args) {
        PetList petList = new PetList();
        OrderList oList = new OrderList(petList);
        MenuService toolMenu = new MenuService("Tool menu", "Enter your choice: ");
        toolMenu.addOptions("Add a pet",
                "Find a pet",
                "Update a pet",
                "Delete a pet",
                "Add an order",
                "List orders",
                "Sort orders",
                "Save data",
                "Load data",
                "Quit");

        int choice;
        boolean petChanged = false;
        boolean orderChanged = false;
        do {
            toolMenu.display();
            choice = toolMenu.getUserChoice();
            switch (choice) {
                case 1:
                    petList.addPet();
                    petChanged = true;
                    break;

                case 2:
                    petList.search();
                    break;
                case 3:
                    petList.updatePet();
                    petChanged = true;
                    break;
                case 4:
                    petList.removePet();
                    petChanged = true;
                    break;
                case 5:
                    oList.addOrder();
                    break;
                case 6:
                    oList.printByDate();
                    orderChanged = true;
                    break;
                case 7:
                    oList.sortOrder();
                    orderChanged = true;
                    break;
                case 8:
                    oList.saveToFile();
                    petList.saveToFile();
                    break;
                case 9:
                    petList.loadFromFile();
                    oList.loadFromFile();
                    petList.printAll();
                    oList.printAll();
                    break;
                default:
                    if (petChanged) {
                        petList.saveToFile();
                    }
                    if (orderChanged) {
                        oList.saveToFile();
                    }
                    choice = 10;
                    break;
            }
        } while (choice != 10);
    }

}
