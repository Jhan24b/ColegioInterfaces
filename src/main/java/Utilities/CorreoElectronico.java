/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import JavaBean.Alumno;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author xbest
 */
public class CorreoElectronico {

    public void enviarCorreo(String asunto, String mensaje, Alumno alumno) {
        // Configuración del servidor de correo saliente (SMTP)
        final String correoEmisor = "molinaunicom@gmail.com";
        final String contrasenaEmisor = "Unicom123@";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticación del remitente
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, contrasenaEmisor);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEmisor));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(alumno.getCorreo_electrico()));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }

}
