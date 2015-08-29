/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorreosElectronicos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Torres
 */
@Named(value = "mbCorreo")
@SessionScoped
public class MbCorreo implements Serializable {

    /**
     * Creates a new instance of MbCorreo
     */
    public MbCorreo() {
    }
    
}
