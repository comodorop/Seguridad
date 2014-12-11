/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import menus.Dominio.Menu;

/**
 *
 * @author PJGT
 */
public class DAOMenus {

    DataSource ds;

    public DAOMenus() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
        }
    }

    public ArrayList<Menu> dameMenus() throws SQLException {
        ArrayList<Menu> lstMenu = new ArrayList<>();
        String sql = "SELECT * FROM modulosMenus";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Menu menu = new Menu(rs.getInt("idMenu"), rs.getString("menu"));
            menu.setIdMenu(rs.getInt("idMenu"));
            menu.setMenu(rs.getString("menu"));
            lstMenu.add(menu);
        }
        return lstMenu;
    }

    public Menu dameMenu(int id) throws SQLException {
        Menu menu = new Menu();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "SELECT * FROM modulosMenus WHERE idMenu = '" + id + "'";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                menu.setIdMenu(rs.getInt("idMenu"));
                menu.setMenu(rs.getString("menu"));
            }
        } finally {
            st.close();
            cn.close();
        }
        return menu;
    }

    public void actualizarMenu(Menu menu) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE modulosMenus set menu='" + menu.getMenu() + "' WHERE idMenu='" + menu.getIdMenu() + "'";

        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }

    public void guardarMenu(Menu menu) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO modulosMenus (menu) VALUES('" + menu.getMenu() + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
