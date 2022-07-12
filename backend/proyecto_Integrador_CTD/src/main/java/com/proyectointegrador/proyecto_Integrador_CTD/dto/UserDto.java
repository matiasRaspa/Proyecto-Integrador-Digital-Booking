package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Role;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.City;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    private String cityFrom;
    private RoleDto role;

    private Boolean enabled;

    public UserDto() {
    }
}
