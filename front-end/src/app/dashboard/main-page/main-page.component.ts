import {Component} from '@angular/core';

@Component({
  templateUrl: './main-page.component.html'
})
export class MainPageComponent {

  checked = false;
  indeterminate = false;
  align = 'start';
  disabled = false;

  constructor() {
  }

}
