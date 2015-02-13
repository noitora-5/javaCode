/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author noitora
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByEstado", query = "SELECT e FROM Empresa e WHERE e.estadoEmpresa = 'Pendiente'"),
    @NamedQuery(name = "Empresa.findByEstadoA", query = "SELECT e FROM Empresa e WHERE e.estadoEmpresa = 'Aceptada'"),
    @NamedQuery(name = "Empresa.findByEstadoR", query = "SELECT e FROM Empresa e WHERE e.estadoEmpresa = 'Rechazada'"),
    @NamedQuery(name = "Empresa.contar", query = "SELECT COUNT(e.idEmpresa) FROM Empresa e "),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresa.findByNombreEmpresa", query = "SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa"),
    @NamedQuery(name = "Empresa.findByRolEmpresa", query = "SELECT e FROM Empresa e WHERE e.rolEmpresa = :rolEmpresa"),
    @NamedQuery(name = "Empresa.findByDireccionEmpresa", query = "SELECT e FROM Empresa e WHERE e.direccionEmpresa = :direccionEmpresa"),
    @NamedQuery(name = "Empresa.findByTelefonoEmpresa", query = "SELECT e FROM Empresa e WHERE e.telefonoEmpresa = :telefonoEmpresa"),
    @NamedQuery(name = "Empresa.findByEstadoEmpresa", query = "SELECT e FROM Empresa e WHERE e.estadoEmpresa = :estadoEmpresa")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmpresa")
    private Integer idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreEmpresa")
    private String nombreEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "rolEmpresa")
    private String rolEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "direccionEmpresa")
    private String direccionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefonoEmpresa")
    private int telefonoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estadoEmpresa")
    private String estadoEmpresa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolEmpresa")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "nombreRegion", referencedColumnName = "nombreRegion")
    @ManyToOne(optional = false)
    private Region nombreRegion;
    @JoinColumn(name = "nombreCiudad", referencedColumnName = "nombreCiudad")
    @ManyToOne(optional = false)
    private Ciudad nombreCiudad;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(Integer idEmpresa, String nombreEmpresa, String rolEmpresa, String direccionEmpresa, int telefonoEmpresa, String estadoEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.rolEmpresa = rolEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.estadoEmpresa = estadoEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRolEmpresa() {
        return rolEmpresa;
    }

    public void setRolEmpresa(String rolEmpresa) {
        this.rolEmpresa = rolEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public int getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(int telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(String estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Region getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(Region nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public Ciudad getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(Ciudad nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}