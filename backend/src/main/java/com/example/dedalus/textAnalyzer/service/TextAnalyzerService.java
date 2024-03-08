package com.example.dedalus.textAnalyzer.service;

import com.example.dedalus.textAnalyzer.dto.AnalysisRequestDto;
import com.example.dedalus.textAnalyzer.dto.AnalysisResponseDto;
import com.example.dedalus.textAnalyzer.exceptions.InvalidInputException;
import com.example.dedalus.textAnalyzer.model.AnalysisType;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * The TextAnalyzerService class provides services for analyzing text.
 * It supports analysis based on different criteria, specifically focusing
 * on the frequency of vowels or consonants in the text.
 */
@Service
public class TextAnalyzerService {

	private static final String VOWELS = "AEIOU";
	private static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";

	
	/**
     * Analyzes the given text based on the specified analysis type.
     * The analysis can be for either vowels or consonants.
     *
     * @param requestDto The request containing the text to be analyzed and the analysis type.
     * @return An AnalysisResponseDto containing the results of the analysis.
     * @throws InvalidInputException if the request data is invalid.
     */
	public AnalysisResponseDto analyze(AnalysisRequestDto requestDto) {
		
		if (requestDto == null || requestDto.getTextToAnalyze() == null || requestDto.getType() == null) {
			throw new InvalidInputException("The analysis request or type cannot be null.");
		}

		String textToAnalyze = requestDto.getTextToAnalyze().toUpperCase();

		if (textToAnalyze.isEmpty()) {
			return new AnalysisResponseDto(new HashMap<>(), requestDto.getType());
		}

		Map<Character, Integer> frequencies = analyzeText(textToAnalyze, requestDto.getType());
		TextAnalysisResult result = new TextAnalysisResult(frequencies, requestDto.getType());
		return new AnalysisResponseDto(result.getCharacterFrequencies(), result.getAnalysisType());
	}

	
	/**
     * Helper method to analyze the frequency of characters in the text.
     * It can analyze either vowels or consonants based on the analysis type.
     *
     * @param input The text to be analyzed.
     * @param type The type of analysis (vowels or consonants).
     * @return A Map containing the frequency of each character in the text.
     */
	private Map<Character, Integer> analyzeText(String input, AnalysisType type) {

		String charactersToAnalyze = type == AnalysisType.VOWELS ? VOWELS : CONSONANTS;
		Map<Character, Integer> frequencies = new HashMap<>();

		for (char ch : input.toCharArray()) {
		    if (charactersToAnalyze.contains(String.valueOf(ch))) {
		        frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
		    }
		}

		return frequencies;
	}
}