package Service;

import Service.InputService;
import java.util.Scanner;
import java.util.Vector;

public class MenuService {
    private  Vector<String> menu = new Vector<>();
    private  Scanner sc = new Scanner(System.in);
    private String title;
    private String inputMessage;
    public MenuService(String title, String inputMessage) {
        this.title = title;
        this.inputMessage = inputMessage;
    }

    public  void addOptions(String... options) {
        for (String o : options) {
            menu.add(o);
        }
    }
    
    public  void display() {
        System.out.println("");
        System.out.println("====== " + title + " ======");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
        System.out.println("");
    }
    
    public  int getUserChoice() {
        return InputService.inputInt(inputMessage, 1, menu.size());
    }

}
