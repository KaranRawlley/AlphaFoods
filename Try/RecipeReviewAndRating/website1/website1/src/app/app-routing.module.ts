import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { RecipeComponent } from './recipe/recipe.component';
import { AddrecipeComponent } from './addrecipe/addrecipe.component';
import { DetailrecipeComponent } from './detailrecipe/detailrecipe.component';
import { MyrecipeComponent } from './myrecipe/myrecipe.component';
import { UpdaterecipeComponent } from './updaterecipe/updaterecipe.component';

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'signin',component:SigninComponent},
  {path:'signup',component:SignupComponent},
  {path:'recipe',component:RecipeComponent},
  {path:'addrecipe',component:AddrecipeComponent},
  {path:'detailrecipe',component:DetailrecipeComponent},
  {path:'myrecipe',component:MyrecipeComponent},
  {path:'updaterecipe',component:UpdaterecipeComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
