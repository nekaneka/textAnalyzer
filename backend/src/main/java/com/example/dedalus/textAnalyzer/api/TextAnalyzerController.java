package com.example.dedalus.textAnalyzer.api;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dedalus.textAnalyzer.dto.AnalysisRequestDto;
import com.example.dedalus.textAnalyzer.dto.AnalysisResponseDto;
import com.example.dedalus.textAnalyzer.exceptions.InvalidInputException;
import com.example.dedalus.textAnalyzer.model.AnalysisType;
import com.example.dedalus.textAnalyzer.service.TextAnalyzerService;

/**
 * The TextAnalyzerController class is a RESTful controller handling text analysis requests.
 * It defines endpoints for analyzing text based on provided criteria (vowels or consonants).
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    /**
     * Constructs a TextAnalyzerController with the specified TextAnalyzerService.
     * 
     * @param textAnalyzerService The service that will handle text analysis logic.
     */
    public TextAnalyzerController(TextAnalyzerService textAnalyzerService) {
        this.textAnalyzerService = textAnalyzerService;
    }
    
    /**
     * Endpoint for analyzing text. It processes a GET request, performs text analysis,
     * and returns the analysis results.
     *
     * @param textToAnalyze The text to be analyzed.
     * @param type The type of analysis to perform (VOWELS or CONSONANTS).
     * @return A ResponseEntity containing the analysis results or an error message.
     */
    @GetMapping("/analyze")
    public ResponseEntity<?> analyzeText(@RequestParam("textToAnalyze") String textToAnalyze, @RequestParam("type") String type) {

        try {
        	
            if (textToAnalyze.isEmpty() || textToAnalyze.equals(null)) {
                return ResponseEntity.badRequest().body("Text to analyze must not be empty");
            }

            AnalysisType analysisType;
            try {
                analysisType = AnalysisType.valueOf(type.toUpperCase());
                
                analysisType = type.equals(AnalysisType.VOWELS.toString()) 
                        ? AnalysisType.VOWELS 
                        : AnalysisType.CONSONANTS;
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid analysis type: " + type);
            }
            
            AnalysisRequestDto requestDto = new AnalysisRequestDto(textToAnalyze, analysisType);
            AnalysisResponseDto responseDto = textAnalyzerService.analyze(requestDto);

            return ResponseEntity.ok(responseDto);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
