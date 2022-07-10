package com.zenan.steepedbackend.controllers;

import com.zenan.steepedbackend.entities.Tea;
import com.zenan.steepedbackend.repositories.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TeaController {
    @Autowired
    private TeaRepository teaRepository;

    // get teas
    @GetMapping("teas")
    public List<Tea> getAllTeas() {
        return this.teaRepository.findAll();
    }

    // get tea by id
    @GetMapping("teas/{teaId}")
    public ResponseEntity<Tea> getTeaById(@PathVariable int teaId)
            throws InvalidConfigurationPropertyValueException {
        Tea tea = teaRepository.findById(teaId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Tea", teaId, "Tea id not found"));
        return ResponseEntity.ok(tea);
    }

    // save tea
    @PostMapping("teas")
    public Tea createTea(@RequestBody Tea tea) {
        return this.teaRepository.save(tea);
    }

    @PutMapping("teas/{teaId}")
    public ResponseEntity<Tea> updateTea(@PathVariable int teaId,
                                         @RequestBody Tea teaDetails) {
        Tea tea = teaRepository.findById(teaId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Tea", teaId, "Tea id not found"));
        tea.replaceFields(teaDetails);
        return ResponseEntity.ok(this.teaRepository.save(tea));
    }

    @DeleteMapping("teas/{teaId}")
    public Map<String, Boolean> deleteTea(@PathVariable int teaId) {
        Tea tea = teaRepository.findById(teaId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Tea", teaId, "Tea id not found"));
        this.teaRepository.delete(tea);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
