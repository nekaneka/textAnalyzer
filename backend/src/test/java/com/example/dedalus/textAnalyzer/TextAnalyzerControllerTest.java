package com.example.dedalus.textAnalyzer;

import com.example.dedalus.textAnalyzer.api.TextAnalyzerController;
import com.example.dedalus.textAnalyzer.dto.AnalysisRequestDto;
import com.example.dedalus.textAnalyzer.dto.AnalysisResponseDto;
import com.example.dedalus.textAnalyzer.exceptions.InvalidInputException;
import com.example.dedalus.textAnalyzer.model.AnalysisType;
import com.example.dedalus.textAnalyzer.service.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TextAnalyzerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TextAnalyzerService textAnalyzerService;

    @InjectMocks
    private TextAnalyzerController textAnalyzerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(textAnalyzerController).build();
    }

    @Test
    public void testAnalyzeEndpoint_Vowels() throws Exception {
        AnalysisResponseDto mockResponse = new AnalysisResponseDto(new HashMap<>() {{
            put('E', 1);
            put('O', 1);
        }}, AnalysisType.VOWELS);
        given(textAnalyzerService.analyze(any(AnalysisRequestDto.class))).willReturn(mockResponse);

        mockMvc.perform(get("/api/analyze")
                .param("textToAnalyze", "hello")
                .param("type", "VOWELS")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value("VOWELS"))
                .andExpect(jsonPath("$.characterFrequencies.E").value(1))
                .andExpect(jsonPath("$.characterFrequencies.O").value(1));
    }

    @Test
    public void testAnalyzeEndpoint_Consonants() throws Exception {
        AnalysisResponseDto mockResponse = new AnalysisResponseDto(new HashMap<>() {{
            put('H', 1);
            put('L', 2);
        }}, AnalysisType.CONSONANTS);
        given(textAnalyzerService.analyze(any(AnalysisRequestDto.class))).willReturn(mockResponse);

        mockMvc.perform(get("/api/analyze")
                .param("textToAnalyze", "hello")
                .param("type", "CONSONANTS")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value("CONSONANTS"))
                .andExpect(jsonPath("$.characterFrequencies.H").value(1))
                .andExpect(jsonPath("$.characterFrequencies.L").value(2));
    }

    @Test
    public void testAnalyzeEndpoint_InvalidRequest() throws Exception {
        given(textAnalyzerService.analyze(any(AnalysisRequestDto.class)))
                .willThrow(new InvalidInputException("Invalid input"));

        mockMvc.perform(get("/api/analyze")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAnalyzeEndpoint_WithSpecialCharacters() throws Exception {
        AnalysisResponseDto mockResponse = new AnalysisResponseDto(new HashMap<>(), AnalysisType.CONSONANTS);
        given(textAnalyzerService.analyze(any(AnalysisRequestDto.class))).willReturn(mockResponse);

        mockMvc.perform(get("/api/analyze")
                .param("textToAnalyze", "!@#$%^&*")
                .param("type", "CONSONANTS")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.characterFrequencies").isEmpty());
    }

    @Test
    public void testAnalyzeEndpoint_WithLongText() throws Exception {
        AnalysisResponseDto mockResponse = new AnalysisResponseDto(new HashMap<>(), AnalysisType.VOWELS);
        given(textAnalyzerService.analyze(any(AnalysisRequestDto.class))).willReturn(mockResponse);

        String longText = "This is a very long text string to test the text analyzer controller's ability to handle large inputs.";
        mockMvc.perform(get("/api/analyze")
                .param("textToAnalyze", longText)
                .param("type", "VOWELS")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAnalyzeEndpoint_EmptyTextParameter() throws Exception {
        mockMvc.perform(get("/api/analyze")
                .param("textToAnalyze", "")
                .param("type", "VOWELS")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }




    @Test
    public void testAnalyzeEndpoint_IncorrectHttpMethod() throws Exception {
        mockMvc.perform(post("/api/analyze") 
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

}
