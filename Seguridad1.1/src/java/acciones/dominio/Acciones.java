/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class Acciones implements Serializable{

    private int idAccion;
    private String accion;
    private String id;
    private int idModulo;

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    @Override
    public String toString() {
        return accion;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idAccion;
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
        final Acciones other = (Acciones) obj;
        if (this.idAccion != other.idAccion) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
}
