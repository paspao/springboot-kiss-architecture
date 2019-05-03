import { Component, OnInit } from '@angular/core';
import {ContactDTO, ContactControllerService} from '../remote-services';
import {Observable} from 'rxjs';
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  contacts: Observable<Array<ContactDTO>>;
  constructor(private contactService: ContactControllerService,private router: Router) { }

  ngOnInit() {
    this.contacts = this.contactService.getAllContactsUsingGET();
  }


  delete(i: number){
    this.contactService.deleteContactUsingDELETE(i).subscribe(result => {


      console.log('Contact has been deleted');
      this.router.navigate(['/'], {skipLocationChange: true});
    }, error => {
    });;
  }
}
