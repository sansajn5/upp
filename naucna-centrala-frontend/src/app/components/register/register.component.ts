import { Component, OnInit } from '@angular/core';
import { ProcessService } from 'src/app/services/process-service.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private processService: ProcessService,
    private userService: UserService,
    private router: Router) { }

  private taskId;
  public formFields;

  ngOnInit() {
    console.log("Ovbdfe")
    this.processService.getRegisterForm().subscribe(
      (res: any) => {
        console.log(res);
        this.taskId = res.taskId;
        this.formFields = res.formFields;
      }
    )
  }

  register(value, form) {
    let properties = new Array();
    for (var property in value) {
      properties.push({fieldId : property, fieldValue : value[property]});
    }

    this.userService.register(properties, this.taskId).subscribe(
      (res: any) => {
        console.log(res);
        this.taskId = res.taskId;
        this.processService.getNextProcess(this.taskId).subscribe(
          (res: any) => {
              this.router.navigateByUrl('/magazines')
          }
        );
      }
    )

  }

}
