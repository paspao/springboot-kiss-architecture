import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ListContactComponent} from "./list-contact/list-contact.component";
import {AddContactComponent} from "./add-contact/add-contact.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FindContactComponent} from "./find-contact/find-contact.component";


const routes: Routes = [
  { path: 'add',component: AddContactComponent},
  { path: 'find',component: FindContactComponent},

  { path: '**', component: ListContactComponent}
];

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      routes// , { enableTracing: true }
    ),
    FormsModule
  ],
  declarations: [AddContactComponent,ListContactComponent,FindContactComponent],
  exports: [RouterModule]
})
export class ContactRoutingModule { }
