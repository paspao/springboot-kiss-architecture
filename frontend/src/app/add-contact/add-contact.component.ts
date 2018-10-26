import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ContactControllerService } from '../remote-services';
import { Contact } from '../remote-services/model/contact';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent implements OnInit {

  addForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    birthPlace: ['', Validators.required],
    country: ['', Validators.required],
    birthDate: ['', Validators.required],
    gender: ['', Validators.required],
    email: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    address: this.fb.group({
      street: ['', Validators.required]
    }),
  });
  constructor(private fb: FormBuilder, private contactService: ContactControllerService) { }

  ngOnInit() {
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    // console.warn(this.addForm.value);
    const contact:  Contact = {};
    contact.firstName = this.addForm.value['firstName'];

    this.contactService.saveContactUsingPOST(contact).subscribe(result => {


      console.log('Contact has been saved');

    }, error => {
    });
  }

}
