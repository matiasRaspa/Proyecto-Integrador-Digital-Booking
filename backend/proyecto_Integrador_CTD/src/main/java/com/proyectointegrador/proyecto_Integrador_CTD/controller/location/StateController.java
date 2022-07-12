package com.proyectointegrador.proyecto_Integrador_CTD.controller.location;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.StateDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location.StateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/states")
public class StateController {

    final static Logger logger = Logger.getLogger(StateController.class);

    @Autowired
    private StateService stateService;

    @GetMapping("/{id}")

    public ResponseEntity<StateDto> findById(@PathVariable Long id) {
        logger.info("Searching state with id: %s"+ (id));
        StateDto state = stateService.findById(id);
        if (Objects.isNull(state)) {
            throw new ResourceNotFoundException("State not found with id:" + id);
        }
        return ResponseEntity.ok(state);
    }

    @PostMapping()

    public ResponseEntity<StateDto> save(@Valid @RequestBody StateDto stateDto) {
        try {
            logger.info("Saving state: %s"+ (stateDto));
            return new ResponseEntity<StateDto>(stateService.save(stateDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving state: %s"+ (stateDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDto> updateById(@PathVariable Long id, @RequestBody StateDto state) {
        logger.info("Updating state with id: %s"+ (id));
        StateDto actualizado = stateService.updateById(id, state);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting state with id: %s"+ (id));
        stateService.deleteById(id);
        return ResponseEntity.ok("State deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<StateDto>> findAll() {
        if (stateService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all states");
            return ResponseEntity.ok(stateService.findAll());
        }
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<StateDto>> saveAll(@RequestBody List<StateDto> states) {
        try {
            logger.info("Saving all states: %s"+ (states));
            return new ResponseEntity<List<StateDto>>(stateService.saveAll(states), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving all states: %s"+ (states));
            return ResponseEntity.badRequest().build();
        }
    }
}
