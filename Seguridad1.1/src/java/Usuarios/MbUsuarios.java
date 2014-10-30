/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Usuarios.DAO.DAOUsuarios;
import Usuarios.Dominio.Usuarios;
import Zonas.DAOZonas.DAOZonas;
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
public class MbUsuarios implements Serializable {

    Usuarios usuario = new Usuarios();
    Usuarios cmbUsuario = new Usuarios();
    Usuarios selccionUsuairo = null;
    private ArrayList<Usuarios> lstUsuarios = null;
    private ArrayList<Usuarios> filtroUsuario = null;
    private ArrayList<SelectItem> lstCmbUsuarios = null;

    public MbUsuarios() {
    }

    public boolean validar() {
        boolean ok = false;
        if (usuario.getUsuario().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un Nombre");
            ok = false;
        } else if (usuario.getLogin().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un login");
            ok = false;
        } else {
            ok = true;
        }
        return ok;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Usuarios> getLstUsuarios() {
        if (lstUsuarios == null) {
            lstUsuarios = new ArrayList<>();
            DAOUsuarios dao = new DAOUsuarios();
            try {
                lstUsuarios = dao.dameUsuarios();
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<Usuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public ArrayList<Usuarios> getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(ArrayList<Usuarios> filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }

    public Usuarios getSelccionUsuairo() {
        return selccionUsuairo;
    }

    public void setSelccionUsuairo(Usuarios selccionUsuairo) {
        this.selccionUsuairo = selccionUsuairo;
    }

    public ArrayList<SelectItem> getLstCmbUsuarios() {
        if (lstCmbUsuarios == null) {
            lstCmbUsuarios = new ArrayList<>();
            DAOUsuarios dao = new DAOUsuarios();
            try {
                Usuarios usua = new Usuarios();
                usua.setIdUsuario(0);
                usua.setUsuario("Seleccione un usuario");
                lstCmbUsuarios.add(new SelectItem(usua, usua.getUsuario()));
                for (Usuarios usuarios : dao.dameUsuariosNoAsignados()) {
                    lstCmbUsuarios.add(new SelectItem(usuarios, usuarios.getUsuario()));
                }
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstCmbUsuarios;
    }

    public void setLstCmbUsuarios(ArrayList<SelectItem> lstCmbUsuarios) {
        this.lstCmbUsuarios = lstCmbUsuarios;
 
    }

    public Usuarios getCmbUsuario() {
        return cmbUsuario;
    }

    public void setCmbUsuario(Usuarios cmbUsuario) {
        this.cmbUsuario = cmbUsuario;
    }
    
    
    
    
}
