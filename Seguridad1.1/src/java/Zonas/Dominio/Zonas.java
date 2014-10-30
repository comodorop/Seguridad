/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Zonas.Dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class Zonas implements Serializable{
    
    private int idZona ;
    private int idCedis;
    private String zona;

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdCedis() {
        return idCedis;
    }

    public void setIdCedis(int idCedis) {
        this.idCedis = idCedis;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idZona;
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
        final Zonas other = (Zonas) obj;
        if (this.idZona != other.idZona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  zona ;
    }
   
    
    
    
}
