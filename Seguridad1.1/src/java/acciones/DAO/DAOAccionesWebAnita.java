/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.DAO;

import UsuarioPerfil.Dominio.UsuarioPerfil;
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
public class DAOAccionesWebAnita {

    DataSource ds;

    public DAOAccionesWebAnita(String jndi) {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/" + jndi);
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<UsuarioPerfil> dameAccionesPerfil(int idModulo, int idPerfil) throws SQLException {
        ArrayList<UsuarioPerfil> lstPerfil = new ArrayList<>();
        Connection cn = ds.getConnection();
        String sql = "SELECT * FROM usuarioPerfil WHERE idModulo = '" + idModulo + "' and idPerfil='" + idPerfil + "'";
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                UsuarioPerfil usuario = new UsuarioPerfil();
                usuario.setIdAccion(rs.getInt("idAccion"));
                usuario.setIdModulo(rs.getInt("idModulo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                lstPerfil.add(usuario);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();

        }

        return lstPerfil;
    }

    public ArrayList<UsuarioPerfil> dameAccionesPerfil(int idPerfil) throws SQLException {
        ArrayList<UsuarioPerfil> lstPerfil = new ArrayList<>();
        Connection cn = ds.getConnection();
        String sql = "SELECT * FROM usuarioPerfil WHERE idPerfil='" + idPerfil + "'";
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
             rs = st.executeQuery(sql);
            while (rs.next()) {
                UsuarioPerfil usuario = new UsuarioPerfil();
                usuario.setIdAccion(rs.getInt("idAccion"));
                usuario.setIdModulo(rs.getInt("idModulo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                lstPerfil.add(usuario);
            }
        } finally {
            rs.close();
            st.close();
            cn.close();
        }

        return lstPerfil;
    }
}
