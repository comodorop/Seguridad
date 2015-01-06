/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.dominio;

import java.io.Serializable;

/**
 *
 * @author Comodoro
 */
public class Login implements Serializable {

    private String password;
    private String jndi;
    private String usuario;

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
