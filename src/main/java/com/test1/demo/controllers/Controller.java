package com.test1.demo.controllers;

import com.test1.demo.Services.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private FrequencyService frequencyService;

    @PostMapping("/calculate-frequency")
    public ResponseEntity<Map<Character, Integer>> calculateFrequency(@RequestBody(required = false) String input) {
        if (input == null || input.isEmpty()) {
            Map<Character, Integer> errorResponse = new HashMap<>();
            errorResponse.put('e', 1);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Map<Character, Integer> frequencyMap = frequencyService.calculateCharacterFrequency(input);
            return new ResponseEntity<>(frequencyMap, HttpStatus.CREATED);
        } catch (HttpMessageNotReadableException e) {
            System.out.println("An error occurred: " + e.toString());
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
    }

}
//повторный коммит чтоб исправить описание