/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbBaseDatos.DAO;

import MbBaseDatos.Dominio.BasesDeDatos;
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
public class DAOBaseDatos {

    DataSource ds;

    public DAOBaseDatos() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<BasesDeDatos> dameBaseDatos() throws SQLException {
        ArrayList<BasesDeDatos> lst = new ArrayList<>();
        String sql = "SELECT * FROM basesDeDatos";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                BasesDeDatos base = new BasesDeDatos();
                base.setIdBaseDatos(rs.getInt("idBaseDeDatos"));
                base.setBaseDatos(rs.getString("baseDeDatos"));
                base.setJndi(rs.getString("jndi"));
                lst.add(base);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();
        }
        return lst;
    }

    public BasesDeDatos dameBaseDatos(int idBaseDatos) throws SQLException {
        BasesDeDatos b = new BasesDeDatos();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM basesDeDatos where idBaseDeDatos ='" + idBaseDatos + "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                b.setIdBaseDatos(rs.getInt("idBaseDeDatos"));
                b.setBaseDatos(rs.getString("baseDeDatos"));
                b.setJndi(rs.getString("jndi"));
            }
        } finally {
            cn.close();
            st.close();
            rs.close();
        }
        return b;
    }

    public ArrayList<BasesDeDatos> dameListaBds() throws SQLException {
        ArrayList<BasesDeDatos> lista = new ArrayList<>();
        Connection cn = ds.getConnection();
        Statement preparedStatement = cn.createStatement();
        ResultSet cursorBases = null;
        cn.setAutoCommit(false);
        try {
            cursorBases = preparedStatement.executeQuery("exec sp_databases");
            int id = 1;
            while (cursorBases.next()) {
                BasesDeDatos bds = new BasesDeDatos();
                bds.setIdBaseDatos(id);
                bds.setBaseDatos(cursorBases.getString("DATABASE_NAME"));
                lista.add(bds);
                id++;
            }
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
            throw e;
        } finally {
            preparedStatement.close();
            cursorBases.close();
            cn.close();
        }
        return lista;
    }

    public void guardarNuevaBaseDatos(BasesDeDatos seleccionBaseDisponibles) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO basesDeDatos (baseDeDatos, jndi) VALUES ('" + seleccionBaseDisponibles.getBaseDatos() + "', 'jdbc/__" + seleccionBaseDisponibles.getBaseDatos() + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }


    }

    public void eliminarBase(int idBaseDatos) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "DELETE FROM basesDeDatos WHERE idBaseDeDatos = '" + idBaseDatos + "'";
        try {
            st.executeUpdate(sql);
        } finally {
            st.close();
            cn.close();
        }
    }
}
