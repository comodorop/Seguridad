/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import login.DAO.DAOLogin;
import login.dominio.Login;
import login.dominio.UsuarioSesion;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbLogin implements Serializable {

    @ManagedProperty(value = "#{usuarioSesion}")
    private UsuarioSesion usuarioSesion;
    private Login login = new Login();
//    private boolean usuario = false;

    /**
     * Creates a new instance of MbLogin
     */
    public MbLogin() {
//        usuario = validarUsuario();
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
            try {
                Login log = dao.validarAcceso(login);
                if (log.getPassword() != "") {
                    usuarioSesion.setPassword(log.getPassword());
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("usuario", this.login.getUsuario());
                    url = "index.xhtml";
                }
                else{
                    Mensajes.Mensajes.MensajeErrorP("Credencial no valida");
                }
            } catch (SQLException ex) {
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

//    public boolean isUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(boolean usuario) {
//        this.usuario = usuario;
//    }
}
