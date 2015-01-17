/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioPerfil.DAO;

import acciones.dominio.Acciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class DAOUsuarioPerfil {

    DataSource ds;

    public DAOUsuarioPerfil(String jndi) {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/" + jndi);
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void guardarUsuarioPerfil(ArrayList<Acciones> lstAcciones, int idPerfil) throws SQLException {
        Connection cn = ds.getConnection();
        PreparedStatement ps = null;
        Statement st = cn.createStatement();
        try {
            String sqlTruncarTabla = "DELETE FROM usuarioPerfil WHERE idPerfil = " + idPerfil + "";
            st.executeUpdate(sqlTruncarTabla);
            for (Acciones acc : lstAcciones) {
                String sql = "INSERT INTO usuarioPerfil (idPerfil, idModulo, idAccion) VALUES ('" + idPerfil + "','" + acc.getIdModulo() + "', '" + acc.getIdAccion() + "')";
                ps = cn.prepareStatement(sql);
                ps.executeUpdate();
            }
        } finally {
            st.close();
            ps.close();
            cn.close();
        }
    }
}
