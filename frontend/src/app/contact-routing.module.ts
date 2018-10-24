import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ListContactComponent} from "./list-contact/list-contact.component";
import {AddContactComponent} from "./add-contact/add-contact.component";


const routes: Routes = [
  { path: 'add',component: AddContactComponent},
  { path: '**', component: ListContactComponent}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(
      routes// , { enableTracing: true }
    )
  ],
  declarations: [AddContactComponent,ListContactComponent],
  exports: [RouterModule]
})
export class ContactRoutingModule { }
