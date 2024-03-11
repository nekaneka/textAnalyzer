import { Component } from '@angular/core';
import { TextAnalyzerService } from '../../service/text-analyzer.service';
import { AnalyzeType } from '../../enum/AnalyzeType.enum';
import { ThemePalette } from '@angular/material/core';
import { CharacterFrequencies } from '../../dto/AnalyzedTextResult';
import { TextAnalysisUtil } from '../../util/TextAnalyzer.util';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-text-analyzer',
  templateUrl: './text-analyzer.component.html',
  styleUrls: ['./text-analyzer.component.css']
})
export class TextAnalyzerComponent {
  // Component state variables
  public textToAnalyze: string = ''; // Stores the text input by the user
  public analyzeInBackend = false; // Flag to determine where the analysis should happen in the backend
  public color: ThemePalette = 'primary'; // Color theme for the UI components
  public analyzeType: AnalyzeType = AnalyzeType.VOWELS; // Type of analysis to be performed
  public types: string[] = [AnalyzeType.VOWELS, AnalyzeType.CONSONANTS]; // Available analysis types
  public listItems: CharacterFrequencies = {}; // Stores the result of the text analysis

  // Constructor injects necessary services
  constructor(
    private textAnalyzerService: TextAnalyzerService, // Service for backend text analysis
    private snackBar: MatSnackBar // Service to display snack-bar notifications
  ) {}

  // Method to handle text analysis
  analyzeText() {
    this.snackBar.dismiss();
    this.clearListItems(); // Clear previous results
    if (this.analyzeInBackend) this.analyzeTextInBackend(); // Analyze using backend if flag is true
    else this.analyzeInFrontend(); // Otherwise, analyze on the frontend
  }

  // Method to reset all filters and inputs
  resetFilters() {
    this.snackBar.dismiss();
    this.clearListItems();
    this.deleteTextToAnalyze();
    this.analyzeType = AnalyzeType.VOWELS;
    this.analyzeInBackend = false;
  }

  // Helper method to clear analysis results
  private clearListItems() {
    this.listItems = {};
  }

  private analyzeTextInBackend() {
    this.textAnalyzerService.analyzeText(this.textToAnalyze, this.analyzeType).subscribe({
      next: (result) => {
        this.listItems = result.characterFrequencies; // Update results from backend response
      },
      error: (errorResponse) => {
        let errorMessage: string;
        errorMessage = errorResponse.toString();
        
        if (errorMessage === 'Text to analyze must not be empty') {
          this.snackBar.open(errorMessage, 'Close');
        } else {
          this.snackBar.open('Please run backend!', 'Close');
        }
      }
    });
  }
  

  // Method for frontend analysis
  private analyzeInFrontend() {
    // Decide the type of analysis based on user selection
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

  // Method to delete text input by the user
  deleteTextToAnalyze() {
    this.textToAnalyze = '';
  }

  // Getter to check if the analysis results list is not empty
  get listIsNotEmpty() {
    return Object.keys(this.listItems).length;
  }
}
