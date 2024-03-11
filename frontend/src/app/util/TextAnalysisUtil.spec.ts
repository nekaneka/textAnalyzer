import { TextAnalysisUtil } from "./TextAnalyzer.util";

describe('TextAnalysisUtil', () => {
  it('should correctly analyze text for vowels', () => {
    const text = 'Hello World';
    const expectedFrequencies = { E: 1, O: 2 };
    expect(TextAnalysisUtil.analyzeText(text, TextAnalysisUtil.vowels)).toEqual(expectedFrequencies);
  });


  it('should convert Map to CharacterFrequencies correctly', () => {
    const map = new Map([['A', 2], ['B', 3]]);
    const expectedFrequencies = { A: 2, B: 3 };
    expect(TextAnalysisUtil.mapToCharacterFrequencies(map)).toEqual(expectedFrequencies);
  });


  it('should return an empty object for an empty string', () => {
    const result = TextAnalysisUtil.analyzeText('', TextAnalysisUtil.vowels);
    expect(result).toEqual({});
  });


  it('should return an empty object if no specified characters are found', () => {
    const text = 'xyz12345"§$%';
    const result = TextAnalysisUtil.analyzeText(text, TextAnalysisUtil.vowels);
    expect(result).toEqual({});
  });
  
  
});
