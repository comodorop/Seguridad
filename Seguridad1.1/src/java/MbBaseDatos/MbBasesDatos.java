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
public class MbBasesDatos implements Serializable {

    /**
     * Creates a new instance of MbBasesDatos
     */
    private ArrayList<SelectItem> lst = null;
    private BasesDeDatos cmbBase = new BasesDeDatos();
    private ArrayList<BasesDeDatos> lstDisponibles;
    private ArrayList<BasesDeDatos> lstElegidas;
    private BasesDeDatos seleccionBaseDisponibles;
    private BasesDeDatos seleccionBaseElegidas;

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

    public void desmarcarOrigen() {
        seleccionBaseDisponibles = null;
    }

    public void desmarcarDestino() {
        seleccionBaseElegidas = null;
    }

    public ArrayList<BasesDeDatos> getLstDisponibles() {
        if (lstDisponibles == null && lstElegidas == null) {
            DAOBaseDatos dao = new DAOBaseDatos();
            try {
                lstElegidas = dao.dameBaseDatos();
                lstDisponibles = dao.dameListaBds();
                for (int x = 0; x < lstDisponibles.size(); x++) {
                    for (int y = 0; y < lstElegidas.size(); y++) {
                        if (lstDisponibles.get(x).getBaseDatos().equals(lstElegidas.get(y).getBaseDatos())) {
                            lstDisponibles.remove(x);
                            x--;
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstDisponibles;
    }

    public void guardarNuevaBaseDatos() {
        DAOBaseDatos dao = new DAOBaseDatos();
        try {
            dao.guardarNuevaBaseDatos(seleccionBaseDisponibles);
            Mensajes.Mensajes.MensajeSuccesP("Nueva base disponible");
            seleccionBaseElegidas = null;
            seleccionBaseDisponibles = null;
            lstDisponibles = null;
            lstElegidas = null;
            lst = null;
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarBaseDatos() {
        DAOBaseDatos dao = new DAOBaseDatos();
        try {
            dao.eliminarBase(seleccionBaseElegidas.getIdBaseDatos());
            Mensajes.Mensajes.MensajeSuccesP("Base de datos eliminada");
            seleccionBaseElegidas = null;
            seleccionBaseDisponibles = null;
            lstDisponibles = null;
            lstElegidas = null;
            lst = null;
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLstDisponibles(ArrayList<BasesDeDatos> lstDisponibles) {
        this.lstDisponibles = lstDisponibles;
    }

    public ArrayList<BasesDeDatos> getLstElegidas() {
        if (lstDisponibles == null && lstElegidas == null) {
            DAOBaseDatos dao = new DAOBaseDatos();
            try {
                lstElegidas = dao.dameBaseDatos();
                lstDisponibles = dao.dameListaBds();
                for (int x = 0; x < lstDisponibles.size(); x++) {
                    for (int y = 0; y < lstElegidas.size(); y++) {
                        if (lstDisponibles.get(x).getBaseDatos().equals(lstElegidas.get(y).getBaseDatos())) {
                            lstDisponibles.remove(x);
                            x--;
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstElegidas;
    }

    public void setLstElegidas(ArrayList<BasesDeDatos> lstElegidas) {
        this.lstElegidas = lstElegidas;
    }

    public BasesDeDatos getSeleccionBaseDisponibles() {
        return seleccionBaseDisponibles;
    }

    public void setSeleccionBaseDisponibles(BasesDeDatos seleccionBaseDisponibles) {
        this.seleccionBaseDisponibles = seleccionBaseDisponibles;
    }

    public BasesDeDatos getSeleccionBaseElegidas() {
        return seleccionBaseElegidas;
    }

    public void setSeleccionBaseElegidas(BasesDeDatos seleccionBaseElegidas) {
        this.seleccionBaseElegidas = seleccionBaseElegidas;
    }
}
