/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbTrreTable;

import Modulos.DAO.DAOModulos;
import Modulos.Dominio.Modulo;
import SubMenus.DAO.DAOSubMenu;
import SubMenus.Dominio.SubMenu;
import UsuarioPerfil.DAO.DAOUsuarioPerfil;
import UsuarioPerfil.Dominio.UsuarioPerfil;
import acciones.DAO.DAOAcciones;
import acciones.DAO.DAOAccionesWebAnita;
import acciones.dominio.Acciones;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import menus.DAO.DAOMenus;
import menus.Dominio.Menu;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import perfiles.DAO.DAOPerfiles;
import perfiles.Dominio.Perfiles;

/**
 *
 * @author PJGT
 */
@ManagedBean
@SessionScoped
public class MbArbol implements Serializable {
    
    private TreeNode root1;
    private TreeNode[] selectedNodes2;
    private Perfiles cmbPerfil = new Perfiles();
    private ArrayList<SelectItem> lstPerfiles;
    
    public MbArbol() {
    }
    
    public TreeNode getRoot1() {
        if (root1 == null) {
            root1 = crearTreeTable();
        }
        return root1;
    }
    
    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }
    
    private TreeNode crearTreeTable() {
        root1 = new DefaultTreeNode(new Menu(0, "nuevo menu"), null);
        DAOMenus dao = new DAOMenus();
        DAOSubMenu daoSubMenu = new DAOSubMenu();
        DAOModulos daoModulos = new DAOModulos();
        DAOAcciones daoAcciones = new DAOAcciones();
        try {
            for (Menu m : dao.dameMenus()) {
                TreeNode nodoMenu = new DefaultTreeNode(m, root1);
                for (SubMenu sub : daoSubMenu.dameSubMenus(m.getIdMenu())) {
                    TreeNode nodSubMenu = new DefaultTreeNode(sub, nodoMenu);
                    for (Modulo modulo : daoModulos.dameModulos(m.getIdMenu(), sub.getIdSubMenu())) {
                        TreeNode nodoModulo = new DefaultTreeNode(modulo, nodSubMenu);
                        for (Acciones acciones : daoAcciones.dameAcciones(modulo.getIdModulo())) {
                            TreeNode nodoAcciones = new DefaultTreeNode(acciones, nodoModulo);
                        }
                    }
                }
                for (Modulo modulo : daoModulos.dameModulos(m.getIdMenu())) {
                    TreeNode nodoModulo = new DefaultTreeNode(modulo, nodoMenu);
                    for (Acciones acciones : daoAcciones.dameAcciones(modulo.getIdModulo())) {
                        TreeNode nodoAcciones = new DefaultTreeNode(acciones, nodoModulo);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MbArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root1;
    }
    
    public TreeNode crearTreeTable2(String jdni) {
        root1 = new DefaultTreeNode(new Menu(0, "nuevo menu"), null);
        try {
            DAOMenus dao = new DAOMenus();
            DAOSubMenu daoSubMenu = new DAOSubMenu();
            DAOModulos daoModulos = new DAOModulos();
            DAOAcciones daoAcciones = new DAOAcciones();
            DAOAccionesWebAnita daoAnita = new DAOAccionesWebAnita(jdni);
            ArrayList<UsuarioPerfil> lstUsuario = null;
            if (cmbPerfil.getIdPerfil() != 0) {
                lstUsuario = daoAnita.dameAccionesPerfil(cmbPerfil.getIdPerfil());
            } else {
                lstUsuario = new ArrayList<>();
            }
            try {
                for (Menu m : dao.dameMenus()) {
                    TreeNode nodoMenu = new DefaultTreeNode(m, root1);
                    for (SubMenu sub : daoSubMenu.dameSubMenus(m.getIdMenu())) {
                        TreeNode nodSubMenu = new DefaultTreeNode(sub, nodoMenu);
                        for (Modulo modulo : daoModulos.dameModulos(m.getIdMenu(), sub.getIdSubMenu())) {
                            TreeNode nodoModulo = new DefaultTreeNode(modulo, nodSubMenu);
                            for (Acciones acciones : daoAcciones.dameAcciones(modulo.getIdModulo())) {
                                TreeNode nodoAcciones = new DefaultTreeNode(acciones, nodoModulo);
                                for (UsuarioPerfil usuarioP : lstUsuario) {
                                    if (acciones.getIdAccion() == usuarioP.getIdAccion()) {
                                        nodoAcciones.setSelected(true);
                                        nodoModulo.setSelected(true);
                                        nodSubMenu.setSelected(true);
                                        nodoMenu.setSelected(true);
                                        nodoAcciones.setSelectable(true);
                                        nodoModulo.setExpanded(true);
                                        nodoMenu.setExpanded(true);
                                        nodSubMenu.setExpanded(true);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (Modulo modulo : daoModulos.dameModulos(m.getIdMenu())) {
                        TreeNode nodoModulo = new DefaultTreeNode(modulo, nodoMenu);
                        for (Acciones acciones : daoAcciones.dameAcciones(modulo.getIdModulo())) {
                            TreeNode nodoAcciones = new DefaultTreeNode(acciones, nodoModulo);
                            for (UsuarioPerfil usuarioP : lstUsuario) {
                                if (acciones.getIdAccion() == usuarioP.getIdAccion()) {
                                    nodoAcciones.setSelected(true);
                                    nodoModulo.setSelected(true);
                                    nodoMenu.setSelected(true);
                                    nodoAcciones.setSelectable(true);
                                    nodoModulo.setExpanded(true);
                                    nodoMenu.setExpanded(true);
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MbArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MbArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root1;
    }
    
    public void guardarAcciones(String jndi, int idPerfil) {
        ArrayList<Acciones> lstAcciones = new ArrayList<>();
        DAOUsuarioPerfil dao = new DAOUsuarioPerfil(jndi);
        if (selectedNodes2 != null && selectedNodes2.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (TreeNode node : selectedNodes2) {
                try {
                    Acciones ac = (Acciones) node.getData();
                    lstAcciones.add(ac);
                } catch (NullPointerException e) {
                } catch (ClassCastException e) {
                } catch (Exception e) {
                }
                builder.append(node.getData().toString());
                builder.append("<br />");
            }
            try {
                dao.guardarUsuarioPerfil(lstAcciones, idPerfil);
            } catch (SQLException ex) {
                Mensajes.Mensajes.mensajeErrorG(ex.getMessage());
                Logger.getLogger(MbArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }
    
    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }
    
    public Perfiles getCmbPerfil() {
        return cmbPerfil;
    }
    
    public void setCmbPerfil(Perfiles cmbPerfil) {
        this.cmbPerfil = cmbPerfil;
    }
    
    public ArrayList<SelectItem> getLstPerfiles() {
        if (lstPerfiles == null) {
            try {
                lstPerfiles = new ArrayList<>();
                DAOPerfiles dao = new DAOPerfiles();
                Perfiles perfil = new Perfiles();
                perfil.setIdPerfil(0);
                perfil.setPerfil("Seleccione un Perfil");
                lstPerfiles.add(new SelectItem(perfil, perfil.getPerfil()));
                for (Perfiles p : dao.damePerfiles()) {
                    lstPerfiles.add(new SelectItem(p, p.getPerfil()));
                }
            } catch (SQLException ex) {
            }
            
        }
        return lstPerfiles;
    }
    
    public void setLstPerfiles(ArrayList<SelectItem> lstPerfiles) {
        this.lstPerfiles = lstPerfiles;
    }
}
