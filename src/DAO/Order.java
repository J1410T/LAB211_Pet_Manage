package DAO;

import DTO.OrderDetailList;
import Service.InputService;
import java.io.Serializable;

public class Order implements Serializable {

    private OrderHeader oh;
    private OrderDetailList odList;

    public Order(OrderHeader oh, OrderDetailList odList) {
        this.oh = oh;
        this.odList = odList;
    }

    public OrderHeader getOh() {
        return oh;
    }

    public void setOh(OrderHeader oh) {
        this.oh = oh;
    }

    public OrderDetailList getOdList() {
        return odList;
    }

    public void setOdList(OrderDetailList odList) {
        this.odList = odList;
    }

    private int petCount() {
        int sum = 0;
        for (OrderDetail od : odList) {
            sum += od.getQuantity();
        }
        return sum;
    }

    public double orderTotal() {
        double sum = 0;
        for (OrderDetail od : odList) {
            sum += od.getPetCost();
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("|%4s|%10s|%20s|%10s|%12s|", oh.getId(), InputService.date(oh.getOrderDate()), oh.getCustomerName(), petCount(), orderTotal());
    }

}
