/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.tables;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author edemko
 */
public class OrderStatus {
    
    private SimpleStringProperty orderStatus;

    public OrderStatus(SimpleStringProperty orderStatus) {
        this.orderStatus = orderStatus;
    }

    public SimpleStringProperty getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(SimpleStringProperty orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public static List<OrderStatus> getOrderStatuses(){
        
        //Create list
        List<OrderStatus> orderStatuses = new ArrayList<>();
        
        orderStatuses.add(new OrderStatus(new SimpleStringProperty("Canceled")));
        orderStatuses.add(new OrderStatus(new SimpleStringProperty("Not Sold")));
        orderStatuses.add(new OrderStatus(new SimpleStringProperty("Sold")));
        
        return orderStatuses;
        
    } 
    
}
