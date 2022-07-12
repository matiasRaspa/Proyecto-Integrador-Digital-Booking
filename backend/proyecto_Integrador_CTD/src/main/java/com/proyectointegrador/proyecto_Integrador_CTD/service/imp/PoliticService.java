package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Politic;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.PoliticDto;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.PoliticRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IPoliticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticService implements IPoliticService<PoliticDto> {

    @Autowired
    private PoliticRepository politicRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PoliticDto save(PoliticDto politic) {
        Politic polticEntity = mapToEntity(politic);
        polticEntity = politicRepository.save(polticEntity);
        return mapToDTO(polticEntity);
    }

    @Override
    public PoliticDto findById(Long id) {
        return politicRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public PoliticDto updateById(Long id, PoliticDto politic) {
        if (politicRepository.findById(id).isPresent()) {
            Politic politicEntity = mapToEntity(politic);
            politicEntity.setId(id);
            Politic newPolitic = politicRepository.save(politicEntity);
            return mapToDTO(newPolitic);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if (politicRepository.findById(id).isPresent()) {
            politicRepository.deleteById(id);
        }
    }

    @Override
    public List<PoliticDto> findAll() {
        return politicRepository.findAll().stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<PoliticDto> saveAll(List<PoliticDto> politic) {
        List<Politic> politicEntity = politic.stream().map(this::mapToEntity).collect(java.util.stream.Collectors.toList());
        return politicRepository.saveAll(politicEntity).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }



  /*  @Override
    public List<PoliticDto> findAllByProductId(Long id) {
        return politicRepository.findAllByProductId(id).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }*/

    // ---- mappers ----

    private PoliticDto mapToDTO(Politic politic) {
        return modelMapper.map(politic, PoliticDto.class);
    }

    private Politic mapToEntity(PoliticDto politicDto) {
        return modelMapper.map(politicDto, Politic.class);
    }
}
