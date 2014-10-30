/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import SubMenus.MbSubMenu;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import menus.DAO.DAOMenus;
import menus.Dominio.Menu;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbMenus implements Serializable {

    /**
     * Creates a new instance of MbMenus
     */
    private ArrayList<SelectItem> lstMenu;
    private Menu cmbMenu = new Menu();
    @ManagedProperty(value = "#{mbSubMenu}")
    private MbSubMenu mbSubMenu = new MbSubMenu();

    public MbMenus() {
    }

    public ArrayList<SelectItem> getLstMenu() {
        if (lstMenu == null) {
            lstMenu = contruirCmbMenus();
        }
        return lstMenu;
    }

    public ArrayList<SelectItem> contruirCmbMenus() {
        ArrayList<SelectItem> listaMenus = new ArrayList<>();
        DAOMenus dao = new DAOMenus();
        try {
            Menu menu = new Menu();
            menu.setIdMenu(0);
            menu.setMenu("Seleccione un Menu");
            listaMenus.add(new SelectItem(menu, menu.getMenu()));
            for (Menu m : dao.dameMenus()) {
                listaMenus.add(new SelectItem(m, m.getMenu()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MbMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMenus;
    }

    public void setLstMenu(ArrayList<SelectItem> lstMenu) {
        this.lstMenu = lstMenu;
    }

    public Menu getCmbMenu() {
        return cmbMenu;
    }

    public void setCmbMenu(Menu cmbMenu) {
        this.cmbMenu = cmbMenu;
    }

    public MbSubMenu getMbSubMenu() {
        return mbSubMenu;
    }

    public void setMbSubMenu(MbSubMenu mbSubMenu) {
        this.mbSubMenu = mbSubMenu;
    }
}
