package DAO;

import DTO.PetList;
import java.io.Serializable;

public class OrderDetail extends PetList implements Serializable {

    private String idDetail;
    private Pet pet;
    private int quantity;
    private double petCost;

    public OrderDetail(String idDetail, Pet pet, int quantity) {
        this.idDetail = idDetail;
        this.pet = pet;
        this.quantity = quantity;
        this.petCost = quantity * pet.getPrice();
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPetCost() {
        return petCost;
    }

    public void setPetCost(double petCost) {
        this.petCost = petCost;
    }

//    @Override
//    public String toString() {
//        return String.format("|%4s|%4s|%3s|%20s|", idDetail, petId, quantity, petCost);
//    }
}
