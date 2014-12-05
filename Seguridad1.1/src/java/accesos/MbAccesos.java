/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesos;

import accesos.DAOAccesos.DAOAccesos;
import accesos.Dominio.Accesos;
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
public class MbAccesos implements Serializable {

    /**
     * Creates a new instance of MbAccesos
     */
    public MbAccesos() {
    }
    private ArrayList<Accesos> lstAccesos;
    private ArrayList<Accesos> fitlroAccesos;
    private Accesos seleccion = null;

    public ArrayList<Accesos> getLstAccesos() {
        return lstAccesos;
    }

    public void dameUsuarios(int idPerfil) {
        if (lstAccesos == null) {
            lstAccesos = new ArrayList<>();
            DAOAccesos dao = new DAOAccesos();
            try {
                lstAccesos = dao.listaAccesos(idPerfil);
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbAccesos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void asignarUsuarioPerfil(int idUsuario, int idPerfil, int idBd) {
        try {
            boolean ok = validarAccesos(idUsuario, idPerfil);
            if (ok == true) {
                DAOAccesos dao = new DAOAccesos();
                dao.asignarPerfilUsuario(idUsuario, idPerfil, idBd);
                Mensajes.Mensajes.MensajeSuccesP("Exito, perfil asignado satisfactoriamente");
            }
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbAccesos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarAccesos(int idUsuario, int idPerfil) {
        boolean ok = false;
        if (idPerfil == 0) {
            Mensajes.Mensajes.MensajeAlertP("Seleccione un Perfil");
        } else if (idUsuario == 0) {
            Mensajes.Mensajes.MensajeAlertP("Seleccione un Usuarios");
        } else {
            ok = true;
        }
        return ok;
    }

    public void setLstAccesos(ArrayList<Accesos> lstAccesos) {
        this.lstAccesos = lstAccesos;
    }

    public ArrayList<Accesos> getFitlroAccesos() {
        return fitlroAccesos;
    }

    public void setFitlroAccesos(ArrayList<Accesos> fitlroAccesos) {
        this.fitlroAccesos = fitlroAccesos;
    }

    public Accesos getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Accesos seleccion) {
        this.seleccion = seleccion;
    }
}