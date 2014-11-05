/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import Cedis.DAOCedis.DAOCedis;
import Cedis.Dominio.Cedis;
import Cedis.MbCedis;
import MbBaseDatos.MbBasesDatos;
import MbTrreTable.MbArbol;
import Modulos.Dominio.Modulo;
import Modulos.MbModulos;
import Seguridad.DAO.DAOSeguridad;
import Usuarios.DAO.DAOUsuarios;
import Usuarios.Dominio.Usuarios;
import Usuarios.MbUsuarios;
import Utilerias.Utilerias;
import Zonas.Dominio.Zonas;
import accesos.MbAccesos;
import acciones.MbAccion;
import acciones.dominio.Acciones;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import menus.MbMenus;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import perfiles.MbPerfiles;
//import org.primefaces.event.FlowEvent;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbSeguridad implements Serializable {

    @ManagedProperty(value = "#{mbAccion}")
    private MbAccion mbAccion = new MbAccion();
    @ManagedProperty(value = "#{mbArbol}")
    private MbArbol mbArbol = new MbArbol();
    @ManagedProperty(value = "#{mbModulos}")
    private MbModulos mbModulos = new MbModulos();
    @ManagedProperty(value = "#{mbMenus}")
    private MbMenus mbMenus = new MbMenus();
    @ManagedProperty(value = "#{mbUsuarios}")
    private MbUsuarios mbUsuarios = new MbUsuarios();
    @ManagedProperty(value = "#{mbBasesDatos}")
    private MbBasesDatos mbBasesDatos = new MbBasesDatos();
    @ManagedProperty(value = "#{mbCedis}")
    private MbCedis mbCedis = new MbCedis();
    @ManagedProperty(value = "#{mbPerfiles}")
    private MbPerfiles mbPerfiles = new MbPerfiles();
    @ManagedProperty(value = "#{mbAccesos}")
    private MbAccesos mbAccesos = new MbAccesos();

    public MbSeguridad() {
        mbModulos.getModulo().setModulo("modulo");
        mbModulos.getModulo().setUrl("url");
    }

    public void asignarPerfilUsuario() {
        mbAccesos.asignarUsuarioPerfil(mbUsuarios.getCmbUsuario().getIdUsuario(), mbPerfiles.getCmbPerfil().getIdPerfil(), mbBasesDatos.getCmbBase().getIdBaseDatos());
        mbPerfiles.cargarAccesos();
        mbUsuarios.setLstCmbUsuarios(null);
    }

    public void crearTreeTable() {
        mbArbol.crearTreeTable2(mbBasesDatos.getCmbBase().getJndi());
    }

    public void cancelarAccesos() {
        mbPerfiles.getMbAccesos().setSeleccion(null);
    }

    public void cargarZonas() {
        mbCedis.getMbZonas().setLstZonas(null);
        mbCedis.getMbZonas().cargarZonas(mbCedis.getCmbCedis().getIdCedis(), mbBasesDatos.getCmbBase().getJndi());
    }

    public void guardarPerfil() {
        mbPerfiles.guardarPerfil();
        mbArbol.setLstPerfiles(null);
    }

    public MbArbol getMbArbol() {
        return mbArbol;
    }

    public void setMbArbol(MbArbol mbArbol) {
        this.mbArbol = mbArbol;
    }

    public MbModulos getMbModulos() {
        return mbModulos;
    }

    public void setMbModulos(MbModulos mbModulos) {
        this.mbModulos = mbModulos;
    }

    public MbMenus getMbMenus() {
        return mbMenus;
    }

    public void setMbMenus(MbMenus mbMenus) {
        this.mbMenus = mbMenus;
    }

    public void dameSubMenus() {
        mbMenus.getMbSubMenu().setLstSubmenus(null);
        mbMenus.getMbSubMenu().construirSubMenus(mbMenus.getCmbMenu().getIdMenu());
    }

    public void dameInformacionUsuarios() {
        try {
            DAOCedis dao = new DAOCedis(mbBasesDatos.getCmbBase().getJndi());
            Cedis cedis = new Cedis();
            cedis = dao.dameCedis(mbUsuarios.getSelccionUsuairo().getIdUsuario());
            mbCedis.getMbZonas().cargarZonas(cedis.getIdCedis(), mbBasesDatos.getCmbBase().getJndi());
            mbUsuarios.setUsuario(mbUsuarios.getSelccionUsuairo());
            mbCedis.setCmbCedis(new Cedis());
            mbCedis.getMbZonas().setCmbZonas(new Zonas());
            mbCedis.getCmbCedis().setIdCedis(cedis.getIdCedis());
            mbCedis.getMbZonas().getCmbZonas().setIdZona(cedis.getZona().getIdZona());
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            Logger.getLogger(MbSeguridad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
        }
    }

    public String onFlowProcess(FlowEvent event) {
        System.err.println("entro al flow event");
        String valor = event.getOldStep();
        try {
            mbModulos.getModulo().setIdMenu(mbMenus.getCmbMenu().getIdMenu());
            boolean okModulo = mbModulos.validar();
            if (okModulo == false) {
                valor = event.getOldStep();
            } else {
                valor = event.getNewStep();
            }
        } catch (NullPointerException e) {
            valor = event.getOldStep();
            System.err.println("es un null pointer exception del evento");
        }
        return valor;
    }

    public void dameInformacionAccion() {
        mbAccion.setAcciones(mbAccion.getSelecctionAccion());
        mbAccion.setActualizar(true);
    }

    public void guardarUsuario() {
        boolean ok = false;
        ok = mbCedis.validar();
        if (ok) {
            ok = mbUsuarios.validar();
            if (ok) {
                try {
                    Utilerias utis = new Utilerias();
                    mbUsuarios.getUsuario().setPass(utis.generarPasswordAleatorio(mbUsuarios.getUsuario().getUsuario()));
                    DAOUsuarios dao = new DAOUsuarios();
                    DAOCedis daoCedis = new DAOCedis(mbBasesDatos.getCmbBase().getJndi());
                    if (mbUsuarios.getSelccionUsuairo() != null) {
                        dao.actualizarUsuario(mbUsuarios.getUsuario());
                        daoCedis.actualizarCedis(mbCedis.getCmbCedis().getIdCedis(), mbCedis.getMbZonas().getCmbZonas().getIdZona(), mbUsuarios.getUsuario().getIdUsuario());
                        Mensajes.Mensajes.MensajeSuccesP("Exito Usuario Modificado");
                    } else {
                        int id = dao.guardarUsuario(mbUsuarios.getUsuario());
                        daoCedis.guardarCedis(mbCedis.getCmbCedis().getIdCedis(), mbCedis.getMbZonas().getCmbZonas().getIdZona(), id);
                        Mensajes.Mensajes.MensajeSuccesP("Nuevo usuario Registrado");
                    }
                    limpiarCamposUsuarios();
                } catch (SQLException ex) {
                    Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
                    Logger.getLogger(MbSeguridad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void limpiarCamposUsuarios() {
        mbUsuarios.setUsuario(new Usuarios());
        mbCedis.getCmbCedis().setIdCedis(0);
        mbCedis.getMbZonas().getCmbZonas().setIdZona(0);
        mbUsuarios.setLstUsuarios(null);
        mbUsuarios.setSelccionUsuairo(null);
    }

    public void guardarAccion() {
        mbAccion.guardarAccion(mbModulos.getCmbModulo().getIdModulo());
        mbAccion.cargarAcciones(mbModulos.getCmbModulo().getIdModulo());
        mbAccion.getAcciones().setAccion("");
        mbAccion.getAcciones().setId("");
    }

    public void cargarInformacion() {
        mbAccion.cargarAcciones(mbModulos.getCmbModulo().getIdModulo());
        mbAccion.setSelecctionAccion(null);

    }

    public void agregarAcciones() {
        boolean ok = mbAccion.validar();
        if (ok == true) {
            mbAccion.getListaAcciones().add(mbAccion.getAcciones());
            mbAccion.setAcciones(new Acciones());
        }
    }

    public void eliminarAccion() {
        mbAccion.getListaAcciones().remove(mbAccion.getSelecctionAccion());
        mbAccion.setSelecctionAccion(null);
    }

    public void guardar() {
        DAOSeguridad dao = new DAOSeguridad();
        try {
            mbModulos.getModulo().setIdSubMenu(mbMenus.getMbSubMenu().getCmbSubMenu().getIdSubMenu());
            dao.guardarModulosConAcciones(mbModulos.getModulo(), mbAccion.getListaAcciones());
            Mensajes.Mensajes.mensajeSuccesG("Exito.. Nuevo modulo Disponible");
            mbArbol.crearTreeTable2(mbBasesDatos.getCmbBase().getJndi());
        } catch (SQLException ex) {
            Mensajes.Mensajes.MensajeAlertP(ex.getMessage());
            Logger.getLogger(MbSeguridad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.err.println("null pointer exceptiion");
        }
    }

    public void limpiarCamposModulo() {
        System.out.println("entro a limpiar modulos");
        mbMenus.getCmbMenu().setIdMenu(0);
        mbMenus.getMbSubMenu().setLstSubmenus(null);
        mbMenus.getMbSubMenu().construirSubMenus(0);
        mbAccion.setAcciones(new Acciones());
        mbAccion.setSelecctionAccion(null);
        mbModulos.setModulo(new Modulo());
        mbAccion.getListaAcciones().clear();

    }

    public void mensajeAviso() {
        if (mbBasesDatos.getCmbBase().getIdBaseDatos() == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una BASE DE DATOS para continuar");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            mbCedis.cargarCedis(mbBasesDatos.getCmbBase().getJndi());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgUsuario').show();");
        }
    }

    public void mensajeAvisoAcceso() {
        if (mbBasesDatos.getCmbBase().getIdBaseDatos() == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una BASE DE DATOS para continuar");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            mbCedis.cargarCedis(mbBasesDatos.getCmbBase().getJndi());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAsignarPerfil').show();");
        }
    }

    public void guardarAccionesModulos() {
        mbArbol.guardarAcciones(mbBasesDatos.getCmbBase().getJndi(), mbArbol.getCmbPerfil().getIdPerfil());
    }

    public MbAccion getMbAccion() {
        return mbAccion;
    }

    public void setMbAccion(MbAccion mbAccion) {
        this.mbAccion = mbAccion;
    }

    public MbUsuarios getMbUsuarios() {
        return mbUsuarios;
    }

    public void setMbUsuarios(MbUsuarios mbUsuarios) {
        this.mbUsuarios = mbUsuarios;
    }

    public MbBasesDatos getMbBasesDatos() {
        return mbBasesDatos;
    }

    public void setMbBasesDatos(MbBasesDatos mbBasesDatos) {
        this.mbBasesDatos = mbBasesDatos;
    }

    public MbCedis getMbCedis() {
        return mbCedis;
    }

    public void setMbCedis(MbCedis mbCedis) {
        this.mbCedis = mbCedis;
    }

    public MbPerfiles getMbPerfiles() {
        return mbPerfiles;
    }

    public void setMbPerfiles(MbPerfiles mbPerfiles) {
        this.mbPerfiles = mbPerfiles;
    }

    public MbAccesos getMbAccesos() {
        return mbAccesos;
    }

    public void setMbAccesos(MbAccesos mbAccesos) {
        this.mbAccesos = mbAccesos;
    }
}
