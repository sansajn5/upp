import { Component, OnInit } from '@angular/core';
import { ProcessService } from 'src/app/services/process-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

  public tasks: any;

  constructor(
    private processService:ProcessService,
    private router: Router
  ) { }

  ngOnInit() {
    this.processService.getTasks()
      .subscribe( (res: any) => {
        console.log(res);
        this.tasks = res;
      } )
  }

  executeTask(id:any) {
    this.processService.getTask(id)
      .subscribe( (res:any) => {
        console.log(res);
        this.router.navigateByUrl(res.path + "/" + id);
      })
  }

}
