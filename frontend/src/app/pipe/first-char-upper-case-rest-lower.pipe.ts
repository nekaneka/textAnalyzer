import { Pipe, PipeTransform } from '@angular/core';

/**
 * Pipe that transforms a string into a format where the first character
 * is in uppercase and all remaining characters are in lowercase.
 */
@Pipe({
  name: 'firstCharUpperCaseRestLower'
})
export class FirstCharUpperCaseRestLowerPipe implements PipeTransform {

  transform(value: string): string{
    if (value == null) return value;
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  }

}
