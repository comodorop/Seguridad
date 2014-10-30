/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Zonas;

import Zonas.DAOZonas.DAOZonas;
import Zonas.Dominio.Zonas;
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
public class MbZonas implements Serializable{

    /**
     * Creates a new instance of MbZonas
     */
    private ArrayList<SelectItem> lstZonas;
    private Zonas cmbZonas = new Zonas();

    public MbZonas() {
        cmbZonas = new Zonas();
    }

    public void cargarZonas(int idCedis, String jndi) {
        if (lstZonas == null) {
            lstZonas = new ArrayList<>();
            DAOZonas dao = new DAOZonas(jndi);
            Zonas zona = new Zonas();
            zona.setIdZona(0);
            zona.setZona("Seleccione una Zona");
            lstZonas.add(new SelectItem(zona,zona.getZona()));
            try {
                for (Zonas z : dao.dameZonas(idCedis)) {
                    lstZonas.add(new SelectItem(z,z.getZona()));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MbZonas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public ArrayList<SelectItem> getLstZonas() {

        return lstZonas;
    }

    public void setLstZonas(ArrayList<SelectItem> lstZonas) {
        this.lstZonas = lstZonas;
    }

    public Zonas getCmbZonas() {
        return cmbZonas;
    }

    public void setCmbZonas(Zonas cmbZonas) {
        this.cmbZonas = cmbZonas;
    }
    
    
    
    
}
