import { Component, OnInit } from '@angular/core';
import {Contact, ContactControllerService} from "../remote-services";
import {Observable} from "rxjs";

@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  contatti: Observable<Array<Contact>>;
  constructor(private contactService: ContactControllerService) { }

  ngOnInit() {
    this.contatti=this.contactService.getAllContactsUsingGET();
  }

}
