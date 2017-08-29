import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {Part} from '../../common/models/Part';
import {CdkTableModule} from '@angular/cdk';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';


@Component({
  templateUrl: './parts-list.component.html',
  styleUrls: ['table.css']
})
export class PartsListComponent implements OnInit, OnDestroy {

  partList: Part[];
  subscriptions: Subscription[] = [];

  constructor(private httpService: HttpService,
              private router: Router) {
  }

  ngOnInit() {
    this.getParts();
  }

  getParts() {
    const getPartsSubscription = this.httpService.getParts()
      .subscribe(
        res => {
          this.partList = res;
        }
      );
    this.subscriptions.push(getPartsSubscription);
  }

  showPartDetails(id) {
    this.router.navigate([`dashboard/part-details/${id}`]);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }
}


