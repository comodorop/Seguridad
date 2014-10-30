/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cedis.DAOCedis;

import Cedis.Dominio.Cedis;
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
public class DAOCedis {

    DataSource ds;

    public DAOCedis(String jndi) {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/" + jndi);
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Cedis> dameCedis() throws SQLException {
        ArrayList<Cedis> lstCedis = new ArrayList<Cedis>();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM cedis";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cedis cedis = new Cedis();
                cedis.setCedis(rs.getString("cedis"));
                cedis.setIdCedis(rs.getInt("idCedis"));
                lstCedis.add(cedis);
            }
        } finally {
            cn.close();
            rs.close();
            st.close();
        }
        return lstCedis;
    }

    public void guardarCedis(int idCedis, int idZona, int id) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String slq = "INSERT INTO usuarioConfig (idUsuario, idCedis, idCedisZona) VALUES('" + id + "', '" + idCedis + "', '" + idZona + "')";
        try {
            st.executeUpdate(slq);
        } finally {
            cn.close();
            st.close();
        }
    }

    public Cedis dameCedis(int idUsuario) throws SQLException {
        Cedis cedi = new Cedis();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarioConfig WHERE idUsuario = '" + idUsuario + "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cedi.setIdCedis(rs.getInt("idCedis"));
                cedi.getZona().setIdZona(rs.getInt("idCedisZona"));
            }
        } finally {
            cn.close();
            rs.close();
            st.close();
        }
        return cedi;
    }

    public void actualizarCedis(int idCedis, int idZona, int idUsuario) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE usuarioConfig set idCedis ='" + idCedis + "', idCedisZona='" + idZona + "' WHERE idUsuario ='" + idUsuario + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
