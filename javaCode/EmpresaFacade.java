/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Empresa;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author noitora
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {
    @PersistenceContext(unitName = "PowerLedSolarProjectPU")
    private EntityManager em;
    private List<Empresa> empresa;
    private List<Empresa> empresa2;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    public List<Empresa> lastID(){
        List<Integer> id;
        int a= 0;
        id =em.createNamedQuery("Empresa.findAll").getResultList();
        a = id.size();
        empresa = em.createNamedQuery("Empresa.findByIdEmpresa").setParameter("idEmpresa",a).getResultList();
        return empresa; 
    }
    public List<Empresa> listaTodo(){
         
          empresa = em.createNamedQuery("Empresa.findAll").getResultList();
          return empresa; 
     }
    
    public List<Empresa> beanLoginEmpresa(int idEmpresaLogin){
        empresa2 = em.createNamedQuery("Empresa.findByIdEmpresa").setParameter("idEmpresa",idEmpresaLogin).getResultList();
        return empresa2; 
    }
    public List<Empresa> listByEstado(){
         
          empresa = em.createNamedQuery("Empresa.findByEstado").getResultList();
          return empresa; 
     }
          public List<Empresa> listByEstadoA(){
         
          empresa = em.createNamedQuery("Empresa.findByEstadoA").getResultList();
          return empresa; 
     }
           public List<Empresa> listByEstadoR(){
         
          empresa = em.createNamedQuery("Empresa.findByEstadoR").getResultList();
          return empresa; 
     }
     
    
}
    

