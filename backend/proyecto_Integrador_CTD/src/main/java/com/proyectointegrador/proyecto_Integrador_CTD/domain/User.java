package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String email;
    @JsonIgnore
    private String password;
    private String phone;

    private String cityFrom;
    @OneToMany
    @JsonIgnore
    private Set<ReviewScore> reviewScores;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Booking> bookings;

    @JsonIgnore
    private Boolean enabled;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName().name().toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        //A chequear
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
