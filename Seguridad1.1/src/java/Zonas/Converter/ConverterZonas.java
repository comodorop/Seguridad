/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Zonas.Converter;

import Zonas.DAOZonas.DAOZonas;
import Zonas.Dominio.Zonas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author PJGT
 */
public class ConverterZonas implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Zonas zona = new Zonas();
        int id = Integer.parseInt(value);
        zona.setIdZona(id);
        return zona;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Zonas zona = (Zonas) value;
        String id = Integer.toString(zona.getIdZona());
        return id;
    }
}
