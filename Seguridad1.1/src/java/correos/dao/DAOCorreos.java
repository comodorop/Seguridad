/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.dao;

import correos.dominio.Correos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PJGT
 */
public class DAOCorreos {

    DataSource ds;

    public DAOCorreos() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public boolean verificar() throws SQLException {
        boolean ok = false;
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "SELECT * FROM correos";
        ResultSet rs = null;
        int valor = 0;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                valor = rs.getInt(rs.getInt("idCorreo"));
            }
        } finally {
            st.close();
//            rs.close();
            cn.close();
        }
        if (valor <= 0) {
            ok = false;
        } else {
            ok = true;
        }
        return ok;
    }

    public void guardarCorreo(Correos correo) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO correos (servidor, correo, password, puerto, protocolo) VALUES ('" + correo.getServidor() + "', '" + correo.getCorreo() + "', '" + correo.getPassword() + "', '" + correo.getPuerto() + "', '" + correo.getProtocolo() + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
            st.close();
        }

    }
}
