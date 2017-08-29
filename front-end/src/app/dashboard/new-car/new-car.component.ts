import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';

@Component({
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car-form.css']
})
export class NewCarComponent implements OnInit, OnDestroy {

  types: string[];

  newCarForm = this.fb.group({
    brand: ['', Validators.required],
    model: ['', Validators.required],
    color: ['', Validators.required]
  });

  subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.getTypes();
  }

  saveCar() {
    const saveCarSubs = this.httpService.saveCar(this.newCarForm.value).subscribe();
    this.subscriptions.push(saveCarSubs);
    this.clearForm();
  }

  clearForm() {
    this.newCarForm.reset();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  getTypes() {
    const getTypeSubs = this.httpService.getTypes().subscribe(part => this.types = part);
    this.subscriptions.push(getTypeSubs);
  }


}
