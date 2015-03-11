/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.dominio;

import java.io.Serializable;

/**
 *
 * @author PJGT
 */
public class Correos implements Serializable{
   private int idCorreo;
   private String servidor;
   private String correo;
   private String password;
   private int puerto;
   private String protocolo;

    public int getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(int idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
   
   
   
   
}
