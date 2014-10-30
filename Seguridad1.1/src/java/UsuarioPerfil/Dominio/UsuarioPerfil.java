/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioPerfil.Dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class UsuarioPerfil implements Serializable{
    private int idPerfil;
    private int idModulo;
    private int idAccion;

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioPerfil other = (UsuarioPerfil) obj;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        return true;
    }
    
    
    
}
