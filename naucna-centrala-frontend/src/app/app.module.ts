import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing-module';
import { LoginComponent } from './components/login-component/login-component.component';
import { ProcessService } from './services/process-service.service';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';
import { RegisterComponent } from './components/register/register.component';
import { MagazinesComponent } from './components/magazines/magazines.component';
import { MagazineService } from './services/magazine-service.service';
import { HeaderComponent } from './components/header/header.component';
import { TasksComponent } from './components/tasks/tasks.component';
import { ScientificWorkSubmissionComponent } from './components/scientificWorkSubmission/scientificWorkSubmission.component';
import { ScientificWorkCheckComponent } from './components/scientificWorkCheck/scientificWorkCheck.component';
import { ChooseReviewersComponent } from './components/chooseReviewers/chooseReviewers.component';
import { ReviewComponent } from './components/review/review.component';
import { EvaluationComponent } from './components/evaluation/evaluation.component';
import { CorrectAndCommentComponent } from './components/correctAndComment/correctAndComment.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MagazinesComponent,
    HeaderComponent,
    TasksComponent,
    ScientificWorkSubmissionComponent,
    ScientificWorkCheckComponent,
    ChooseReviewersComponent,
    ReviewComponent,
    EvaluationComponent,
    CorrectAndCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProcessService, UserService, MagazineService],
  bootstrap: [AppComponent]
})
export class AppModule { }
