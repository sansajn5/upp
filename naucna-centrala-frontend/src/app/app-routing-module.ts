import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from './components/login-component/login-component.component';
import { RegisterComponent } from './components/register/register.component';
import { MagazinesComponent } from './components/magazines/magazines.component';
import { TasksComponent } from './components/tasks/tasks.component';
import { ScientificWorkSubmissionComponent } from './components/scientificWorkSubmission/scientificWorkSubmission.component';
import { ScientificWorkCheckComponent } from './components/scientificWorkCheck/scientificWorkCheck.component';
import { ChooseReviewersComponent } from './components/chooseReviewers/chooseReviewers.component';
import { ReviewComponent } from './components/review/review.component';
import { EvaluationComponent } from './components/evaluation/evaluation.component';
import { CorrectAndCommentComponent } from './components/correctAndComment/correctAndComment.component';

const appRoutes: Routes = 
[
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'magazines', component: MagazinesComponent},
    {path: 'tasks', component: TasksComponent},
    {path: 'scientificWork/submit/:taskId', component: ScientificWorkSubmissionComponent},
    {path: 'scientificWork/check/:taskId', component: ScientificWorkCheckComponent},
    {path: 'scientificWork/reviewers/:taskId', component: ChooseReviewersComponent},
    {path: 'scientificWork/review/:taskId', component: ReviewComponent},
    {path: 'scientificWork/evaluation/:taskId', component: EvaluationComponent},
    {path: 'scientificWork/correctAndComment/:taskId', component: CorrectAndCommentComponent}
];

@NgModule({
    imports : [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
  
}