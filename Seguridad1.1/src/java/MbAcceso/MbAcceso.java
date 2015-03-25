/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbAcceso;

import MbAcceso.DAO.DAOAcceso;
import MbAcceso.dominio.Acceso;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author PJGT
 */
@Named(value = "mbAcceso")
@SessionScoped
public class MbAcceso implements Serializable {

    private Acceso acceso = new Acceso();

    /**
     * Creates a new instance of MbAcceso
     */
    public MbAcceso() {
    }

    public void actualizarContrasenias() {
        boolean ok = validar();
        if (ok) {
            DAOAcceso dao = new DAOAcceso();
            try {
                dao.actualizarContrasenia(acceso);
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgPass').hide();");
            } catch (SQLException ex) {
                Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                Logger.getLogger(MbAcceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validar() {
        boolean ok = false;
        if (acceso.getPass1().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere la contraseña #1");
        } else if (acceso.getPass2().equals("")) {
            Mensajes.Mensajes.MensajeAlertP("Se requiere la contraseña #2");
        } else if (acceso.getPass1().equals(acceso.getPass2()) == false) {
            Mensajes.Mensajes.MensajeAlertP("Las cotraseñas no coinciden");
        } else {
            ok = true;
        }
        return ok;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

}
