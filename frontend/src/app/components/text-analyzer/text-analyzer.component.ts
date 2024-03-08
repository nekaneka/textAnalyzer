import { Component } from '@angular/core';
import { TextAnalyzerService } from '../../service/text-analyzer.service';
import { AnalyzeType } from '../../enum/AnalyzeType.enum';
import { ThemePalette } from '@angular/material/core';
import { CharacterFrequencies } from '../../dto/AnalyzedTextResult';
import { TextAnalysisUtil } from '../../util/TextAnalyzer.util';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-text-analyzer',
  templateUrl: './text-analyzer.component.html',
  styleUrl: './text-analyzer.component.css'
})
export class TextAnalyzerComponent {

  public textToAnalyze: string = ''; 
  public analyzeInBackend = false;
  public color: ThemePalette = 'primary';
  public analyzeType: AnalyzeType = AnalyzeType.VOWELS; 

  public types: string[] = [AnalyzeType.VOWELS, AnalyzeType.CONSONANTS];
  public listItems: CharacterFrequencies = {};

  constructor(
    private textAnalyzerService: TextAnalyzerService,
    private snackBar: MatSnackBar
  ) {}

  analyzeText() {
    this.clearListItems();
    if(this.analyzeInBackend) this.analyzeTextInBackend();
    else this.analyzeInFrontend();
  }

  resetFilters() {
    this.clearListItems();
    this.textToAnalyze = '';
    this.analyzeType = AnalyzeType.VOWELS;
    this.analyzeInBackend = false;
  }

  private clearListItems() {
    this.listItems = {};
  }

  private analyzeTextInBackend() {
    this.textAnalyzerService.analyzeText(this.textToAnalyze, this.analyzeType).subscribe(
        (result) => {
          this.listItems = result.characterFrequencies;
        },
        (error) => {
          this.snackBar.open('Please run backend!', 'close');
        }
    );
  }

  private analyzeInFrontend() {
    switch (this.analyzeType) {
        case AnalyzeType.VOWELS:
          this.listItems = TextAnalysisUtil.analyzeText(this.textToAnalyze, TextAnalysisUtil.vowels);
          break;
        case AnalyzeType.CONSONANTS:
          this.listItems = TextAnalysisUtil.analyzeText(this.textToAnalyze, TextAnalysisUtil.consonants);
          break;
        default:
          this.clearListItems();
    }
  }

  deleteTextToAnalyze() {
    this.textToAnalyze = '';
    this.clearListItems();
  }

  get listIsNotEmpty() {
    return Object.keys(this.listItems).length;
  }
}
