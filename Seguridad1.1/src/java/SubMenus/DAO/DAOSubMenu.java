/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenus.DAO;

import SubMenus.Dominio.SubMenu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PJGT
 */
public class DAOSubMenu {

    DataSource ds;

    public DAOSubMenu() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<SubMenu> dameSubMenus(int idMenu) throws SQLException {
        ArrayList<SubMenu> lstSubmenu = new ArrayList<>();
        String sql = "SELECT * FROM modulosSubMenus WHERE idMenu='" + idMenu + "'";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SubMenu subMenu = new SubMenu();
                subMenu.setIdSubMenu(rs.getInt("idSubMenu"));
                subMenu.setSubMenu(rs.getString("subMenu"));
                lstSubmenu.add(subMenu);
            }
        } finally {
            cn.close();
        }
        return lstSubmenu;
    }

    public SubMenu dameSubMenu(int idSubmenu) throws SQLException {
        SubMenu subMenu = new SubMenu();
        String sql = "SELECT * FROM modulosSubMenus WHERE idSubMenu='" + idSubmenu + "'";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                subMenu.setIdSubMenu(rs.getInt("idSubMenu"));
                subMenu.setSubMenu(rs.getString("subMenu"));
            }
        } finally {
            cn.close();
        }
        return subMenu;
    }

    public void guardarSubMenu(SubMenu subMenu) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO modulosSubMenus (subMenu, idMenu) VALUES ('" + subMenu.getSubMenu() + "','" + subMenu.getIdMenu() + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }

    public void actualizarSubMenu(SubMenu subMenu) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE modulosSubMenus set subMenu = '" + subMenu.getSubMenu() + "' WHERE idSubMenu ='" + subMenu.getIdSubMenu() + "' and idMenu='" + subMenu.getIdMenu() + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
