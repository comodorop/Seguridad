/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cedis;

import Cedis.DAOCedis.DAOCedis;
import Cedis.Dominio.Cedis;
import Zonas.MbZonas;
import acciones.MbAccion;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbCedis implements Serializable{

    private ArrayList<SelectItem> lstCedis = null;
    @ManagedProperty(value = "#{mbZonas}")
    private MbZonas mbZonas = new MbZonas();
    private Cedis cmbCedis = new Cedis();

    /**
     * Creates a new instance of MbCedis
     */
    public MbCedis() {
        cmbCedis = new Cedis();
    }

    public void cargarCedis(String jndi) {
        lstCedis = new ArrayList<>();
        DAOCedis dao = new DAOCedis(jndi);
        Cedis cedis = new Cedis();
        cedis.setIdCedis(0);
        cedis.setCedis("Seleccione un Cedis");
        lstCedis.add(new SelectItem(cedis, cedis.getCedis()));
        try {
            for (Cedis ced : dao.dameCedis()) {
                lstCedis.add(new SelectItem(ced, ced.getCedis()));
            }
        } catch (SQLException ex) {
            Mensajes.Mensajes.mensajeErrorG(ex.getMessage());
            Logger.getLogger(MbCedis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validar() {
        boolean ok = false;
        if (cmbCedis.getIdCedis() == 0) {
            Mensajes.Mensajes.MensajeAlertP("Seleccione un Cedis");
        } else if (mbZonas.getCmbZonas().getIdZona() == 0) {
            Mensajes.Mensajes.MensajeAlertP("Seleccione una zona");
        }
        else{
            ok = true;
        }
        return ok ;
    }

    public ArrayList<SelectItem> getLstCedis() {
        return lstCedis;
    }

    public void setLstCedis(ArrayList<SelectItem> lstCedis) {
        this.lstCedis = lstCedis;
    }

    public MbZonas getMbZonas() {
        return mbZonas;
    }

    public void setMbZonas(MbZonas mbZonas) {
        this.mbZonas = mbZonas;
    }

    public Cedis getCmbCedis() {
        return cmbCedis;
    }

    public void setCmbCedis(Cedis cmbCedis) {
        this.cmbCedis = cmbCedis;
    }
}
