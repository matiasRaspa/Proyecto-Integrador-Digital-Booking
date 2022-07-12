package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.jwt.AuthenticationRequest;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.jwt.AuthenticationResponse;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.UserDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.UserAlreadyRegisteredException;
import com.proyectointegrador.proyecto_Integrador_CTD.security.jwt.JwtTokenUtil;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/id={id}")

    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        logger.info("Searching user with id: %s" + (id));
        UserDto user = userService.findById(id);
        if (Objects.isNull(user)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
        try {
            logger.info("Saving user: %s" + (userDto));
            userService.registerUser(userDto);
            return new ResponseEntity<String>("User Register Ok", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving user: %s" + (userDto));
            throw new UserAlreadyRegisteredException("Error saving user: " + userDto);

        }
    }

    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authorizeRequests) {
        try {
            logger.info("Authenticating user: %s" + (authorizeRequests));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizeRequests.getUsername(), authorizeRequests.getPassword()));
        } catch (BadCredentialsException e) {
            logger.error("Error creating authentication token: %s" + (authorizeRequests));
            throw new BadCredentialsException("Bad Credentials");
        }
        final UserDetails userDetails = userService.loadUserByUsername(authorizeRequests.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        logger.info("Creating authentication token: %s" + (jwt));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        if (userService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all users");
            return ResponseEntity.ok(userService.findAll());
        }
    }


    @GetMapping("/confirm")
    public String enableUser(@RequestParam("token") String token) {
        logger.info("Enabling user with token: %s" + (token));
        userService.enableUser(token);
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>User Enable</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500,700\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<style>\n" +
                "    body{\n" +
                "         background: #f5f5f5;\n" +
                "         display: flex;\n" +
                "         flex-direction: column;\n" +
                "         align-items: center\n" +
                "\n" +
                "    }\n" +
                "    h1{\n" +
                "        margin-top: 30vh;\n" +
                "        font-family: 'Roboto', sans-serif;\n" +
                "        font-size: 45px;\n" +
                "        color: rgb(230, 149, 18);\n" +
                "    }\n" +
                "    p{\n" +
                "        font-family: 'Roboto', sans-serif;\n" +
                "        font-size: 20px;\n" +
                "        color: rgb(230, 149, 18);\n" +
                "\n" +
                "    }\n" +
                "    #button{\n" +
                "        font-family: 'Roboto', sans-serif;\n" +
                "        font-size: 20px;\n" +
                "        background-color: rgb(230, 149, 18);\n" +
                "        color: #fff;\n" +
                "        padding: 15px;\n" +
                "        border-radius: 10px;\n" +
                "        text-decoration: none;\n" +
                "    }\n" +
                "    a{\n" +
                "        text-decoration: none;\n" +
                "        color: #fff;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "</style>\n" +
                "<script>\n" +
                "   window.setTimeout(function(){\n" +
                "       var countDown = 4;\n" +
                "       var interval = setInterval(function(){\n" +
                "           countDown--;\n" +
                "           document.getElementById(\"countDown\").innerHTML = countDown;\n" +
                "           if(countDown == 0){\n" +
                "               window.location.href = \"http://grupo6-c1-621-front.s3-website.us-east-2.amazonaws.com\";\n" +
                "               clearInterval(interval);\n" +
                "           }\n" +
                "       }, 1000);\n" +
                "   }, 4000);\n" +
                "\n" +
                "</script>\n" +
                "<body>\n" +
                "    <h1>Felicitaciones tu usuario fue autenticado correctamente!</h1>\n" +
                "    <p>Ya podes comenzar a usar tu cuenta!</p>\n" +
                "    <p id=\"button\"><a href=\"http://grupo6-c1-621-front.s3-website.us-east-2.amazonaws.com\">volviendo al home en </a> <span id=\"countDown\">\n";

    }

}