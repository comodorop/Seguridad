/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Modulos.DAO.DAOModulos;
import Modulos.Dominio.Modulo;
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
public class MbModulos implements Serializable {
    
    private ArrayList<SelectItem> lstModulos;
    private Modulo modulo = new Modulo();
    private Modulo cmbModulo = new Modulo();
    
    public MbModulos() {
    }
    
    public ArrayList<SelectItem> getLstModulos() {
        if (lstModulos == null) {
            lstModulos = contruirComboModulos();
        }
        return lstModulos;
    }
    
    public ArrayList<SelectItem> contruirComboModulos() {
        ArrayList<SelectItem> lst = new ArrayList<>();
        DAOModulos dao = new DAOModulos();
        Modulo modulo = new Modulo();
        modulo.setModulo("Seleccione un Módulo");
        modulo.setIdModulo(0);
        lst.add(new SelectItem(modulo, modulo.getModulo()));
        try {
            for (Modulo m : dao.dameModulos()) {
                lst.add(new SelectItem(m, m.getModulo()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MbModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    
    public void setLstModulos(ArrayList<SelectItem> lstModulos) {
        this.lstModulos = lstModulos;
    }
    
    public Modulo getModulo() {
        return modulo;
    }
    
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    public boolean validar() {
        boolean ok = true;
        if (modulo.getModulo().equals("")) {
            ok = false;
            Mensajes.Mensajes.MensajeAlertP("Se requiere el Módulo");
        } else if (modulo.getUrl().equals("")) {
            ok = false;
            Mensajes.Mensajes.MensajeAlertP("Se requiere la Url");
        } else if (modulo.getIdMenu() == 0) {
            ok = false;
            Mensajes.Mensajes.MensajeAlertP("Se requiere un Menu");
        }
        return ok;
    }

    public Modulo getCmbModulo() {
        return cmbModulo;
    }

    public void setCmbModulo(Modulo cmbModulo) {
        this.cmbModulo = cmbModulo;
    }
    
    
}
