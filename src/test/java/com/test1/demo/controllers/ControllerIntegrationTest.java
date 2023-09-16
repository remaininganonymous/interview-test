package com.test1.demo.controllers;

import com.test1.demo.Services.FrequencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FrequencyService frequencyService;

    @BeforeEach
    void setUp() {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put('a', 5);
        frequencyMap.put('c', 4);
        frequencyMap.put('b', 1);
        when(frequencyService.calculateCharacterFrequency(anyString())).thenReturn(frequencyMap);
    }

    @Test
    void testCalculateFrequency() throws Exception {
        String input = "aaaaabcccc";
        mockMvc.perform(post("/api/calculate-frequency")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.a").value(5))
                .andExpect(jsonPath("$.c").value(4))
                .andExpect(jsonPath("$.b").value(1));
    }

    @Test
    void calculateFrequency_withNullInput_shouldReturnSingleKeyMap() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/calculate-frequency")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                //.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.e").value(1));

    }
}

