/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Erik PC
 */
public class Customer {
    
    private SimpleStringProperty customer_lastName, customer_firstName, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment;
    private SimpleIntegerProperty customer_id;

    public Customer(SimpleStringProperty customer_lastName, SimpleStringProperty customer_firstName, SimpleStringProperty customer_mail, SimpleStringProperty customer_phone, SimpleStringProperty customer_address, SimpleStringProperty customer_city, SimpleStringProperty customer_zipCode, SimpleStringProperty customer_country, SimpleStringProperty customer_company, SimpleStringProperty customer_comment, SimpleIntegerProperty customer_id) {
        this.customer_lastName = customer_lastName;
        this.customer_firstName = customer_firstName;
        this.customer_mail = customer_mail;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_city = customer_city;
        this.customer_zipCode = customer_zipCode;
        this.customer_country = customer_country;
        this.customer_company = customer_company;
        this.customer_comment = customer_comment;
        this.customer_id = customer_id;
    }

    public SimpleStringProperty getCustomer_lastName() {
        return customer_lastName;
    }

    public void setCustomer_lastName(SimpleStringProperty customer_lastName) {
        this.customer_lastName = customer_lastName;
    }

    public SimpleStringProperty getCustomer_firstName() {
        return customer_firstName;
    }

    public void setCustomer_firstName(SimpleStringProperty customer_firstName) {
        this.customer_firstName = customer_firstName;
    }

    public SimpleStringProperty getCustomer_mail() {
        return customer_mail;
    }

    public void setCustomer_mail(SimpleStringProperty customer_mail) {
        this.customer_mail = customer_mail;
    }

    public SimpleStringProperty getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(SimpleStringProperty customer_phone) {
        this.customer_phone = customer_phone;
    }

    public SimpleStringProperty getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(SimpleStringProperty customer_address) {
        this.customer_address = customer_address;
    }

    public SimpleStringProperty getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(SimpleStringProperty customer_city) {
        this.customer_city = customer_city;
    }

    public SimpleStringProperty getCustomer_zipCode() {
        return customer_zipCode;
    }

    public void setCustomer_zipCode(SimpleStringProperty customer_zipCode) {
        this.customer_zipCode = customer_zipCode;
    }

    public SimpleStringProperty getCustomer_country() {
        return customer_country;
    }

    public void setCustomer_country(SimpleStringProperty customer_country) {
        this.customer_country = customer_country;
    }

    public SimpleStringProperty getCustomer_company() {
        return customer_company;
    }

    public void setCustomer_company(SimpleStringProperty customer_company) {
        this.customer_company = customer_company;
    }

    public SimpleStringProperty getCustomer_comment() {
        return customer_comment;
    }

    public void setCustomer_comment(SimpleStringProperty customer_comment) {
        this.customer_comment = customer_comment;
    }

    public SimpleIntegerProperty getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(SimpleIntegerProperty customer_id) {
        this.customer_id = customer_id;
    }
    
    
}
