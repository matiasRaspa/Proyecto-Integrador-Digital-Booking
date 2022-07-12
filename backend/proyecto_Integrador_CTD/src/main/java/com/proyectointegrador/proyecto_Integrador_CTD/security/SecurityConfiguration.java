package com.proyectointegrador.proyecto_Integrador_CTD.security;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;
import com.proyectointegrador.proyecto_Integrador_CTD.security.jwt.JwtRequestFilter;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() /*.
                authorizeRequests().antMatchers("/bookings/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/products/**").hasAuthority("ROLE_ADMIN")
                .and()

                .authorizeRequests().antMatchers(HttpMethod.POST, "/features/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/politics/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ROLE_ADMIN")
                .and()*/
                .authorizeRequests().antMatchers(HttpMethod.GET, "/users/id={id}/**").hasAuthority(RoleTypes.ROLE_ADMIN.name())
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/bookings/**").hasAnyAuthority(RoleTypes.ROLE_USER.name(), RoleTypes.ROLE_ADMIN.name())
                .and()
                .authorizeRequests().antMatchers(/*"/users/**"*/"/**").permitAll().anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
