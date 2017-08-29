import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';

@Component({
  templateUrl: './new-part.component.html'
})
export class NewPartComponent implements OnInit, OnDestroy {

  types: string[];

  newPartForm = this.fb.group({
    name: ['', Validators.required],
    type: ['', Validators.required],
    price: ['', Validators.required]
  });

  subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.getTypes();
  }

  savePart() {
    const savePartSubs = this.httpService.savePart(this.newPartForm.value).subscribe();
    this.subscriptions.push(savePartSubs);
    this.clearForm();
  }

  getTypes() {
    const getTypeSubs = this.httpService.getTypes().subscribe(part => this.types = part);
    this.subscriptions.push(getTypeSubs);
  }

  clearForm() {
    this.newPartForm.reset();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  setType(type) {
    this.newPartForm.controls['type'].setValue(type);
  }

}
