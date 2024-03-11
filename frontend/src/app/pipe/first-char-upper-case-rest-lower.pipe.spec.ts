import { FirstCharUpperCaseRestLowerPipe } from "./first-char-upper-case-rest-lower.pipe";

describe('FirstCharUpperCaseRestLowerPipe', () => {
  const pipe = new FirstCharUpperCaseRestLowerPipe();

  it('transforms "abc" to "Abc"', () => {
    expect(pipe.transform('abc')).toBe('Abc');
  });

  it('transforms "ABC" to "Abc"', () => {
    expect(pipe.transform('ABC')).toBe('Abc');
  });

  it('returns empty string for empty input', () => {
    expect(pipe.transform('')).toBe('');
  });

  /*
  it('handles null input', () => {
    expect(pipe.transform(null)).toBeNull();
  });

  it('handles undefined input', () => {
    expect(pipe.transform(undefined)).toBeUndefined();
  });

   */
});
