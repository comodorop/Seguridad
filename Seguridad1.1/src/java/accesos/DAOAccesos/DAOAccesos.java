/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesos.DAOAccesos;

import accesos.Dominio.Accesos;
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
public class DAOAccesos {

    DataSource ds;

    public DAOAccesos() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Accesos> listaAccesos(int idPerfil) throws SQLException {
        ArrayList<Accesos> lstAccesoses = new ArrayList<>();
        String sql = "SELECT * FROM accesos a "
                + "INNER JOIN usuarios u "
                + "on a.idUsuario = u.idUsuario "
                + "INNER JOIN perfiles p on "
                + "p.idPerfil = a.idPerfil "
                + "WHERE a.idPerfil = '" + idPerfil + "'";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Accesos acceso = new Accesos();
                acceso.getUsuarios().setUsuario(rs.getString("usuario"));
                acceso.getUsuarios().setIdUsuario(rs.getInt("idUsuario"));
                lstAccesoses.add(acceso);
            }
        } finally {
            rs.close();
            st.close();
            cn.close();
        }

        return lstAccesoses;
    }

    public ArrayList<Accesos> listaAccesos(int idPerfil, int idBaseDatos) throws SQLException {
        ArrayList<Accesos> lstAccesoses = new ArrayList<>();
        String sql = "SELECT * FROM accesos a "
                + "INNER JOIN usuarios u "
                + "on a.idUsuario = u.idUsuario "
                + "INNER JOIN perfiles p on "
                + "p.idPerfil = a.idPerfil "
                + "WHERE a.idPerfil = '" + idPerfil + "' and idDbs = '"+idBaseDatos+"'";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Accesos acceso = new Accesos();
                acceso.getUsuarios().setUsuario(rs.getString("usuario"));
                acceso.getUsuarios().setIdUsuario(rs.getInt("idUsuario"));
                lstAccesoses.add(acceso);
            }
        } finally {
            rs.close();
            st.close();
            cn.close();
        }

        return lstAccesoses;
    }

    public void asignarPerfilUsuario(int idUsuario, int idPerfil, int idBd) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO accesos (idUsuario, idDbs, idPerfil) VALUES ('" + idUsuario + "', '" + idBd + "', '" + idPerfil + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
            st.close();
        }
    }
}
