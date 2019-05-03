import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ContactControllerService, ContactDTO } from '../remote-services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-find-contact',
  templateUrl: './find-contact.component.html',
  styleUrls: ['./find-contact.component.css']
})
export class FindContactComponent implements OnInit {
  condition = 0;
  contact:  ContactDTO = {};

  constructor( private contactService: ContactControllerService, private router: Router) { }

  ngOnInit() {
    this.contact=null;
  }

  find(){
    console.log("find");

    this.contactService.findContactUsingGET(this.condition).subscribe(result => {


      console.log('Contact has been find');
      console.log(result);
      this.contact=result;
     // this.router.navigate(['/'], {skipLocationChange: true});
    }, error => {
      console.log(error);
    });


  }




}
