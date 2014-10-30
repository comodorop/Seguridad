/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos.Converter;

import Modulos.DAO.DAOModulos;
import Modulos.Dominio.Modulo;
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
public class ConverterModulo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Modulo modulo = null;
        int id = Integer.parseInt(value);
        if (id == 0) {
            modulo = new Modulo();
        } else {
            try {
                DAOModulos dao = new DAOModulos();
                modulo = dao.dameModulo(id);
            } catch (SQLException ex) {
                Logger.getLogger(ConverterModulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return modulo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Modulo modulo = (Modulo) value;
        return Integer.toString(modulo.getIdModulo());
    }
}
