/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import login.DAO.DAOLogin;
import login.dominio.Login;
import login.dominio.UsuarioSesion;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbLogin {

    @ManagedProperty(value = "#{usuarioSesion}")
    private UsuarioSesion usuarioSesion = new UsuarioSesion();
    private Login login = new Login();
    private boolean usuario = false;

    /**
     * Creates a new instance of MbLogin
     */
    public MbLogin() {
        usuario = validarUsuario();
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String accesoSistema() {
        String url = "";
        boolean ok = validar();
        DAOLogin dao = new DAOLogin();
        if (ok) {
            if (usuario == true) {
                try {
                    Login log = dao.validarAcceso(login);
                    if (log.getPassword().equals("")) {
                        url = "login.xhtml";
                        Mensajes.Mensajes.MensajeAlertP("Acceso denegado");
                    } else {
                        usuarioSesion.setPassword(log.getPassword());
                        url = "index.xhtml";
                    }
                } catch (SQLException ex) {
                    Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                    Logger.getLogger(MbLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    dao.guardarNuevoUsuario(login);
                    usuarioSesion.setPassword(login.getPassword());
                } catch (SQLException ex) {
                    Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                    Logger.getLogger(MbLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                    Logger.getLogger(MbLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "index.xhtml";
            }
        }

        return url;
    }

    public boolean validar() {
        boolean ok = false;
        if (login.getPassword().equals("") || login.getPassword() == null) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere una contraseña");
        } else {
            ok = true;
        }
        return ok;
    }

    public boolean validarUsuario() {
        boolean ok = false;
        DAOLogin dao = new DAOLogin();
        try {
            ok = dao.verificarUsuarioDisponible();
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }

    public UsuarioSesion getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(UsuarioSesion usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }
}
