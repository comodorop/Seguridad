/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbBaseDatos;

import MbBaseDatos.DAO.DAOBaseDatos;
import MbBaseDatos.Dominio.BasesDeDatos;
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
public class MbBasesDatos implements Serializable{

    /**
     * Creates a new instance of MbBasesDatos
     */
    private ArrayList<SelectItem> lst = null;
    private BasesDeDatos cmbBase = new BasesDeDatos();

    public MbBasesDatos() {
    }

    public ArrayList<SelectItem> getLst() {
        if (lst == null) {
            lst = new ArrayList<>();
            DAOBaseDatos dao = new DAOBaseDatos();
            BasesDeDatos bases = new BasesDeDatos();
            bases.setIdBaseDatos(0);
            bases.setBaseDatos("Seleccione una Base de datos");
            lst.add(new SelectItem(bases, bases.getBaseDatos()));
            try {
                for (BasesDeDatos b : dao.dameBaseDatos()) {
                    lst.add(new SelectItem(b, b.getBaseDatos()));
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

    public void setLst(ArrayList<SelectItem> lst) {
        this.lst = lst;
    }

    public BasesDeDatos getCmbBase() {
        return cmbBase;
    }

    public void setCmbBase(BasesDeDatos cmbBase) {
        this.cmbBase = cmbBase;
    }
    
    
    
    
    
    
}
