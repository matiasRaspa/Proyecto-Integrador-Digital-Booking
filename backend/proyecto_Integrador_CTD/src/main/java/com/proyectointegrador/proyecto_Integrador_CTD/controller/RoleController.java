package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.RoleDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.RoleRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RoleDto>> findAll() {
        if (roleService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(roleService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
       if (roleService.findById(id) == null) {
           throw new ResourceNotFoundException("Role not found with id " + id);
       } else {
           return ResponseEntity.ok(roleService.findById(id));
       }
    }

}
