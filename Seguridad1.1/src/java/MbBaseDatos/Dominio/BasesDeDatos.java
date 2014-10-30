/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbBaseDatos.Dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class BasesDeDatos implements Serializable{

    private int idBaseDatos;
    private String baseDatos;
    private String jndi;

    public BasesDeDatos() {
    }

    
    
    
    
    
    public int getIdBaseDatos() {
        return idBaseDatos;
    }

    public void setIdBaseDatos(int idBaseDatos) {
        this.idBaseDatos = idBaseDatos;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idBaseDatos;
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
        final BasesDeDatos other = (BasesDeDatos) obj;
        if (this.idBaseDatos != other.idBaseDatos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return baseDatos;
    }
}
