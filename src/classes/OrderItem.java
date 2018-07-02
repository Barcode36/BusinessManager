/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Erik PC
 */
public class OrderItem {
    
    private SimpleStringProperty  object_name, object_buildTime_formated, printer_name, material_type, material_color;
    private SimpleIntegerProperty object_id, object_buildTime, quantity, printer_id, material_id;
    private SimpleDoubleProperty object_supportWeight, object_weight, price, costs;

    public OrderItem(SimpleStringProperty object_name, SimpleStringProperty object_buildTime_formated, SimpleStringProperty printer_name, SimpleStringProperty material_type, SimpleStringProperty material_color, SimpleIntegerProperty object_id, SimpleIntegerProperty object_buildTime, SimpleIntegerProperty qunatity, SimpleIntegerProperty printer_id, SimpleIntegerProperty material_id, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight, SimpleDoubleProperty price, SimpleDoubleProperty costs) {
        this.object_name = object_name;
        this.object_buildTime_formated = object_buildTime_formated;
        this.printer_name = printer_name;
        this.material_type = material_type;
        this.material_color = material_color;
        this.object_id = object_id;
        this.object_buildTime = object_buildTime;
        this.quantity = qunatity;
        this.printer_id = printer_id;
        this.material_id = material_id;
        this.object_supportWeight = object_supportWeight;
        this.object_weight = object_weight;
        this.price = price;
        this.costs = costs;
    }

    public SimpleStringProperty getObject_name() {
        return object_name;
    }

    public void setObject_name(SimpleStringProperty object_name) {
        this.object_name = object_name;
    }

    public SimpleStringProperty getObject_buildTime_formated() {
        return object_buildTime_formated;
    }

    public void setObject_buildTime_formated(SimpleStringProperty object_buildTime_formated) {
        this.object_buildTime_formated = object_buildTime_formated;
    }

    public SimpleStringProperty getPrinter_name() {
        return printer_name;
    }

    public void setPrinter_name(SimpleStringProperty printer_name) {
        this.printer_name = printer_name;
    }

    public SimpleStringProperty getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(SimpleStringProperty material_type) {
        this.material_type = material_type;
    }

    public SimpleStringProperty getMaterial_color() {
        return material_color;
    }

    public void setMaterial_color(SimpleStringProperty material_color) {
        this.material_color = material_color;
    }

    public SimpleIntegerProperty getObject_id() {
        return object_id;
    }

    public void setObject_id(SimpleIntegerProperty object_id) {
        this.object_id = object_id;
    }

    public SimpleIntegerProperty getObject_buildTime() {
        return object_buildTime;
    }

    public void setObject_buildTime(SimpleIntegerProperty object_buildTime) {
        this.object_buildTime = object_buildTime;
    }

    public SimpleIntegerProperty getQunatity() {
        return quantity;
    }

    public void setQunatity(SimpleIntegerProperty qunatity) {
        this.quantity = qunatity;
    }

    public SimpleIntegerProperty getPrinter_id() {
        return printer_id;
    }

    public void setPrinter_id(SimpleIntegerProperty printer_id) {
        this.printer_id = printer_id;
    }

    public SimpleIntegerProperty getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(SimpleIntegerProperty material_id) {
        this.material_id = material_id;
    }

    public SimpleDoubleProperty getObject_supportWeight() {
        return object_supportWeight;
    }

    public void setObject_supportWeight(SimpleDoubleProperty object_supportWeight) {
        this.object_supportWeight = object_supportWeight;
    }

    public SimpleDoubleProperty getObject_weight() {
        return object_weight;
    }

    public void setObject_weight(SimpleDoubleProperty object_weight) {
        this.object_weight = object_weight;
    }

    public SimpleDoubleProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleDoubleProperty price) {
        this.price = price;
    }

    public SimpleDoubleProperty getCosts() {
        return costs;
    }

    public void setCosts(SimpleDoubleProperty costs) {
        this.costs = costs;
    }


 }
