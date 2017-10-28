/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marianacro
 */
@Entity
@Table(name = "Jugadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugadores.findAll", query = "SELECT j FROM Jugadores j"),
    @NamedQuery(name = "Jugadores.findByIdJugadores", query = "SELECT j FROM Jugadores j WHERE j.idJugadores = :idJugadores"),
    @NamedQuery(name = "Jugadores.findByUsuario", query = "SELECT j FROM Jugadores j WHERE j.usuario = :usuario"),
    @NamedQuery(name = "Jugadores.findByClave", query = "SELECT j FROM Jugadores j WHERE j.clave = :clave")})
public class Jugadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idJugadores")
    private Integer idJugadores;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;
    @JoinTable(name = "Jugadores_has_Partida", joinColumns = {
        @JoinColumn(name = "Jugadores_idJugadores", referencedColumnName = "idJugadores")}, inverseJoinColumns = {
        @JoinColumn(name = "Partida_idPartida", referencedColumnName = "idPartida")})
    @ManyToMany
    private Collection<Partida> partidaCollection;

    public Jugadores() {
    }

    public Jugadores(Integer idJugadores) {
        this.idJugadores = idJugadores;
    }

    public Jugadores(Integer idJugadores, String usuario) {
        this.idJugadores = idJugadores;
        this.usuario = usuario;
    }

    public Integer getIdJugadores() {
        return idJugadores;
    }

    public void setIdJugadores(Integer idJugadores) {
        this.idJugadores = idJugadores;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public Collection<Partida> getPartidaCollection() {
        return partidaCollection;
    }

    public void setPartidaCollection(Collection<Partida> partidaCollection) {
        this.partidaCollection = partidaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJugadores != null ? idJugadores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadores)) {
            return false;
        }
        Jugadores other = (Jugadores) object;
        if ((this.idJugadores == null && other.idJugadores != null) || (this.idJugadores != null && !this.idJugadores.equals(other.idJugadores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Jugadores[ idJugadores=" + idJugadores + " ]";
    }
    
}
