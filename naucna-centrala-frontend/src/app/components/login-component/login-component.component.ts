import { Component, OnInit } from '@angular/core';
import { ProcessService } from 'src/app/services/process-service.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private processService: ProcessService,
    private userService: UserService,
    private router: Router) { }

  public formFields: any[];
  private taskId;

  ngOnInit() {
    this.processService.startProcess().subscribe( (res : any) =>{
      console.log(res);
      this.taskId = res.taskId;
      this.formFields = res.formFields;
      console.log(this.formFields);
    })
  }

  login(value, form) {
    console.log(value);
    let properties = new Array();
    for (var property in value) {
      properties.push({fieldId : property, fieldValue : value[property]});
    }
    
    this.userService.login(properties, this.taskId).subscribe(
      (res: any) => {
        console.log(res);
        this.taskId = res.taskId;
        this.processService.getNextProcess(this.taskId).subscribe(
          (res: any) => {
            if( res.taskName == 'Fill in register form') {
              this.router.navigateByUrl('/register');
            } else {
              this.router.navigateByUrl('/magazines')
            }
          }
        );
      })
  }

}
