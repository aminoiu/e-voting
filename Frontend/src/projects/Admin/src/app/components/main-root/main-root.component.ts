import { Component,  Input, OnInit} from '@angular/core';
import {FormDataService} from "../../services/FormData/form-data.service";

@Component({
  selector: 'main-root',
  templateUrl: './main-root.component.html',
  styleUrls: ['./main-root.component.css']
})
export class MainRootComponent implements OnInit {
  title = 'Multi-Step Wizard';
  @Input() formData;

  constructor(private formDataService: FormDataService) {
  }

  ngOnInit() {
    this.formData = this.formDataService.getFormData();
    console.log(this.title + ' loaded!');
  }
}
