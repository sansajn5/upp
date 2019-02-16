import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from './components/login-component/login-component.component';
import { RegisterComponent } from './components/register/register.component';
import { MagazinesComponent } from './components/magazines/magazines.component';

const appRoutes: Routes = 
[
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'magazines', component: MagazinesComponent}
];

@NgModule({
    imports : [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
  
}