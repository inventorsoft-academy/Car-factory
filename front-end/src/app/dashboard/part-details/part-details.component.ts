import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../../common/services/http.service';
import {Part} from '../../common/models/Part';
import {Subscription} from 'rxjs/Subscription';

@Component({
  templateUrl: './part-details.component.html'
})
export class PartDetailsComponent implements OnInit, OnDestroy {

  part: Part;

  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.findPartById(this.route.snapshot.params['id']);
  }

  findPartById(id) {
    const findPartSubs = this.httpService.findPartById(id)
      .subscribe(
        res => {
          this.part = res;
        }
      );
    this.subscriptions.push(findPartSubs);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}
