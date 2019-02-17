import { Component, OnInit } from '@angular/core';
import { ProcessService } from 'src/app/services/process-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss']
})
export class EvaluationComponent implements OnInit {

  constructor(
    private processService: ProcessService,
    private route: ActivatedRoute
  ) { }

  public formFields: any[];
  private taskId;

  ngOnInit() {
    this.taskId = this.route.snapshot.params['taskId'];
    this.processService.getTaskForm(this.taskId).subscribe( (res : any) =>{
      this.formFields = res.formFields;
    })
  }

  submit(value, form) {
    console.log(value);

    let properties = new Array();
    for (var property in value) {
      properties.push({fieldId : property, fieldValue : value[property]});
    }

    let count = 0;
    for(let i = 0; i < properties.length; i++){
      if(properties[i].fieldValue){
        count++; 
      }
    }

    if(count != 1){
      alert("You must check exactly one option!");
      return;
    }

    this.processService.submitTask(properties,this.taskId)
      .subscribe( (res:any) => {
        alert("Success!");
      })

  }
}
