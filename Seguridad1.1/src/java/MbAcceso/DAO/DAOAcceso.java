/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbAcceso.DAO;

import MbAcceso.dominio.Acceso;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PJGT
 */
public class DAOAcceso {

    DataSource ds;

    public DAOAcceso() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean validarContraseña() throws SQLException {
        boolean ok = false;
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM accesoAdministrativo";
        rs = st.executeQuery(sql);
        String clave = "";
        while (rs.next()) {
            clave = rs.getString("password");
        }
        try {
            if (clave.equals(Utilerias.Utilerias.md5("adminadmin"))==true) {
                ok = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public void actualizarContrasenia(Acceso acceso) throws SQLException, Exception {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE accesoadministrativo set password ='" + Utilerias.Utilerias.md5(acceso.getPass1()) + "' ";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
        }

    }
}
