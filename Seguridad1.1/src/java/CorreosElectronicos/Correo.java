/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorreosElectronicos;

import correos.dominio.Correos;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Torres
 */
public class Correo {

    public static void enviarCorreo(DatosCorreo correo, Correos ConfigCorreo) {
        try {
            String servidorCorreos = ConfigCorreo.getServidor();
            String user = ConfigCorreo.getCorreo();
            String remitente = ConfigCorreo.getCorreo();
            String passRemitente = ConfigCorreo.getPassword();
            int puerto = ConfigCorreo.getPuerto();
            String protocolo = ConfigCorreo.getProtocolo();
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.live.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.user", user);
            props.setProperty("mail.smtp.pass", passRemitente);
            Session mailSession;
            mailSession = Session.getDefaultInstance(props);
            MimeMessage mensaje = new MimeMessage(mailSession);
            try {
                mensaje.setFrom(new InternetAddress(remitente));
                mensaje.setSender(new InternetAddress(remitente));
//                BodyPart texto = new MimeBodyPart();
//                texto.setText(correo.getDetalle());
//                MimeMultipart multiparte = new MimeMultipart();
//                multiparte.addBodyPart(texto);
                mensaje.setSubject(correo.getAsunto());
                mensaje.setContent(correo.getDetalle(), "text/html");
                mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correo.getPara()));
            } catch (MessagingException e) {
                System.err.println(e);
            }
            Transport transport = null;
            try {
                transport = mailSession.getTransport(protocolo);
                transport.connect(servidorCorreos, puerto, remitente, passRemitente);
                transport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
                transport.close();
            } catch (javax.mail.NoSuchProviderException e) {
                Mensajes.Mensajes.MensajeErrorP(e.getMessage());
                System.err.println(e);
            } catch (MessagingException e) {
                Mensajes.Mensajes.MensajeErrorP(e.getMessage());
                System.err.println(e);
            }
        } catch (Exception ex) {
            Mensajes.Mensajes.MensajeErrorP(ex.getMessage());
            System.err.println(ex);
        }
    }

//    public static void enviarCorreo(DatosCorreo correo, Correos ConfigCorreo) {
//        try {
//            String servidorCorreos = "smtp.live.com";
//            String user = "comodoro_21@hotmail.com";
//            String remitente = "comodoro_21@hotmail.com";
//            String passRemitente = "(torres)!2";
//            int puerto = 587;
//            String protocolo = "smtp";
//            Properties props = new Properties();
//            props.setProperty("mail.smtp.auth", "true");
//            props.setProperty("mail.transport.protocol", "smtp");
//            props.setProperty("mail.host", "smtp.live.com");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.setProperty("mail.smtp.user", user);
//            props.setProperty("mail.smtp.pass", passRemitente);
//            Session mailSession;
//            mailSession = Session.getDefaultInstance(props);
//            MimeMessage mensaje = new MimeMessage(mailSession);
//            try {
//                mensaje.setFrom(new InternetAddress(remitente));
//                mensaje.setSender(new InternetAddress(remitente));
//                BodyPart texto = new MimeBodyPart();
//                texto.setText(correo.getDetalle());
//                MimeMultipart multiparte = new MimeMultipart();
//                multiparte.addBodyPart(texto);
//                mensaje.setSubject(correo.getAsunto());
//                mensaje.setContent(multiparte);
//                mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correo.getPara()));
//            } catch (MessagingException e) {
//                System.err.println(e);
//            }
//            Transport transport = null;
//            try {
//                transport = mailSession.getTransport(protocolo);
//                transport.connect(servidorCorreos, puerto, remitente, passRemitente);
//                transport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
//                transport.close();
//            } catch (javax.mail.NoSuchProviderException e) {
//                System.err.println(e);
//            } catch (MessagingException e) {
//                System.err.println(e);
//            }
//        } catch (Exception ex) {
//            System.err.println(ex);
//        }
//    }
}
