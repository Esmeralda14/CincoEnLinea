/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marianacro
 */
@Entity
@Table(name = "Jugadores_has_Partida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JugadoreshasPartida.findAll", query = "SELECT j FROM JugadoreshasPartida j")
    , @NamedQuery(name = "JugadoreshasPartida.findByJugadoresusuario", query = "SELECT j FROM JugadoreshasPartida j WHERE j.jugadoreshasPartidaPK.jugadoresusuario = :jugadoresusuario")
    , @NamedQuery(name = "JugadoreshasPartida.findByPartidaidPartida", query = "SELECT j FROM JugadoreshasPartida j WHERE j.jugadoreshasPartidaPK.partidaidPartida = :partidaidPartida")
    , @NamedQuery(name = "JugadoreshasPartida.findByPuntuacionObtenida", query = "SELECT j FROM JugadoreshasPartida j WHERE j.puntuacionObtenida = :puntuacionObtenida")})
public class JugadoreshasPartida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JugadoreshasPartidaPK jugadoreshasPartidaPK;
    @Basic(optional = false)
    @Column(name = "puntuacionObtenida")
    private int puntuacionObtenida;
    @JoinColumn(name = "Jugadores_usuario", referencedColumnName = "usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jugadores jugadores;
    @JoinColumn(name = "Partida_idPartida", referencedColumnName = "idPartida", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partida partida;

    public JugadoreshasPartida() {
    }

    public JugadoreshasPartida(JugadoreshasPartidaPK jugadoreshasPartidaPK) {
        this.jugadoreshasPartidaPK = jugadoreshasPartidaPK;
    }

    public JugadoreshasPartida(JugadoreshasPartidaPK jugadoreshasPartidaPK, int puntuacionObtenida) {
        this.jugadoreshasPartidaPK = jugadoreshasPartidaPK;
        this.puntuacionObtenida = puntuacionObtenida;
    }

    public JugadoreshasPartida(String jugadoresusuario, int partidaidPartida) {
        this.jugadoreshasPartidaPK = new JugadoreshasPartidaPK(jugadoresusuario, partidaidPartida);
    }

    public JugadoreshasPartidaPK getJugadoreshasPartidaPK() {
        return jugadoreshasPartidaPK;
    }

    public void setJugadoreshasPartidaPK(JugadoreshasPartidaPK jugadoreshasPartidaPK) {
        this.jugadoreshasPartidaPK = jugadoreshasPartidaPK;
    }

    public int getPuntuacionObtenida() {
        return puntuacionObtenida;
    }

    public void setPuntuacionObtenida(int puntuacionObtenida) {
        this.puntuacionObtenida = puntuacionObtenida;
    }

    public Jugadores getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugadores jugadores) {
        this.jugadores = jugadores;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jugadoreshasPartidaPK != null ? jugadoreshasPartidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JugadoreshasPartida)) {
            return false;
        }
        JugadoreshasPartida other = (JugadoreshasPartida) object;
        if ((this.jugadoreshasPartidaPK == null && other.jugadoreshasPartidaPK != null) || (this.jugadoreshasPartidaPK != null && !this.jugadoreshasPartidaPK.equals(other.jugadoreshasPartidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.JugadoreshasPartida[ jugadoreshasPartidaPK=" + jugadoreshasPartidaPK + " ]";
    }
    
}
