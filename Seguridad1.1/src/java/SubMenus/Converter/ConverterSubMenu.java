/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenus.Converter;

import SubMenus.DAO.DAOSubMenu;
import SubMenus.Dominio.SubMenu;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author PJGT
 */
public class ConverterSubMenu implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int id = Integer.parseInt(value);
        SubMenu sub = null;
        if (id == 0) {
            sub = new SubMenu();
        } else {
            try {
                DAOSubMenu dao = new DAOSubMenu();
                sub = dao.dameSubMenu(id);
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(ConverterSubMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sub;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        SubMenu sub = (SubMenu) value;

        return Integer.toString(sub.getIdSubMenu());

    }
}
