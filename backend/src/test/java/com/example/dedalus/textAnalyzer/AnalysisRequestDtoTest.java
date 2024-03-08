package com.example.dedalus.textAnalyzer;

import com.example.dedalus.textAnalyzer.dto.AnalysisRequestDto;
import com.example.dedalus.textAnalyzer.model.AnalysisType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnalysisRequestDtoTest {

    @Test
    public void testDtoFunctionality() {
        String textToAnalyze = "test";
        AnalysisType type = AnalysisType.VOWELS;
        AnalysisRequestDto dto = new AnalysisRequestDto(textToAnalyze, type);

        assertEquals(textToAnalyze, dto.getTextToAnalyze());
        assertEquals(type, dto.getType());

        // Test setters
        String newTextToAnalyze = "new test";
        AnalysisType newType = AnalysisType.CONSONANTS;
        dto.setTextToAnalyze(newTextToAnalyze);
        dto.setType(newType);

        assertEquals(newTextToAnalyze, dto.getTextToAnalyze());
        assertEquals(newType, dto.getType());
    }
}
