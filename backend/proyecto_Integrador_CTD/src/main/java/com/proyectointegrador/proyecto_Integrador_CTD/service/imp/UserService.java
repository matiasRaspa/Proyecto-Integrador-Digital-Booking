package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.controller.UserController;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.ConfirmationToken;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.User;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.UserDto;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.RoleRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.UserRepository;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {
    final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + email));
    }


    public void registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + userDto.getEmail());
        } else {
            User user = mapToEntity(userDto);
            logger.info("saving user: " + user);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setRole(roleRepository.findByName(RoleTypes.valueOf("ROLE_USER")));
            logger.info("user role: " + user.getRole());
            String newPass = bCryptPasswordEncoder.encode(userDto.getPassword());
            user.setPassword(newPass);
            user.setEnabled(false);
            logger.info("user password: " + user.getPassword());


            User newUser = userRepository.save(user);


            try {
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();
                ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), newUser);
                logger.info("confirmation token created successfully : " + confirmationToken.getToken());
                confirmationTokenService.saveConfirmationToken(confirmationToken);
                String Url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/confirm").
                        queryParam("token", token).toUriString();
                emailSenderService.sendEmail(newUser.getEmail(), "Bienvenid@ a DG-Booking ", emailSenderService.createEnableUserTemplate(newUser.getName() + " " + newUser.getLastName(), Url));
            } catch (Exception e) {
                logger.error("Error sending email to: " + user.getEmail());
            }
        }
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserDto.class)).orElse(null);
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public void enableUser(String token) {
        if (userRepository.findByEmail(confirmationTokenService.findByToken(token).get().getUser().getEmail()).isPresent()) {
            userRepository.enableUser(token);
            confirmationTokenService.setConfirmationDate(token);
        } else {
            throw new RuntimeException("User not found with token: " + token);
        }
    }

    public UserDto findByEmail(String username) {
        return userRepository.findByEmail(username).map(user -> modelMapper.map(user, UserDto.class)).orElse(null);
    }

    //-- Mapper---
    public UserDto mapToDTO(UserDto userDto) {
        return modelMapper.map(userDto, UserDto.class);
    }

    public User mapToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
