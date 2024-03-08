package com.example.dedalus.textAnalyzer.dto;

import com.example.dedalus.textAnalyzer.model.AnalysisType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * The AnalysisRequestDto class is a Data Transfer Object (DTO) for handling requests
 * in the text analysis service. It encapsulates the data required for analyzing text:
 * the type of analysis to be performed and the text to be analyzed.
 */
public class AnalysisRequestDto {
	
	@NotNull(message = "Analysis type must not be null")
    private AnalysisType type;

    @NotBlank(message = "Text to analyze must not be empty")
    private String textToAnalyze;

    /**
     * Constructs a new AnalysisRequestDto with specified text and analysis type.
     *
     * @param textToAnalyze The text that needs to be analyzed.
     * @param type The type of analysis to perform (e.g., VOWELS or CONSONANTS).
     */
    public AnalysisRequestDto(String textToAnalyze, AnalysisType type) {
        this.type = type;
        this.textToAnalyze = textToAnalyze;
    }

    /**
     * Returns the type of analysis.
     *
     * @return The analysis type.
     */
    public AnalysisType getType() {
        return type;
    }

    /**
     * Sets the type of analysis.
     *
     * @param type The analysis type to set.
     */
    public void setType(AnalysisType type) {
        this.type = type;
    }

    /**
     * Returns the text to be analyzed.
     *
     * @return The text for analysis.
     */
    public String getTextToAnalyze() {
        return textToAnalyze;
    }

    /**
     * Sets the text to be analyzed.
     *
     * @param textToAnalyze The text to set for analysis.
     */
    public void setTextToAnalyze(String textToAnalyze) {
        this.textToAnalyze = textToAnalyze;
    }
}
