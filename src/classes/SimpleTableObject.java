/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author edemko
 */
public class SimpleTableObject {
    
    private SimpleIntegerProperty property_id, property_type_id;
    private SimpleStringProperty property_name;

    public SimpleTableObject(SimpleIntegerProperty property_id, SimpleIntegerProperty property_type_id, SimpleStringProperty property_type_name) {
        this.property_id = property_id;
        this.property_type_id = property_type_id;
        this.property_name = property_type_name;
    }

    public SimpleIntegerProperty getProperty_id() {
        return property_id;
    }

    public void setProperty_id(SimpleIntegerProperty property_id) {
        this.property_id = property_id;
    }

    public SimpleIntegerProperty getProperty_type_id() {
        return property_type_id;
    }

    public void setProperty_type_id(SimpleIntegerProperty property_type_id) {
        this.property_type_id = property_type_id;
    }

    public SimpleStringProperty getProperty_name() {
        return property_name;
    }

    public void setProperty_name(SimpleStringProperty property_name) {
        this.property_name = property_name;
    }

    //get number of properties of particular type passed as parameter - used for statistic calculation like number of material types, number of colors, etc....
    public static int getNumberOfProperties(List<SimpleTableObject> list, int type_id){
        
        int counter = 0;
        
        for (int i = 0; i < list.size(); i++) {
            
            SimpleTableObject obj = list.get(i);
            
            if(obj.getProperty_type_id().get() == type_id)
            counter++;
        }        
        return counter;
    }
    
    
    
}
