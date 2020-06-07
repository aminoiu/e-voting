import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'formatTitle'
})
export class FormatTitlePipe implements PipeTransform {
  transform(title: string): string {
    const result = title.replace(/([A-Z])/g, ' $1');


    return result.charAt(0).toUpperCase() + result.slice(1);
  }
}
