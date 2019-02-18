import { Component, OnInit } from '@angular/core';
import { ProcessService } from 'src/app/services/process-service.service';
import { ActivatedRoute } from '@angular/router';
import { jsonpCallbackContext } from '@angular/common/http/src/module';

@Component({
  selector: 'app-chooseReviewers',
  templateUrl: './chooseReviewers.component.html',
  styleUrls: ['./chooseReviewers.component.scss']
})
export class ChooseReviewersComponent implements OnInit {

  constructor(
    private processService: ProcessService,
    private route: ActivatedRoute
  ) { }

  public formFields: any[];
  private taskId;
  public reviewerIds;

  ngOnInit() {
    this.taskId = this.route.snapshot.params['taskId'];
  }

  submit(){
    console.log(this.reviewerIds);
    console.log(this.reviewerIds.split(','));

    console.log(JSON.stringify(this.reviewerIds.split(',')))

    let body = { reviewers: this.reviewerIds.split(',')}
    this.processService.submitTaskReviewers(body,this.taskId)
      .subscribe( (res: any) => {
        console.log(res);
      })
  }

  


}
