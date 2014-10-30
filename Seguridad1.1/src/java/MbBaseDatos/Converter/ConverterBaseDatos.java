/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbBaseDatos.Converter;

import MbBaseDatos.DAO.DAOBaseDatos;
import MbBaseDatos.Dominio.BasesDeDatos;
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
public class ConverterBaseDatos implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BasesDeDatos b = null;
        int x = Integer.parseInt(value);
        if (x == 0) {
            b = new BasesDeDatos();
        } else {
            try {
                DAOBaseDatos dao = new DAOBaseDatos();
                b = dao.dameBaseDatos(x);
            } catch (SQLException ex) {
                Mensajes.Mensajes.mensajeErrorG(ex.getMessage());
                Logger.getLogger(ConverterBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return b;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BasesDeDatos base = (BasesDeDatos) value;
        return Integer.toString(base.getIdBaseDatos());
    }
}
