import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from './header/header.component';
import {MainPageComponent} from './main-page/main-page.component';
import {DashboardComponent} from './dashboard.component';
import {NewPartComponent} from './new-part/new-part.component';
import {PartsListComponent} from './parts-list/parts-list.component';
import {PartDetailsComponent} from './part-details/part-details.component';
import {MaterialModule} from './material-module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CarsListComponent} from './cars-list/cars-list.component';
import {CarDetailsComponent} from './car-details/car-details.component';
import {NewCarComponent} from './new-car/new-car.component';
import 'hammerjs';

@NgModule({
  imports: [
    MaterialModule,
    BrowserAnimationsModule,
    CommonModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule
  ],
  declarations: [
    NewCarComponent,
    CarDetailsComponent,
    CarsListComponent,
    HeaderComponent,
    MainPageComponent,
    NewPartComponent,
    PartsListComponent,
    PartDetailsComponent,
    DashboardComponent
  ],
  providers: []
})
export class DashboardModule {
}
