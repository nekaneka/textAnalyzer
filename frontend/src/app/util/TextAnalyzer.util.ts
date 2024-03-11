import { CharacterFrequencies } from "../dto/AnalyzedTextResult";

export class TextAnalysisUtil {

    // Static properties for vowels, consonants, etc..
    public static vowels = "AEIOU";
    public static consonants = "BCDFGHJKLMNPQRSTVWXYZ";
    public static numbers = "0123456789";


    // Static method for analyzing the frequency of specified characters in a given text.
    static analyzeText(text: string, characterList: String) {

      const frequencies = new Map<string, number>();
  
      for (const ch of text) {
        const key = ch.toUpperCase();
        if (characterList.includes(key)) {
            const count = frequencies.get(key) || 0;
            frequencies.set(key, count + 1);
        }
      }
  
        return this.mapToCharacterFrequencies(frequencies);
    }
  
    // Static method to convert a Map of string-number pairs to CharacterFrequencies.
    static mapToCharacterFrequencies(map: Map<string, number>): CharacterFrequencies {

      const frequencies: CharacterFrequencies = {};

      map.forEach((value, key) => {
        frequencies[key] = value;
      });
      
      return frequencies;
    }  
  
}