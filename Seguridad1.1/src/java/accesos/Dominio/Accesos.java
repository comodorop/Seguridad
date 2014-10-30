/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesos.Dominio;

import MbBaseDatos.Dominio.BasesDeDatos;
import Usuarios.Dominio.Usuarios;
import perfiles.Dominio.Perfiles;

/**
 *
 * @author PJGT
 */
public class Accesos {
    private Usuarios usuarios =  new Usuarios();
    private BasesDeDatos  baseDatos = new BasesDeDatos();
    private Perfiles perfil = new  Perfiles();

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public BasesDeDatos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(BasesDeDatos baseDatos) {
        this.baseDatos = baseDatos;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }
           
    
    
}
