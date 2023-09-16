package com.test1.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test1.demo.Services.FrequencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(Controller.class)
class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FrequencyService frequencyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Mock the behavior of FrequencyService
        Map<Character, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put('a', 5);
        frequencyMap.put('c', 4);
        frequencyMap.put('b', 1);
        when(frequencyService.calculateCharacterFrequency(anyString())).thenReturn(frequencyMap);
    }

    @Test
    void testCalculateFrequency() throws Exception {
        String input = "aaaaabcccc";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate-frequency")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.a").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.c").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.b").value(1));
    }
}