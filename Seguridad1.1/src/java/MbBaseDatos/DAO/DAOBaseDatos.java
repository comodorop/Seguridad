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
}
