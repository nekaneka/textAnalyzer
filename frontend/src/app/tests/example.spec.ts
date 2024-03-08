import { test, expect, Locator } from '@playwright/test';

test.describe('Text Analyzer feature tests', () => {
  // Common locators and variables declared here to be used in beforeEach and individual tests
  let textAreaLocator: Locator;
  let analyzeButtonLocator: Locator;
  let resetFiltersButtonLocator: Locator;
  let clearButtonLocator: Locator;
  let dropdownLocator: Locator;
  let noDataLocator: Locator;
  
  // Perform actions that are common across all tests
  test.beforeEach(async ({ page }) => {
    await page.goto('http://localhost:4200/');
    textAreaLocator = page.locator('textarea');
    analyzeButtonLocator = page.locator('button', { hasText: 'Analyze' });
    resetFiltersButtonLocator = page.locator('button', { hasText: 'Reset filters' });
    clearButtonLocator = page.locator('button[aria-label="Clear"]');
    dropdownLocator = page.locator('mat-select');
    noDataLocator = page.locator('text=No data');
  });


  test('has title', async ({ page }) => {
    // Expect a title "to contain" a substring.
    await expect(page).toHaveTitle(/Text Analyzer/);
  });


  test('show no data on reset click', async () => {
    await resetFiltersButtonLocator.click();
    await expect(noDataLocator).toBeVisible();
  });


  test('input text is captured', async () => {
    await textAreaLocator.fill('Sample Text');
    const inputText = await textAreaLocator.inputValue();
    expect(inputText).toBe('Sample Text');
  });


  test('clear text on clear button click', async () => {
    await textAreaLocator.fill('Sample Text');
    await clearButtonLocator.click();
    await resetFiltersButtonLocator.click();
    const inputText = await textAreaLocator.inputValue();
    expect(inputText).toBe('');
  });


  test('select analysis type', async ({ page }) => {
    await dropdownLocator.click();
    await page.locator('mat-option', { hasText: 'Consonants' }).click();
    const selectedType = await dropdownLocator.textContent();
    expect(selectedType).toContain('Consonants');
  });


  test('text input and vowel analysis results', async ({ page }) => {
    await textAreaLocator.fill('TEST input CAse');
    await analyzeButtonLocator.click();
    // Expectations for vowel analysis results
    await expect(page.locator('text=A:')).toContainText('1');
    await expect(page.locator('text=E:')).toContainText('2');
    await expect(page.locator('text=I:')).toContainText('1');
    await expect(page.locator('text=U:')).toContainText('1');
  });

  
  test('text input and consonant analysis results', async ({ page }) => {
    await textAreaLocator.fill('TEST input CAse');
    await dropdownLocator.click();
    await page.locator('mat-option', { hasText: 'Consonants' }).click();
    await analyzeButtonLocator.click();

    // Expectations for consonant analysis results
    await expect(page.locator('text=C:')).toContainText('1');
    await expect(page.locator('text=N:')).toContainText('1');
    await expect(page.locator('text=P:')).toContainText('1');
    await expect(page.locator('text=S:')).toContainText('2');
    await expect(page.locator('text=T:')).toContainText('3');
  });


  test('reset filters functionality', async () => {
    const initialTextValue = await textAreaLocator.inputValue();
    expect(initialTextValue).toBe('');
    await textAreaLocator.fill('TEST input CAse');
    await analyzeButtonLocator.click();
    await resetFiltersButtonLocator.click();
    const resetTextValue = await textAreaLocator.inputValue();
    expect(resetTextValue).toBe('');
    await expect(noDataLocator).toBeVisible();
  });

});
