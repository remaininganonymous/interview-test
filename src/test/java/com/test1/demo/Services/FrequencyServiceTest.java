package com.test1.demo.Services;

import com.test1.demo.Services.FrequencyService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyServiceTest {

    private final FrequencyService frequencyService = new FrequencyService();

    @Test
    void testCalculateCharacterFrequency() {
        String input = "aaaaabcccc";
        Map<Character, Integer> result = frequencyService.calculateCharacterFrequency(input);
        assertEquals(5, (int) result.get('a'));
        assertEquals(4, (int) result.get('c'));
        assertEquals(1, (int) result.get('b'));
    }
}
