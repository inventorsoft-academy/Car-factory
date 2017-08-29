import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Part } from '../models/Part';
import {Car} from '../models/Car';

@Injectable()
export class HttpService {

    private API = 'http://localhost:8080/car-factory/';

    constructor(private http: Http) {
    }

    getParts(): Observable<Part[]> {
        const url = `${this.API}parts`;
        return this.http.get(url)
                   .map(res => res.json());
    }

  getCars(): Observable<Car[]> {
    const url = `${this.API}cars`;
    return this.http.get(url)
      .map(res => res.json());
  }

    findPartById(id): Observable<Part> {
        const url = `${this.API}part/${id}`;
        return this.http.get(url)
                   .map(res => res.json());
    }

  findCarById(id): Observable<Car> {
    const url = `${this.API}car/${id}`;
    return this.http.get(url)
      .map(res => res.json());
  }

    savePart(obj: {}): Observable<Response> {
        const url = `${this.API}create/part`;
        return this.http.post(url, obj);
    }

  saveCar(obj: {}): Observable<Response> {
    const url = `${this.API}create/car`;
    return this.http.post(url, obj);
  }

  getTypes(): Observable<string[]> {
    const url = `${this.API}types`;
     return this.http.get(url)
      .map(res => res.json());
  }

}
