/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios.DAO;

import Usuarios.Dominio.Usuarios;
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
public class DAOUsuarios {

    DataSource ds;

    public DAOUsuarios() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int guardarUsuario(Usuarios usuarios) throws SQLException, Exception {
        int id = 0;
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "INSERT INTO usuarios (usuario, login, password, email) VALUES ('" + usuarios.getUsuario() + "', '" + usuarios.getLogin() + "', '" + Utilerias.Utilerias.md5(usuarios.getPass()) + "', '" + usuarios.getEmail() + "')";
        String sqlIdentity = "SELECT @@IDENTITY as indentidad";
        try {
            st.executeUpdate(sql);
            rs = st.executeQuery(sqlIdentity);
            while (rs.next()) {
                id = rs.getInt("indentidad");
            }
        } finally {
            cn.close();
        }
        return id;
    }

    public ArrayList<Usuarios> dameUsuarios() throws SQLException {
        ArrayList<Usuarios> lstUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                lstUsuarios.add(u);
            }
        } finally {
            st.close();
            cn.close();
        }
        return lstUsuarios;
    }

    public ArrayList<Usuarios> dameUsuariosNoAsignados() throws SQLException {
        ArrayList<Usuarios> lstUsuarios = new ArrayList<>();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "select * from usuarios  u\n"
                + "left join accesos a \n"
                + "on u.idUsuario = a.idUsuario\n"
                + "where a.idUsuario is null";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUsuario(rs.getString("usuario"));
                lstUsuarios.add(usuario);
            }
        } finally {
            st.close();
            rs.close();
            cn.close();
        }
        return lstUsuarios;
    }

    public void actualizarUsuario(Usuarios usuario) throws SQLException {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE usuarios set login ='" + usuario.getLogin() + "', usuario ='" + usuario.getUsuario() + "', email ='" + usuario.getEmail() + "' WHERE idUsuario ='" + usuario.getIdUsuario() + "' ";
        try {
            st.executeUpdate(sql);
        } finally {
            cn.close();
            st.close();
        }
    }

    public Usuarios dameUsuarios(int id) throws SQLException {
        Usuarios usuarios = new Usuarios();
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios WHERE idUsuario = '" + id + "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                usuarios.setIdUsuario(rs.getInt("idUsuario"));
                usuarios.setUsuario(rs.getString("usuario"));
            }
        } finally {
            rs.close();
            cn.close();
            st.close();
        }
        return usuarios;
    }

    public void actualizarContraseña(Usuarios seleccion) throws SQLException, Exception {
        Connection cn = ds.getConnection();
        Statement st = cn.createStatement();
        String sql = "UPDATE usuarios set password='" + Utilerias.Utilerias.md5(seleccion.getPass()) + "' WHERE idUsuario = '" + seleccion.getIdUsuario() + "'";
        st.executeUpdate(sql);
    }
}
