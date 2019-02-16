import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class MagazineService {
    private appUrl = "http://localhost:8080/";

    constructor(private http: HttpClient) { }

    getAllMagazines() {
        return this.http.get(this.appUrl+'magazine/getAll');
    }

  }