import { AnalyzeType } from "../enum/AnalyzeType.enum";

export interface CharacterFrequencies {
    [character: string]: number;
  }
  
export class AnalyzedTextResult {
    characterFrequencies!: CharacterFrequencies;
    type!: AnalyzeType;
  }
  