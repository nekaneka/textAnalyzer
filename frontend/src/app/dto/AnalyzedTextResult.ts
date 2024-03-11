import { AnalyzeType } from "../enum/AnalyzeType.enum";

/**
 * Represents a mapping from characters to their frequencies.
 * Object where each key is a character (string)
 * Value is the number of times this character appears (number).
 */
export interface CharacterFrequencies {
    [character: string]: number; // Key: Character, Value: Frequency count
}

/**
 * Represents the result of analyzing a text.
 * This class includes both the frequencies of specific characters in the text
 * and the type of analysis that was performed.
 */
export class AnalyzedTextResult {
    /**
     * Mapping of characters and their frequencies in the analyzed text.
     * This property is required (non-optional) and is initialized dynamically.
     */
    characterFrequencies!: CharacterFrequencies;

    /**
     * The type of analysis that was performed.
     * This uses the AnalyzeType enum to specify the kind of text analysis,
     * such as analyzing vowels or consonants
     * This property is required (non-optional) and is initialized dynamically.
     */
    type!: AnalyzeType;
}
