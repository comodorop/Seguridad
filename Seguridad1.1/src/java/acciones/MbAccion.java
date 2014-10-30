/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import acciones.DAO.DAOAcciones;
import acciones.dominio.Acciones;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbAccion implements Serializable{

    /**
     * Creates a new instance of MbAccion
     */
    private ArrayList<Acciones> listaAcciones = new ArrayList<>();
    private Acciones acciones = new Acciones();
    private Acciones selecctionAccion;
    public boolean actualizar = false;

    public MbAccion() {
        acciones = new Acciones();
    }

    /**
     * Creates a new instance of MbAcciones
     */
    public ArrayList<Acciones> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<Acciones> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public boolean validarAccionesWizard() {
        boolean ok = false;
        if (listaAcciones.isEmpty()) {
            Mensajes.Mensajes.MensajeAlertP("Se requieren acciones para finalizar");
        }
        return ok;
    }

    public boolean validar() {
        boolean ok = true;
        if (acciones.getAccion().equals("") || acciones.getAccion() == null) {
            ok = false;
            Mensajes.Mensajes.MensajeAlertP("Se requeire la accions");
        } else if (acciones.getId().equals("") || acciones.getId() == null) {
            ok = false;
            Mensajes.Mensajes.MensajeAlertP("Se requiere el id de la Accion");
        }
        return ok;
    }

    public void cargarAcciones(int idModulo) {
        DAOAcciones dao = new DAOAcciones();
        try {
            listaAcciones = dao.dameAcciones(idModulo);
        } catch (SQLException ex) {
            Logger.getLogger(MbAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarAccion(int idModulo) {
        boolean ok = validar();
        if (ok == true) {
            try {
                DAOAcciones dao = new DAOAcciones();
                acciones.setIdModulo(idModulo);
                if (actualizar == false) {
                    dao.guardarAcciones(acciones);
                    Mensajes.Mensajes.MensajeSuccesP("Nueva Accion disponible");
                } else {
                    dao.actalizarAcciones(acciones);
                    Mensajes.Mensajes.MensajeSuccesP("Accion actualizada correctamente");
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbAccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cancelar() {
        actualizar = false;
        acciones = new Acciones();
        selecctionAccion = null;

    }

    public Acciones getAcciones() {
        return acciones;
    }

    public void setAcciones(Acciones acciones) {
        this.acciones = acciones;
    }

    public Acciones getSelecctionAccion() {
        return selecctionAccion;
    }

    public void setSelecctionAccion(Acciones selecctionAccion) {
        this.selecctionAccion = selecctionAccion;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
}
