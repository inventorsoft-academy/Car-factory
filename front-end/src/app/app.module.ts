import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {DashboardModule} from './dashboard/dashboard.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MainPageComponent} from './dashboard/main-page/main-page.component';
import {PartsListComponent} from './dashboard/parts-list/parts-list.component';
import {PartDetailsComponent} from './dashboard/part-details/part-details.component';
import {NewPartComponent} from './dashboard/new-part/new-part.component';
import {HttpService} from './common/services/http.service';
import {CarsListComponent} from './dashboard/cars-list/cars-list.component';
import {CarDetailsComponent} from './dashboard/car-details/car-details.component';
import {NewCarComponent} from './dashboard/new-car/new-car.component';



const routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: '',
        redirectTo: 'main-page',
        pathMatch: 'full'
      },
      {
        path: 'main-page',
        component: MainPageComponent
      },
      {
        path: 'part-list',
        component: PartsListComponent
      },
      {
        path: 'car-list',
        component: CarsListComponent
      },
      {
        path: 'part-details/:id',
        component: PartDetailsComponent
      },
      {
        path: 'car-details/:id',
        component: CarDetailsComponent
      },
      {
        path: 'new-part',
        component: NewPartComponent
      },
      {
        path: 'new-car',
        component: NewCarComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    DashboardModule,
    RouterModule.forRoot(routes)
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
