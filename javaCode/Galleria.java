/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author dolca
 */
@ManagedBean(name = "galleria")
public class Galleria {
    
     private List<String> images; 
     
      @PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
        images.add("Varias Direcciones.JPG");
        images.add("Alta Luminosidad.jpg");
        images.add("Alta Precision.jpg");
        images.add("Flecha Direccional.jpg");
        images.add("Flechas de 5 Señales.jpg");
        images.add("Focos de Iluminacion.jpg"); 
        images.add("Innovacion.jpg");
        images.add("Presentacion Industrial.jpg");
        images.add("Señales Viales.jpg");
        images.add("Tecnologia Led.jpg");
         ;
        
     
        
      
    }  
      
       public List<String> getImages() {  
        return images;  
    
}
}
