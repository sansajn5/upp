import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  private appUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  startProcess(magazineId: any) {
    return this.http.get(this.appUrl+'process/start/' + magazineId);
  }

  getNextProcess(taskId: string) {
    return this.http.get(this.appUrl+'process/getNext');
  }

  getRegisterForm(){
    return this.http.get(this.appUrl+'process/getRegisterForm');
  }


}
