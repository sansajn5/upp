import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(user, taskId) {
    return this.http.post("http://localhost:8080/auth/login/".concat(taskId), user);
  }

  register(user, taskId) {
    return this.http.post("http://localhost:8080/auth/register/".concat(taskId), user)
  }

}
