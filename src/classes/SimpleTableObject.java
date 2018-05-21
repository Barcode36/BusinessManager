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
 * @author edemko
 */
public class SimpleTableObject {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public SimpleTableObject(SimpleIntegerProperty id, SimpleStringProperty name) {
        this.id = id;
        this.name = name;
    }

    public SimpleIntegerProperty getId() {
        return id;
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }
    
    
    
}
