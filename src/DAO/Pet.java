package DAO;

import Service.InputService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Pet implements Serializable {

    private String id;
    private String description;
    private Date importDate;
    private double price;
    private String category;

    public Pet(String id, String description, Date importDate, double price, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    private String date(){
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(importDate);
//        return String.format("%2s/%2s/%4s", cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR));
//    }
    @Override
    public String toString() {
        return String.format("|%-5s|%-50s|%-10s|%-5s|%-8s|", id, description, InputService.date(importDate), price, category);
    }

}
