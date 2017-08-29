import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {Car} from '../../common/models/Car';


@Component({
  templateUrl: './cars-list.component.html'
})
export class CarsListComponent implements OnInit, OnDestroy {

  carList: Car[];

  subscriptions: Subscription[] = [];

  constructor(private httpService: HttpService,
              private router: Router) {
  }

  ngOnInit() {
    this.getCars();
  }

  getCars() {
    const getPartsSubscription = this.httpService.getCars()
      .subscribe(
        res => {
          this.carList = res;
        }
      );
    this.subscriptions.push(getPartsSubscription);
  }

  showPartDetails(id) {
    this.router.navigate([`dashboard/car-details/${id}`]);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }
}
