/**
 * Nombre del proyecto:
 *    5 en linea.
 *
 * Nombres de los desarrolladores:
 *    Mariana Cadena Romero
 *    Esmeralda Jimenez Ramos
 *
 * Fecha en la que se inició el programa:
 *    28-noviembre-2017
 *
 * Descripción: Juego que lleva por nombre '5 en linea' el cual esta disponible
 * para todo publico, tiene la capacidad de soportar multijugador de dos
 * participantes en tiempo real y de realizar registro de nuevos usuarios,
 * así como consultar la puntuacion de todos los jugadores.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase encargada de realizar las consultas a la base de datos
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
@Entity
@Table(name = "Jugadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugadores.findAll", query = "SELECT j FROM Jugadores j")
    ,
    @NamedQuery(name = "Jugadores.findByUsuario", query = "SELECT j FROM Jugadores j WHERE j.usuario = :usuario")
    ,
    @NamedQuery(name = "Jugadores.findByClave", query = "SELECT j FROM Jugadores j WHERE j.clave = :clave")
    ,
    @NamedQuery(name = "puntaje.jugadores", query = "SELECT j.puntuacionTotal FROM Jugadores j ORDER BY j.puntuacionTotal DESC")
    ,
    @NamedQuery(name = "usuarios.puntaje", query = "SELECT j.usuario from Jugadores j ORDER BY j.puntuacionTotal DESC")
    ,
    @NamedQuery(name = "sumar.puntajeObtenido", query = "UPDATE Jugadores j SET j.puntuacionTotal = j.puntuacionTotal + 10 WHERE j.usuario = :usuario")
    ,
    @NamedQuery(name = "Jugadores.findByPuntuacionTotal", query = "select j from Jugadores j where j.puntuacionTotal IS NOT NULL order by j.puntuacionTotal desc")})

public class Jugadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Column(name = "puntuacionTotal")
    private Integer puntuacionTotal;

    public Jugadores() {
    }

    public Jugadores(String usuario) {
        this.usuario = usuario;
    }

    public Jugadores(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
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

    public Integer getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(Integer puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadores)) {
            return false;
        }
        Jugadores other = (Jugadores) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Jugadores[ usuario=" + usuario + " ]";
    }

}
