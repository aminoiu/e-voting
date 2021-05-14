import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-blockchain-visualization',
  templateUrl: './blockchain-visualization.component.html',
  styleUrls: ['./blockchain-visualization.component.css']
})
export class BlockchainVisualizationComponent implements OnInit {
  form: FormGroup;
  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'}
  ];

  foodControl =  new FormControl(this.foods[2].value);

  constructor() {
    this.form = new FormGroup({
      food: this.foodControl
    });
  }

  ngOnInit(): void {
  }


}
