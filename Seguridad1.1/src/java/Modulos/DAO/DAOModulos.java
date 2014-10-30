/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos.DAO;

import Modulos.Dominio.Modulo;
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
public class DAOModulos {

    DataSource ds;

    public DAOModulos() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Modulo> dameModulos(int idMenu) throws SQLException {
        ArrayList<Modulo> lstModulo = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * from modulos WHERE idmenu = '" + idMenu + "' order by idMenu";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Modulo modulo = new Modulo();
                modulo.setIdModulo(rs.getInt("idModulo"));
                modulo.setModulo(rs.getString("modulo"));
                lstModulo.add(modulo);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();
        }

        return lstModulo;
    }

    public ArrayList<Modulo> dameModulos(int idMenu, int idSubMenu) throws SQLException {
        ArrayList<Modulo> lstModulo = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * from modulos "
                + "WHERE idmenu = '" + idMenu + "' "
                + "and idSubMenu='" + idSubMenu + "' "
                + "order by idMenu";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Modulo modulo = new Modulo();
                modulo.setIdModulo(rs.getInt("idModulo"));
                modulo.setModulo(rs.getString("modulo"));
                lstModulo.add(modulo);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();
        }
        return lstModulo;
    }

    public ArrayList<Modulo> dameModulos() throws SQLException {
        ArrayList<Modulo> lstModulo = new ArrayList<>();
        String slq = "SELECT * FROM modulos";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(slq);
            while (rs.next()) {
                Modulo m = new Modulo();
                m.setIdMenu(rs.getInt("idMenu"));
                m.setIdModulo(rs.getInt("idModulo"));
                m.setModulo(rs.getString("modulo"));
                lstModulo.add(m);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();
        }
        return lstModulo;
    }

    public Modulo dameModulo(int id) throws SQLException {
        Modulo modulo = new Modulo();
        ResultSet rs = null;
        String sql = "SELECT * FROM modulos WHERE idModulo = '" + id + "'";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                modulo.setIdModulo(rs.getInt("idModulo"));
                modulo.setModulo(rs.getString("modulo"));

            }
        } finally {
            cn.close();
            rs.close();
            st.close();
        }
        return modulo;
    }
}
