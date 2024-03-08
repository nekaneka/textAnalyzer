package com.example.dedalus.textAnalyzer.dto;

import java.util.Map;
import com.example.dedalus.textAnalyzer.model.AnalysisType;


/**
 * The AnalysisResponseDto class is a Data Transfer Object (DTO) used for transferring
 * the results of a text analysis operation. It encapsulates the results of the analysis
 * in the form of character frequencies, along with the type of analysis that was performed.
 */
public class AnalysisResponseDto {
    private Map<Character, Integer> characterFrequencies;
    private AnalysisType type;

    
    /**
     * Constructs a new AnalysisResponseDto with the specified character frequencies and analysis type.
     *
     * @param characterFrequencies A Map<Character, Integer> representing the frequency of each character.
     * @param type The type of analysis that was performed.
     */
    public AnalysisResponseDto(Map<Character, Integer> characterFrequencies, AnalysisType type) {
        this.characterFrequencies = characterFrequencies;
        this.type = type;
    }

    
    /**
     * Retrieves the character frequencies from the analysis result.
     *
     * @return A Map<Character, Integer> representing the frequency of each character.
     */
    public Map<Character, Integer> getCharacterFrequencies() {
        return characterFrequencies;
    }

    
    /**
     * Sets the character frequencies for this response.
     *
     * @param characterFrequencies A Map<Character, Integer> representing the frequency of each character.
     */
    public void setCharacterFrequencies(Map<Character, Integer> characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
    }

    
    /**
     * Retrieves the type of analysis that was performed.
     *
     * @return The analysis type.
     */
    public AnalysisType getType() {
        return type;
    }

    
    /**
     * Sets the type of analysis that was performed.
     *
     * @param type The analysis type to set.
     */
    public void setType(AnalysisType type) {
        this.type = type;
    }
    
    
    /**
     * Prints the character frequencies to the standard output.
     * This method iterates over the entries of the characterFrequencies map
     * and prints each character and its corresponding frequency.
     */
    public void printCharacterFrequencies() {
        for (Map.Entry<Character, Integer> entry : characterFrequencies.entrySet()) {
            System.out.println("Character: " + entry.getKey() + ", Frequency: " + entry.getValue());
        }
    }
}
