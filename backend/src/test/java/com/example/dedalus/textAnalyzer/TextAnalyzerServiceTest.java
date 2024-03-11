package com.example.dedalus.textAnalyzer;

import com.example.dedalus.textAnalyzer.dto.AnalysisRequestDto;
import com.example.dedalus.textAnalyzer.dto.AnalysisResponseDto;
import com.example.dedalus.textAnalyzer.exceptions.InvalidInputException;
import com.example.dedalus.textAnalyzer.model.AnalysisType;
import com.example.dedalus.textAnalyzer.service.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TextAnalyzerServiceTest {

    @InjectMocks
    private TextAnalyzerService textAnalyzerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAnalyzeVowels_Success() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("hello", AnalysisType.VOWELS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);

        assertNotNull(responseDto);
        assertEquals(AnalysisType.VOWELS, responseDto.getType());
        
        // 'hello' is analyzed as 'HELLO'
        Map<Character, Integer> frequencies = responseDto.getCharacterFrequencies();
        assertEquals(1, frequencies.get('E')); 
        assertEquals(1, frequencies.get('O')); 
    }

    @Test
    public void testAnalyzeConsonants_Success() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("hello", AnalysisType.CONSONANTS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);

        assertNotNull(responseDto);
        assertEquals(AnalysisType.CONSONANTS, responseDto.getType());
        Map<Character, Integer> frequencies = responseDto.getCharacterFrequencies();
        assertEquals(1, frequencies.get('H'));
        assertEquals(2, frequencies.get('L'));
    }

    @Test
    public void testAnalyzeEmptyText() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("", AnalysisType.VOWELS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);

        assertTrue(responseDto.getCharacterFrequencies().isEmpty());
    }

    @Test
    public void testAnalyzeNullInput() {
        assertThrows(InvalidInputException.class, () -> textAnalyzerService.analyze(null));
    }

    @Test
    public void testAnalyzeOnlyVowels() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("aeiiiou", AnalysisType.VOWELS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);
        assertNotNull(responseDto);
        assertEquals(AnalysisType.VOWELS, responseDto.getType());
        Map<Character, Integer> frequencies = responseDto.getCharacterFrequencies();
        assertEquals(1, frequencies.get('A'));
        assertEquals(1, frequencies.get('E'));
        assertEquals(3, frequencies.get('I'));
        assertEquals(1, frequencies.get('O'));
        assertEquals(1, frequencies.get('U'));
    }

    @Test
    public void testAnalyzeOnlyConsonants() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("bcdfg", AnalysisType.CONSONANTS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);
        assertNotNull(responseDto);
        assertEquals(AnalysisType.CONSONANTS, responseDto.getType());
        Map<Character, Integer> frequencies = responseDto.getCharacterFrequencies();
        assertEquals(1, frequencies.get('B'));
        assertEquals(1, frequencies.get('C'));
        assertEquals(1, frequencies.get('D'));
        assertEquals(1, frequencies.get('F'));
        assertEquals(1, frequencies.get('G'));
    }

    @Test
    public void testAnalyzeMixedCharacters() {
        AnalysisRequestDto requestDto = new AnalysisRequestDto("abc123!@", AnalysisType.CONSONANTS);
        AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);
        assertNotNull(responseDto);
        assertEquals(AnalysisType.CONSONANTS, responseDto.getType());
        Map<Character, Integer> frequencies = responseDto.getCharacterFrequencies();
        assertEquals(1, frequencies.get('B'));
        assertEquals(1, frequencies.get('C'));
    }
}
