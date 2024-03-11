import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TextAnalyzerComponent } from './text-analyzer.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppRoutingModule } from '../../app-routing.module';
import { AnalyzeType } from '../../enum/AnalyzeType.enum';
import { of, throwError } from 'rxjs';
import { TextAnalyzerService } from '../../service/text-analyzer.service';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AnalyzedTextResult } from '../../dto/AnalyzedTextResult';
import { FirstCharUpperCaseRestLowerPipe } from '../../pipe/first-char-upper-case-rest-lower.pipe';


describe('TextAnalyzerComponent', () => {

  // Variable declarations
  let component: TextAnalyzerComponent;
  let fixture: ComponentFixture<TextAnalyzerComponent>;
  let textAnalyzerService: TextAnalyzerService;
  let snackBar: MatSnackBar;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        TextAnalyzerComponent,
        FirstCharUpperCaseRestLowerPipe,
      ],
      imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        HttpClientTestingModule,
        MatInputModule,
        MatFormFieldModule,
        FormsModule,
        MatSlideToggleModule,
        MatSelectModule,
        MatButtonModule,
        MatCardModule,
        MatListModule,
        MatIconModule,
        MatSnackBarModule,
        NoopAnimationsModule, 
      ],
      providers: [
        provideAnimationsAsync(),
        TextAnalyzerService,
      ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TextAnalyzerComponent);
    component = fixture.componentInstance;
    textAnalyzerService = TestBed.inject(TextAnalyzerService);
    snackBar = TestBed.inject(MatSnackBar);
    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('Should reset filters when resetFilters is called', () => {
    component.textToAnalyze = 'test';
    component.analyzeType = AnalyzeType.CONSONANTS;
    component.analyzeInBackend = true;

    component.resetFilters();

    expect(component.textToAnalyze).toEqual('');
    expect(component.analyzeType).toEqual(AnalyzeType.VOWELS);
    expect(component.analyzeInBackend).toBeFalse();
    expect(component.listItems).toEqual({});
  });


  // Test to ensure analyzeText does not call the service when backend analysis is disabled
  it('Should not call textAnalyzerService when analyzeInBackend is false', () => {
    spyOn(textAnalyzerService, 'analyzeText');
  
    component.textToAnalyze = 'test';
    component.analyzeType = AnalyzeType.VOWELS;
    component.analyzeInBackend = false;
  
    component.analyzeText();
    fixture.detectChanges();
  
    // Assert that the service method is not called
    expect(textAnalyzerService.analyzeText).not.toHaveBeenCalled();
  });

  
  // Mock response from textAnalyzerService
  it('should update listItems on successful analyzeTextInBackend call', () => {
    const mockResponse: AnalyzedTextResult = {
      characterFrequencies: { 
        'E': 3,
        'A': 5 
      },
      type: AnalyzeType.VOWELS
    };

    spyOn(textAnalyzerService, 'analyzeText').and.returnValue(of(mockResponse));

    component.textToAnalyze = 'testEEAaaBBBAA$%&/(/&%&/777';
    component.analyzeType = AnalyzeType.VOWELS;
    component.analyzeInBackend = false;

    component.analyzeText();
    fixture.detectChanges();

    expect(component.listItems).toEqual(mockResponse.characterFrequencies);
  });



  it('Should show snackbar message on analyzeTextInBackend error', () => {
    spyOn(textAnalyzerService, 'analyzeText').and.returnValue(throwError('error'));
    spyOn(snackBar, 'open');

    component.textToAnalyze = 'test';
    component.analyzeType = AnalyzeType.VOWELS;
    component.analyzeInBackend = true;

    component.analyzeText();
    fixture.detectChanges();

    expect(snackBar.open).toHaveBeenCalledWith('Please run backend!', 'close');
  });
});
