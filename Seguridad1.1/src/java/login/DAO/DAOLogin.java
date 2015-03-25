/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.DAO;

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
import login.dominio.Login;

/**
 *
 * @author PJGT
 */
public class DAOLogin {

    DataSource ds;

    public DAOLogin() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Login validarAcceso(Login login) throws SQLException {
        String pass = "";
        try {
            pass = Utilerias.Utilerias.md5(login.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Login log = new Login();
        String nombre = "";
        Connection cn = ds.getConnection();
        String sql = "SELECT * FROM accesoAdministrativo WHERE password='" + pass + "'";
        Statement st = cn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("password");
            }
            if (nombre.equals("")) {
                log.setPassword("");
            } else {
                log.setPassword(nombre);
            }
        } finally {
            cn.close();
        }
        return log;
    }

    public boolean verificarUsuarioDisponible() throws SQLException {
        boolean ok = false;
        Connection cn = ds.getConnection();
        String nombre = "";
        String sql = "SELECT * FROM accesoAdministrativo";
        Statement st = cn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("password");
            }
            if (nombre.equals("")) {
                ok = false;
            } else {
                ok = true;
            }
        } finally {
            cn.close();
        }
        return ok;
    }

    public void guardarNuevoUsuario(Login login) throws SQLException, Exception {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "INSERT INTO accesoAdministrativo (password) VALUES ('" + Utilerias.Utilerias.md5(login.getPassword()) + "')";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
        }
    }
}
