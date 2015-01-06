/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.io.File;
import java.security.MessageDigest;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author PJGT
 */
public class Utilerias {

    public String generarPasswordAleatorio(String nombre) {
        String pass = "";
        String parte1 = nombre.substring(0, 3);
        String parte2 = generarLetrasAleatorias(3);
        pass = parte1 + parte2;
        return pass;
    }

    public String generarLetrasAleatorias(int longitudContraseña) {
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!#$";
        String contrasena = "";
        int longitud = base.length();
        for (int i = 0; i < longitudContraseña; i++) {
            int numero = (int) (Math.random() * (longitud));
            String caracter = base.substring(numero, numero + 1);
            contrasena = contrasena + caracter;
        }
        return contrasena;
    }

    public void enviarCorreoElectronico() {

        String servidorCorreos = "mail.laanita.com";
        String user = "carlos.pat";
        String remitente = "carlos.pat@laanita.com";
        String passRemitente = "Usuario1";
        int puerto = 587;
        String protocolo = "smtp";
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocolo);
        props.setProperty("mail.smtp.host", servidorCorreos);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.user", user);
        props.setProperty("mail.smtp.pass", passRemitente);
        Session mailSession;
        mailSession = Session.getDefaultInstance(props);
        MimeMessage mensaje = new MimeMessage(mailSession);
        try {
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setSender(new InternetAddress(remitente));
            BodyPart texto = new MimeBodyPart();
            texto.setText("Aqui va el contenido");
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
//            BodyPart adjunto = new MimeBodyPart();
//            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
//            multiparte.addBodyPart(adjunto);
            mensaje.setSubject("Envio de contraseña del sistema");
            mensaje.setContent(multiparte);
//            if (emails.indexOf(',') > 0) {
//                mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emails));
//            } else {
//                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(emails));
//            }
        } catch (MessagingException e) {
        }

        Transport transport;
        try {
            transport = mailSession.getTransport(protocolo);
            transport.connect(servidorCorreos, puerto, user, passRemitente);
            transport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException e) {
        } catch (MessagingException e) {
        }
    }

    public static String md5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuilder h = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0");
                h.append(Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
}
