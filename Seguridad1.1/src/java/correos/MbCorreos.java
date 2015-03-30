/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package correos;

import correos.dao.DAOCorreos;
import correos.dominio.Correos;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbCorreos implements Serializable {

    private Correos correo = new Correos();

    /**
     * Creates a new instance of MbCorreos
     */
    public MbCorreos() {
    }

    public void guardar() {

        try {
            boolean ok = validar();
            if (ok == true) {
                DAOCorreos daoCorreo = new DAOCorreos();
                daoCorreo.guardarCorreo(correo);
                Mensajes.Mensajes.MensajeSuccesP("Guardados Satisfactoriamente");
            }
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validar() {
        boolean ok = false;
        if (correo.getServidor().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un servidor");
        } else if (correo.getCorreo().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un correo");
        } else if (correo.getPassword().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un pass");
        } else if (correo.getPuerto() == 0) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un puerto");
        } else if (correo.getProtocolo().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere un protocolo");
        } else {
            ok = true;
        }

        return ok;
    }

    public boolean validarCorreo() {
        boolean ok = false;
        DAOCorreos dao = new DAOCorreos();
        try {
            ok = dao.validarCorreo();
            if (ok == false) {
                Mensajes.Mensajes.mensajeSuccesG("Se requiere una cuenta para de correo.");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgCorreo').show();");
            }
        } catch (SQLException ex) {
            Mensajes.Mensajes.mensajeErrorG(ex.getMessage());
            Logger.getLogger(MbCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }

    public Correos getCorreo() {
        return correo;
    }

    public void setCorreo(Correos correo) {
        this.correo = correo;
    }

}
