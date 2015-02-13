/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author dolca
 */
@ManagedBean(name = "galleria1")

public class Galleria2 {
    
     private List<Productos> productos;  
  
    private Productos selectedProducto;  
    
    @PostConstruct  
    public void init() {  
        
  
        productos = new ArrayList<Productos>();  
  
        productos.add(new Productos("vbcbc", "historia1.jpg", "CFbbbbbbbbb"));  
        productos.add(new Productos("Inibcvbesta","historia2.jpg", "CMssssssss"));  
        productos.add(new Productos("Vivvcbclla", "historia1.jpg", "CFdddd"));  
        productos.add(new Productos("Xabcvvi","historia2.jpg", "CM"));  
        productos.add(new Productos("Puvcbcvbyol","historia1.jpg", "CB"));  
    }  
  
    public Productos getSelectedProducto() {  
        return selectedProducto;  
    }  
  
    public void setSelectedProducto(Productos selectedProducto) {  
        this.selectedProducto = selectedProducto;  
    }  

    public List<Productos> getProductos() {
        return productos;
    }
  
    
    
}
