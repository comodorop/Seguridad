/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.dominio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class UsuarioSesion {

    private String password;
    private String jndi;
//    private String usuario;

    /**
     * Creates a new instance of UsuarioSession
     */
    public UsuarioSesion() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }
//
//    public String getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(String usuario) {
//        this.usuario = usuario;
//    }
}
