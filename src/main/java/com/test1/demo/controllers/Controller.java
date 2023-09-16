package com.test1.demo.controllers;

import com.test1.demo.Services.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private FrequencyService frequencyService;

    @PostMapping("/calculate-frequency")
    public ResponseEntity<Map<Character, Integer>> calculateFrequency(@RequestBody String input) {
        Map<Character, Integer> frequencyMap = frequencyService.calculateCharacterFrequency(input);
        return new ResponseEntity<>(frequencyMap, HttpStatus.CREATED);
    }
}
