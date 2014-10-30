/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perfiles.Converter;

//import java.io.Serializable;
//import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.xml.bind.ParseConversionEvent;
import perfiles.DAO.DAOPerfiles;
import perfiles.Dominio.Perfiles;

/**
 *
 * @author PJGT
 */
public class ConverterPerfil implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Perfiles perfiles = null;
        int idPerfil = Integer.parseInt(value);
        if (idPerfil == 0) {
            perfiles = new Perfiles();
        } else {
            DAOPerfiles dao = new DAOPerfiles();
            try {
                perfiles = dao.damePerfil(idPerfil);
            } catch (SQLException ex) {
                Logger.getLogger(ConverterPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return perfiles;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Perfiles perfiles = (Perfiles) value;
        return Integer.toString(perfiles.getIdPerfil());
    }
}
