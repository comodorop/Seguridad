/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cedis.Dominio;

import Zonas.Dominio.Zonas;
import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class Cedis implements Serializable{

    private int idCedis;
    private String cedis;
    private Zonas zona = new Zonas();

    public int getIdCedis() {
        return idCedis;
    }

    public void setIdCedis(int idCedis) {
        this.idCedis = idCedis;
    }

    public String getCedis() {
        return cedis;
    }

    public void setCedis(String cedis) {
        this.cedis = cedis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idCedis;
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
        final Cedis other = (Cedis) obj;
        if (this.idCedis != other.idCedis) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cedis;
    }

    public Zonas getZona() {
        return zona;
    }

    public void setZona(Zonas zona) {
        this.zona = zona;
    }
    
    
    
}
