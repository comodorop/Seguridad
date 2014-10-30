/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenus.Dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class SubMenu implements Serializable{

    private int idSubMenu;
    private String subMenu;
    private int idMenu;

    public int getIdSubMenu() {
        return idSubMenu;
    }

    public void setIdSubMenu(int idSubMenu) {
        this.idSubMenu = idSubMenu;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idSubMenu;
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
        final SubMenu other = (SubMenu) obj;
        if (this.idSubMenu != other.idSubMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  subMenu ;
    }
    
    
    
    
    
    
}
