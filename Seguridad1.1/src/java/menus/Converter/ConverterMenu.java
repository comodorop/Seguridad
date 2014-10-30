/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.Converter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import menus.DAO.DAOMenus;
import menus.Dominio.Menu;
import menus.MbMenus;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DualListModel;

/**
 *
 * @author PJGT
 */
public class ConverterMenu implements Converter {


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Menu menu = new Menu();
        int id = Integer.parseInt(value);
        if (id == 0) {
            menu = new Menu();
        } else {
            DAOMenus dao = new DAOMenus();
            try {
                menu = dao.dameMenu(id);
            } catch (SQLException ex) {
                Logger.getLogger(ConverterMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return menu;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Menu m = (Menu) value;
        return Integer.toString(m.getIdMenu());//        return Integer.toString(m.getIdMenu());
    }
}
