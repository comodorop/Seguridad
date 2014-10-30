/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios.Converter;

import Usuarios.DAO.DAOUsuarios;
import Usuarios.Dominio.Usuarios;
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
public class ConverterUsuarios implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuarios usuarios = null;
        int id = Integer.parseInt(value);
        if (id == 0) {
            usuarios = new Usuarios();
        } else {
            try {
                DAOUsuarios dao = new DAOUsuarios();
                usuarios = dao.dameUsuarios(id);
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeAlertP(ex.getMessage());
                Logger.getLogger(ConverterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Usuarios usuarios = (Usuarios) value;
        return Integer.toString(usuarios.getIdUsuario());
    }
}
