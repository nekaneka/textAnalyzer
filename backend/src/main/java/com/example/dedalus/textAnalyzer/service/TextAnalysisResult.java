package com.example.dedalus.textAnalyzer.service;

import com.example.dedalus.textAnalyzer.model.AnalysisType;
import java.util.Map;

/**
 * The TextAnalysisResult class encapsulates the results of a text analysis operation.
 * It holds the frequency of each character in the text and the type of analysis performed.
 */
public class TextAnalysisResult {

    /**
     * A map containing the frequency of each character in the analyzed text.
     * Each map entry consists of a character and its corresponding frequency count.
     */
    private final Map<Character, Integer> characterFrequencies;

    /**
     * The type of analysis that was conducted, as defined in the AnalysisType enum.
     * This can be either VOWELS or CONSONANTS.
     */
    private final AnalysisType analysisType;

    /**
     * Constructs a new TextAnalysisResult with the given character frequencies and analysis type.
     *
     * @param characterFrequencies A Map<Character, Integer> representing the frequency of each character.
     * @param analysisType The type of analysis performed, specified by the AnalysisType enum.
     */
    public TextAnalysisResult(Map<Character, Integer> characterFrequencies, AnalysisType analysisType) {
        this.characterFrequencies = characterFrequencies;
        this.analysisType = analysisType;
    }

    /**
     * Retrieves the character frequencies from the analysis result.
     *
     * @return A Map<Character, Integer> representing the frequency of each character in the analyzed text.
     */
    public Map<Character, Integer> getCharacterFrequencies() {
        return characterFrequencies;
    }

    /**
     * Retrieves the type of analysis that was performed.
     *
     * @return The analysis type as specified in the AnalysisType enum.
     */
    public AnalysisType getAnalysisType() {
        return analysisType;
    }
}
