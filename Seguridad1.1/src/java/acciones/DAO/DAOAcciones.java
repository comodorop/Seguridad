/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones.DAO;

import acciones.dominio.Acciones;
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
public class DAOAcciones {

    DataSource ds;

    public DAOAcciones() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public DAOAcciones(String jndi) {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/" + jndi);
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Acciones> dameAcciones(int idModulo) throws SQLException {
        ArrayList<Acciones> lstAcciones = new ArrayList<>();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String slq = "SELECT * FROM acciones WHERE idModulo = '" + idModulo + "'";
        try {
            rs = st.executeQuery(slq);
            while (rs.next()) {
                Acciones acciones = new Acciones();
                acciones.setIdAccion(rs.getInt("idAccion"));
                acciones.setAccion(rs.getString("accion"));
                acciones.setId(rs.getString("idBoton"));
                acciones.setIdModulo(rs.getInt("idModulo"));
                lstAcciones.add(acciones);
            }
        } finally {
            rs.close();
            st.close();
            cn.close();
        }
        return lstAcciones;
    }

    public void guardarAcciones(Acciones acciones) throws SQLException {
        String slq = "INSERT INTO acciones (accion, idBoton, idModulo) VALUES('" + acciones.getAccion() + "', '" + acciones.getId() + "', '" + acciones.getIdModulo() + "')";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            st.execute(slq);
        } finally {
            st.close();
            cn.close();
        }
    }

    public void actalizarAcciones(Acciones acciones) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE acciones set accion = '" + acciones.getAccion() + "', idBoton = '" + acciones.getId() + "' WHERE idAccion ='" + acciones.getIdAccion() + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
