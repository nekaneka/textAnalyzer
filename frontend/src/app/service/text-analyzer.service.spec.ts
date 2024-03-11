import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TextAnalyzerService } from './text-analyzer.service';
import { HttpClient } from '@angular/common/http';
import { AnalyzeType } from '../enum/AnalyzeType.enum';
import { AnalyzedTextResult } from '../dto/AnalyzedTextResult';

describe('TextAnalyzerService', () => {
  let service: TextAnalyzerService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TextAnalyzerService]
    });

    service = TestBed.inject(TextAnalyzerService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpTestingController.verify();
  });



  it('should be created', () => {
    expect(service).toBeTruthy();
  });



  it('should make a GET request', () => {
    const mockResponse: AnalyzedTextResult = {
      characterFrequencies: {},
      type: AnalyzeType.VOWELS
    };
    
    const testText = '';
    const testType = AnalyzeType.VOWELS;
  
    service.analyzeText(testText, testType).subscribe();
  
    const req = httpTestingController.expectOne(`${service.apiUrl}?textToAnalyze=${testText}&type=${testType}`);
    expect(req.request.method).toBe('GET'); // Verify that it was a GET request
  
    req.flush(mockResponse); // Provide mock data as the response
  });
  
});
