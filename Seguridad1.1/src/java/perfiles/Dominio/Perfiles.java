/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perfiles.Dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class Perfiles implements Serializable{

    private int idPerfil;
    private String perfil;

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idPerfil;
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
        final Perfiles other = (Perfiles) obj;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return perfil;
    }
}
