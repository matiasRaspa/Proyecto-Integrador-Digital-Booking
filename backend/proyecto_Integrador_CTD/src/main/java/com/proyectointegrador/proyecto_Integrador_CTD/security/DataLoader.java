package com.proyectointegrador.proyecto_Integrador_CTD.security;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Role;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.RoleRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.RestService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.EmailSenderService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.RoleService;
import com.proyectointegrador.proyecto_Integrador_CTD.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RestService restService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleRepository.findByName(RoleTypes.ROLE_ADMIN) == null) {
            Role role = new Role();
            role.setName(RoleTypes.ROLE_ADMIN);
            roleRepository.save(role);
        }
        if (roleRepository.findByName(RoleTypes.ROLE_USER) == null) {
            Role role = new Role();
            role.setName(RoleTypes.ROLE_USER);
            roleRepository.save(role);

        }

        /*try {

            String name = "Grego";
            String location = "Ciudad de México";
            String productName = "hotel la santa teresa";
            String startDate = "2020-01-01";
            String eta = "13:00";
            String bookingId = "12345";
            String urlImg1 = "https://bucket-grupo6-c1.s3.us-east-2.amazonaws.com/img/imgcategory/caba%C3%B1a.jpeg";
            String urlGoogleMaps = "https://www.google.com/maps/embed/v1/place?key=AIzaSyDqGQhTTknu4Bfw1R1gXgoSXoYiYejzA-M&q="+productName.replace(" ","+")+"+"+location.fullname+"&center="+lat+","+lng+"&zoom=15";

            StringBuilder sb = new StringBuilder();
            FileReader fr = new FileReader("src/main/resources/emailConfirmation.html");

            BufferedReader br = new BufferedReader(fr);
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
            sb.replace(sb.indexOf("{/bookingId/}"), sb.indexOf("{/bookingId/}") + "{/bookingId/}".length(), bookingId);
            sb.replace(sb.indexOf("{/urlImg1/}"), sb.indexOf("{/urlImg1/}") + "{/urlImg1/}".length(), urlImg1);
            sb.replace(sb.indexOf("{/urlGoogleMaps/}"), sb.indexOf("{/urlGoogleMaps/}") + "{/urlGoogleMaps/}".length(), urlGoogleMaps);

            System.out.println(sb.toString());
            FileWriter fw = new FileWriter("src/main/resources/emailConfirmationOut.html");
            fw.write(sb.toString());
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fatal Error");
        }
*/

    }
}
