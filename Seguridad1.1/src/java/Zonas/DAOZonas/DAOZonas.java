/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Zonas.DAOZonas;

import Zonas.Dominio.Zonas;
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
public class DAOZonas {

    DataSource ds;

    public DAOZonas(String jndi) {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/" + jndi);
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Zonas> dameZonas(int idCedis) throws SQLException {
        ArrayList<Zonas> lst = new ArrayList<>();

        String sql = "select * from cedisZonasDetalle zona \n"
                + "inner join cedisZonas cz \n"
                + "on zona.idZona = cz.idZona\n"
                + "where zona.idCedis = '" + idCedis + "';";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Zonas zonas = new Zonas();
                zonas.setIdZona(rs.getInt("idCedis"));
                zonas.setZona(rs.getString("zona"));
                lst.add(zonas);
            }
        } finally {
            cn.close();
            rs.close();
            st.close();
        }

        return lst;
    }
}
