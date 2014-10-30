/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad.DAO;

import Modulos.Dominio.Modulo;
import acciones.dominio.Acciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class DAOSeguridad {

    DataSource ds;

    public DAOSeguridad() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void guardarModulosConAcciones(Modulo modulo, ArrayList<Acciones> lstAcciones) throws SQLException {
        Connection cn = ds.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idModulo = 0;
        Statement st = null;
        try {
            st = cn.createStatement();
            st.executeUpdate("begin transaction");
            String sqlInsertarModulo = "INSERT INTO modulos (modulo, url, idSubMenu, idMenu) VALUES ('" + modulo.getModulo() + "', '" + modulo.getUrl() + "', '" + modulo.getIdSubMenu() + "', '" + modulo.getIdMenu() + "')";
            st.executeUpdate(sqlInsertarModulo);
            String sqlIdentity = "SELECT @@IDENTITY as indentidad";
            rs = st.executeQuery(sqlIdentity);
            while (rs.next()) {
                idModulo = rs.getInt("indentidad");
            }
            for (Acciones acciones : lstAcciones) {
                String sqlInsertarAcciones = "INSERT INTO acciones (accion, idBoton, idModulo) VALUES ('" + acciones.getAccion() + "', '" + acciones.getId() + "', '" + idModulo + "')";
                ps = cn.prepareStatement(sqlInsertarAcciones);
                ps.executeUpdate();
            }
            st.executeUpdate("commit transaction");
        } catch (SQLException ex) {
            st.executeUpdate("rollback transaction");
            throw ex;
        } finally {

            cn.close();
            ps.close();
            rs.close();
        }
    }
}
