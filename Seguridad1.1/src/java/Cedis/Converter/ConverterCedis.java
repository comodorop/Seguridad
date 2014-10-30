/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cedis.Converter;

import Cedis.DAOCedis.DAOCedis;
import Cedis.Dominio.Cedis;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author PJGT
 */
public class ConverterCedis implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cedis cedis;
        int id = Integer.parseInt(value);
        if (id == 0) {
            cedis = new Cedis();
        } else {
            cedis = new Cedis();
            cedis.setIdCedis(id);
        }
        return cedis;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cedis cedis = (Cedis) value;
        return Integer.toString(cedis.getIdCedis());
    }
}
