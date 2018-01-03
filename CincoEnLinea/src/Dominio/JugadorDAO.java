/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Esmeralda
 */
public class JugadorDAO {
    String usuario;
    String clave;
    int puntuacionTotal;

    public JugadorDAO(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
        public JugadorDAO(String usuario,int puntuacionTotal) {
        this.usuario = usuario;
        this.puntuacionTotal = puntuacionTotal;
    }
    
    public JugadorDAO(){}

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

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }
    
    
}
