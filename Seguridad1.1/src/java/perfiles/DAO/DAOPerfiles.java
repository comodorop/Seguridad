/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perfiles.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import perfiles.Dominio.Perfiles;

/**
 *
 * @author PJGT
 */
public class DAOPerfiles {

    DataSource ds;

    public DAOPerfiles() {

        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Perfiles> damePerfiles() throws SQLException {
        ArrayList<Perfiles> lstPerfiles = new ArrayList<>();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "SELECT * FROM perfiles";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Perfiles perfil = new Perfiles();
                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setPerfil(rs.getString("perfil"));
                lstPerfiles.add(perfil);
            }
        } finally {
            cn.close();
        }
        return lstPerfiles;
    }

    public Perfiles damePerfil(int idPerfil) throws SQLException {
        Perfiles p = new Perfiles();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "SELECT * FROM perfiles WHERE idPerfil = '" + idPerfil + "'";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                p.setIdPerfil(rs.getInt("idPerfil"));
                p.setPerfil(rs.getString("perfil"));
            }
        } finally {
            cn.close();
        }
        return p;
    }

    public void actualizarAsignacionPerfilAcceso(int idPerfil, int idUsuario) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE accesos set idPerfil = '" + idPerfil + "' WHERE idUsuario ='" + idUsuario + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
            st.close();
        }
    }

    public void guardarPerfil(Perfiles perfil) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO perfiles (perfil) VALUES ('" + perfil.getPerfil() + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }

    public void actualizarPerfil(Perfiles perfil) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE perfiles set perfil ='" + perfil.getPerfil() + "' WHERE idPerfil = '" + perfil.getIdPerfil() + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
