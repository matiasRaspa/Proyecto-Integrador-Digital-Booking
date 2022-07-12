package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Booking;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Image;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.User;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForMail;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.UserDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IEmailSenderService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.RestService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsForMailService;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTML;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class EmailSenderService implements IEmailSenderService {

    private static final Logger logger = Logger.getLogger(EmailSenderService.class);
    @Autowired
    private JavaMailSender javaEmailSender;
    @Autowired
    private RestService restService;

    @Autowired
    private IBookingDetailsForMailService bookingDetailsForMailService;

    @Override
    @Async
    public void sendEmail(String to, String subject, String body) {
        logger.info("Sending email to: " + to);
        logger.info("Subject: " + subject);
        try {
            MimeMessage message = javaEmailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(body, true);
            logger.info("Body created");
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setFrom("dg.booking6@gmail.com");
            javaEmailSender.send(message);
            logger.info("Email sent to " + to);

        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }


    }

    @Async
    public String createEnableUserTemplate(String fullname, String url) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        logger.info("createEnableUserTemplate");
        String file = restService.getHtmlFromUrl("https://bucket-grupo6-c1.s3.us-east-2.amazonaws.com/emailResources/emailEnable2.html");


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("emailEnable.html"));
            writer.write(file);
            writer.close();
            File f = new File("emailEnable.html");

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            // replace the placeholders with the actual values
            sb.replace(sb.indexOf("{/fullname/}"), sb.indexOf("{/fullname/}") + "{/fullname/}".length(), fullname);
            sb.replace(sb.indexOf("{/enlace/}"), sb.indexOf("{/enlace/}") + "{/enlace/}".length(), url);
            sb.replace(sb.indexOf("{/enlace/}"), sb.indexOf("{/enlace/}") + "{/enlace/}".length(), url);
            sb.replace(sb.indexOf("{/enlace/}"), sb.indexOf("{/enlace/}") + "{/enlace/}".length(), url);
            logger.info("Email template created successfully");


        } catch (Exception e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Async
    public String createBookingConfirmationTemplate(BookingDetailsForMail bookingDetailsForMail) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        logger.info("createEnableUserTemplate");
        String file = restService.getHtmlFromUrl("https://bucket-grupo6-c1.s3.us-east-2.amazonaws.com/emailResources/emailConfirmation.html");
        logger.info(file.toString());


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("emailConfirmation.html"));
            bw.write(file);
            bw.close();
            File f = new File("emailConfirmation.html");


            String name = bookingDetailsForMail.getUsername();
            String location = bookingDetailsForMail.getLocationName();
            String productName = bookingDetailsForMail.getProductName();
            String startDate = bookingDetailsForMail.getStartDate();
            String eta = bookingDetailsForMail.getEta();
            Long bookingId = bookingDetailsForMail.getId();
            String imagesUrl = bookingDetailsForMail.getImageUrl();
            String urlGoogleMaps = "https://www.google.com/maps/embed/v1/place?key=AIzaSyDqGQhTTknu4Bfw1R1gXgoSXoYiYejzA-M&q=" + productName.replace(" ", "+").replace("&", "") + bookingDetailsForMail.getLocationName().replace(" ", "") + "&center=" + bookingDetailsForMail.getLatitude() + "," + bookingDetailsForMail.getLongitude() + "&zoom=15";


            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            sb.replace(sb.indexOf("{/name/}"), sb.indexOf("{/name/}") + "{/name/}".length(), name);
            sb.replace(sb.indexOf("{/location/}"), sb.indexOf("{/location/}") + "{/location/}".length(), location);
            sb.replace(sb.indexOf("{/productName/}"), sb.indexOf("{/productName/}") + "{/productName/}".length(), productName);
            sb.replace(sb.indexOf("{/productName/}"), sb.indexOf("{/productName/}") + "{/productName/}".length(), productName);
            sb.replace(sb.indexOf("{/startDate/}"), sb.indexOf("{/startDate/}") + "{/startDate/}".length(), startDate);
            sb.replace(sb.indexOf("{/eta/}"), sb.indexOf("{/eta/}") + "{/eta/}".length(), eta);
            sb.replace(sb.indexOf("{/bookingId/}"), sb.indexOf("{/bookingId/}") + "{/bookingId/}".length(), "Bk-000" +bookingId.toString());
            sb.replace(sb.indexOf("{/urlImg1/}"), sb.indexOf("{/urlImg1/}") + "{/urlImg1/}".length(), imagesUrl);
            sb.replace(sb.indexOf("{/urlGoogleMaps/}"), sb.indexOf("{/urlGoogleMaps/}") + "{/urlGoogleMaps/}".length(), urlGoogleMaps);


        } catch (Exception e) {
            System.out.println("error" + e);
            e.printStackTrace();
            System.out.println("Fatal Error");
        }
        logger.info("Email template created successfully");
        return sb.toString();
    }
}
