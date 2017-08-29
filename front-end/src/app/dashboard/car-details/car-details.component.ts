import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';
import {Car} from '../../common/models/Car';

@Component({
  templateUrl: './car-details.component.html'
})
export class CarDetailsComponent implements OnInit, OnDestroy {

  car: Car;

  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.findCarById(this.route.snapshot.params['id']);
  }

  findCarById(id) {
    const findCarSubs = this.httpService.findCarById(id)
      .subscribe(
        res => {
          this.car = res;
        }
      );
    this.subscriptions.push(findCarSubs);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}
