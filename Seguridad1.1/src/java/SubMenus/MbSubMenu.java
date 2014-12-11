/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenus;

import SubMenus.DAO.DAOSubMenu;
import SubMenus.Dominio.SubMenu;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbSubMenu implements Serializable {

    private ArrayList<SelectItem> lstSubmenus = null;
    private SubMenu cmbSubMenu = new SubMenu();
    private SubMenu subMenu = new SubMenu();

    /**
     * Creates a new instance of MbSubMenu
     */
    public MbSubMenu() {
    }

    public ArrayList<SelectItem> getLstSubmenus() {

        return lstSubmenus;
    }

    public void construirSubMenus(int idMenu) {
        if (lstSubmenus == null) {
            try {
                lstSubmenus = new ArrayList<>();
                DAOSubMenu dao = new DAOSubMenu();
                SubMenu sub = new SubMenu();
                sub.setIdMenu(0);
                sub.setIdSubMenu(0);
                sub.setSubMenu("Seleccione un SubMenu");
                lstSubmenus.add(new SelectItem(sub, sub.getSubMenu()));
                for (SubMenu subMenu : dao.dameSubMenus(idMenu)) {
                    lstSubmenus.add(new SelectItem(subMenu, subMenu.getSubMenu()));
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbSubMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guardar(int idMenu) {
        boolean ok = validar();
        if (ok == true) {
            DAOSubMenu dao = new DAOSubMenu();
            subMenu.setIdMenu(idMenu);
            if (subMenu.getIdSubMenu() == 0) {
                try {
                    dao.guardarSubMenu(subMenu);
                    Mensajes.Mensajes.MensajeSuccesP("Nuevo sub menu disponible");
                } catch (SQLException ex) {
                    Mensajes.Mensajes.MensajeSuccesP(ex.getMessage());
                    Logger.getLogger(MbSubMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    dao.actualizarSubMenu(subMenu);
                } catch (SQLException ex) {
                    Mensajes.Mensajes.MensajeSuccesP(ex.getMessage());
                    Logger.getLogger(MbSubMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                Mensajes.Mensajes.MensajeSuccesP("Sub menu actualizado correctamente");
            }
        }
    }

    public boolean validar() {
        boolean ok = false;
        if (subMenu.getSubMenu().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere el nombre del sub menu");
        } else {
            ok = true;
        }
        return ok;
    }

    public void setLstSubmenus(ArrayList<SelectItem> lstSubmenus) {
        this.lstSubmenus = lstSubmenus;
    }

    public SubMenu getCmbSubMenu() {
        return cmbSubMenu;
    }

    public void setCmbSubMenu(SubMenu cmbSubMenu) {
        this.cmbSubMenu = cmbSubMenu;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }
}
