/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perfiles;

import accesos.MbAccesos;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import perfiles.DAO.DAOPerfiles;
import perfiles.Dominio.Perfiles;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbPerfiles implements Serializable {

    private ArrayList<SelectItem> lstPerfiles;
    private Perfiles cmbPerfil = new Perfiles();
    @ManagedProperty(value = "#{mbAccesos}")
    private MbAccesos mbAccesos = new MbAccesos();

    public MbPerfiles() {
    }

    public void cargarAccesos() {
        if (mbAccesos.getSeleccion() == null) {
            mbAccesos.setLstAccesos(null);
            mbAccesos.dameUsuarios(cmbPerfil.getIdPerfil());
        }
    }

    public ArrayList<SelectItem> getLstPerfiles() {
        if (lstPerfiles == null) {
            lstPerfiles = new ArrayList<>();
            DAOPerfiles dao = new DAOPerfiles();
            try {
                Perfiles p = new Perfiles();
                p.setIdPerfil(0);
                p.setPerfil("Nuevo Perfil");
                lstPerfiles.add(new SelectItem(p, p.getPerfil()));
                for (Perfiles perfil : dao.damePerfiles()) {
                    lstPerfiles.add(new SelectItem(perfil, perfil.getPerfil()));
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbPerfiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstPerfiles;
    }

    public void actualizarAsignacionUsuarioPerfil() {
        DAOPerfiles dao = new DAOPerfiles();
        try {
            dao.actualizarAsignacionPerfilAcceso(cmbPerfil.getIdPerfil(), mbAccesos.getSeleccion().getUsuarios().getIdUsuario());
            mbAccesos.setSeleccion(null);
            cargarAccesos();
        } catch (SQLException e) {
            Mensajes.Mensajes.MensajeErrorP(e.getMessage());
        }
    }

    public void setLstPerfiles(ArrayList<SelectItem> lstPerfiles) {
        this.lstPerfiles = lstPerfiles;
    }

    public MbAccesos getMbAccesos() {
        return mbAccesos;
    }

    public void setMbAccesos(MbAccesos mbAccesos) {
        this.mbAccesos = mbAccesos;
    }

    public Perfiles getCmbPerfil() {
        return cmbPerfil;
    }

    public void setCmbPerfil(Perfiles cmbPerfil) {
        this.cmbPerfil = cmbPerfil;
    }
}
