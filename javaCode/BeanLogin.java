/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Conexion;
import Entity.Empresa;
import Entity.Role;
import Entity.Usuario;
import Entity.util.JsfUtil;
import Facade.EmpresaFacade;
import Facade.UsuarioFacade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 */

@SessionScoped
public class BeanLogin {

    private String nickname;
    private String contrasena;
    private Usuario usuario;
    private Empresa empresa;
    @EJB
    private UsuarioFacade ejbFacadeUsuario;
    @EJB
    private EmpresaFacade ejbFacadeEmpresa;

    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UsuarioFacade getEjbFacadeUsuario() {
        return ejbFacadeUsuario;
    }
    
    public EmpresaFacade getEjbFacadeEmpresa() {
        return ejbFacadeEmpresa;
    }

    
    public String login(){
        if(loginAccion()){
            //crear sesion 
            if("Aceptada".equals(empresa.getEstadoEmpresa().toString())){
                if("Administrador".equals(usuario.getNombreRole().toString()))
                {
                    return "loginAdministrador";
                }
                if("Usuario".equals(usuario.getNombreRole().toString()))
                {
                    return "loginCliente";
                }
                else
                {
                    return "loginError";
                } 
            }
            if("Pendiente".equals(empresa.getEstadoEmpresa().toString())){
                return "empresaPendiente";
            }
            if("Rechazada".equals(empresa.getEstadoEmpresa().toString())){
                return "empresaRechazada";
            }
            else
            {
                return "loginError";
            }
        }
        else{
             JsfUtil.addErrorMessage("Credenciales Incorrectas");
            return "";
        }   
    }
    
    public Usuario credencialesUsuario(){
        
        Usuario usuario = new Usuario();
        List<Usuario> u;
        Usuario usu = new Usuario();
        u = ejbFacadeUsuario.beanLoginUsuario(nickname,contrasena);
            Iterator<Usuario> it = u.iterator();
        while(it.hasNext()){
            usuario =it.next();
            usu = usuario;
        }
        return usu;  
    }
    
    public Empresa credencialesEmpresa(){
        
        Empresa empresa = new Empresa();
        Empresa empresa2= usuario.getIdEmpresa();
        int numero = empresa2.getIdEmpresa();
        List<Empresa> e;
        Empresa eme = new Empresa();
        e = ejbFacadeEmpresa.beanLoginEmpresa(numero);
        Iterator<Empresa> it = e.iterator();
        while(it.hasNext()){
            empresa =it.next();
            eme = empresa;
        }
        return eme;    
    }
    
    private boolean loginAccion(){
        usuario = credencialesUsuario();
        if(usuario.getIdEmpresa() == null)
        {
            return false;
        }
        else
        {
            empresa = credencialesEmpresa();
            if(empresa.getIdEmpresa() == null)
            {
                return false;
            }
            else
            {
            return true;
            }
        }   
    }

    public String cerrarSesion(){
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      session.removeAttribute("LoginBean");
      return "cerrar";
    }
    
    
    public SelectItem[] getItemsAvailableSelectManyEmpresaSolicitud() {
        return JsfUtil.getSelectItems(ejbFacadeEmpresa.beanLoginEmpresa(empresa.getIdEmpresa()), false);
   }
    
}