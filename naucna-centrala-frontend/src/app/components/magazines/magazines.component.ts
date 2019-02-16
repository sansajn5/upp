import { Component, OnInit } from '@angular/core';
import { MagazineService } from 'src/app/services/magazine-service.service';
import { ProcessService } from 'src/app/services/process-service.service';

@Component({
  selector: 'app-magazines',
  templateUrl: './magazines.component.html',
  styleUrls: ['./magazines.component.scss']
})
export class MagazinesComponent implements OnInit {

  public magazines : any;

  constructor(
    private magazineService: MagazineService,
    private processService: ProcessService
    ) { }

  ngOnInit() {
    this.magazineService.getAllMagazines()
      .subscribe( (res : any) => {
        this.magazines = res;
      })
  }

  publish(magazineId: any){
    this.processService.startProcess(magazineId)
      .subscribe( (res:any) => {
        console.log(res);
      })
  }

}
