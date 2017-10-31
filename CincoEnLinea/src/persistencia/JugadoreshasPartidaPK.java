/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marianacro
 */
@Embeddable
public class JugadoreshasPartidaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Jugadores_usuario")
    private String jugadoresusuario;
    @Basic(optional = false)
    @Column(name = "Partida_idPartida")
    private int partidaidPartida;

    public JugadoreshasPartidaPK() {
    }

    public JugadoreshasPartidaPK(String jugadoresusuario, int partidaidPartida) {
        this.jugadoresusuario = jugadoresusuario;
        this.partidaidPartida = partidaidPartida;
    }

    public String getJugadoresusuario() {
        return jugadoresusuario;
    }

    public void setJugadoresusuario(String jugadoresusuario) {
        this.jugadoresusuario = jugadoresusuario;
    }

    public int getPartidaidPartida() {
        return partidaidPartida;
    }

    public void setPartidaidPartida(int partidaidPartida) {
        this.partidaidPartida = partidaidPartida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jugadoresusuario != null ? jugadoresusuario.hashCode() : 0);
        hash += (int) partidaidPartida;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JugadoreshasPartidaPK)) {
            return false;
        }
        JugadoreshasPartidaPK other = (JugadoreshasPartidaPK) object;
        if ((this.jugadoresusuario == null && other.jugadoresusuario != null) || (this.jugadoresusuario != null && !this.jugadoresusuario.equals(other.jugadoresusuario))) {
            return false;
        }
        if (this.partidaidPartida != other.partidaidPartida) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.JugadoreshasPartidaPK[ jugadoresusuario=" + jugadoresusuario + ", partidaidPartida=" + partidaidPartida + " ]";
    }
    
}
