package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Role;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.RoleDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.RoleRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IRoleService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService<RoleDto> {

    private final static Logger logger = Logger.getLogger(RoleService.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RoleDto save(RoleDto role) {
        Role roleEntity = mapToEntity(role);
        roleEntity = roleRepository.save(roleEntity);
        return mapToDto(roleEntity);
    }

    @Override
    public RoleDto findById(Long id) {
        Role roleEntity = roleRepository.findById(id).orElse(null);
        return mapToDto(roleEntity);
    }

    @Override
    public RoleDto updateById(Long id, RoleDto role) {
        if (roleRepository.findById(id).isPresent()) {
            Role roleEntity = mapToEntity(role);
            roleEntity.setId(id);
            roleEntity = roleRepository.save(roleEntity);
            return mapToDto(roleEntity);
        } else {
            throw new ResourceNotFoundException("Role not found with id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (roleRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Role not found with id " + id);
        } else {
            roleRepository.deleteById(id);
        }
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roleEntityList = roleRepository.findAll();
        return roleEntityList.stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public RoleDto findByName(RoleTypes name) {
        Role roleEntity = roleRepository.findByName(name);
        return mapToDto(roleEntity);
    }

    //--- Model Mapper ------------------
    public RoleDto mapToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    public Role mapToEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }
}
