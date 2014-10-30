/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensajes;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author PJGT
 */
public class Mensajes {

    public static void MensajeAlertP(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", mensaje));
    }
    public static void MensajeErrorP(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensaje));
    }
    public static void MensajeSuccesP(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", mensaje));
    }

    public static void mensajeSuccesG(String valor) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito:", valor);
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("ok", true);
    }
       public static void mensajeErrorG(String valor) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", valor);
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("ok", true);
    }
}
