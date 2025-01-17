package DAO;

import Service.InputService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class OrderHeader implements Serializable {

    private String id;
    private Date orderDate;
    private String customerName;

    public OrderHeader(String id, Date orderDate, String customerName) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    public String date(){
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(orderDate);
//        return String.format("%2s/%2s/%4s", cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR));
//    }
    @Override
    public String toString() {
        return String.format("|%4s|%10s|%20s|", id, InputService.date(orderDate), customerName);
    }

}
