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

  getTasks() {
    return this.http.get(this.appUrl+'process/tasks');
  }

  getTask(taskId: any) {
    return this.http.get(this.appUrl+'process/'+ taskId);
  }

  getTaskForm(taskId:any) {
    return this.http.get(this.appUrl+'process/taskForm/'+ taskId);
  }

  submitTask(body:any, taskId:any) {
      return this.http.post(this.appUrl.concat("process/submitTask/" + taskId),body);

  }

  submitTaskReviewers(body:any, taskId:any) {
    return this.http.post(this.appUrl.concat("process/task/chooseReviewers/" + taskId),body);

}


  getNextProcess(taskId: string) {
    return this.http.get(this.appUrl+'process/getNext');
  }

  getRegisterForm(){
    return this.http.get(this.appUrl+'process/getRegisterForm');
  }


}
