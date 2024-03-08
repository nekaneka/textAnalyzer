import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnalyzedTextResult } from '../dto/AnalyzedTextResult';
import { Observable } from 'rxjs';
import { AnalyzeType } from '../enum/AnalyzeType.enum';

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzerService {

  private readonly apiUrl = 'http://localhost:8080/api/analyze'; 

  constructor(
    private httpClient: HttpClient
  ) { }
  
  /**
   * Analyzes the given text based on the specified analysis type.
   * It constructs an HTTP GET request with query parameters 
   * and returns an Observable of AnalyzedTextResult.
   * 
   * @param text The text to be analyzed.
   * @param type The type of analysis to be performed, defaulting to AnalyzeType.VOWELS.
   * @returns An Observable of AnalyzedTextResult, which is the result of the text analysis.
   */
  analyzeText(text: string, type: AnalyzeType = AnalyzeType.VOWELS): Observable<AnalyzedTextResult> {
    const params = new HttpParams()
      .set('textToAnalyze', text)
      .set('type', type);
    return this.httpClient.get<AnalyzedTextResult>(this.apiUrl, { params });
  }
}
