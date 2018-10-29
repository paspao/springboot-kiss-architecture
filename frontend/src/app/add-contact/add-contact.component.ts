import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ContactControllerService } from '../remote-services';
import { Contact } from '../remote-services/model/contact';
import { Router } from '@angular/router';

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
  constructor(private fb: FormBuilder, private contactService: ContactControllerService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    // console.warn(this.addForm.value);
    const contact:  Contact = {};
    contact.firstName = this.addForm.value['firstName'];
    contact.lastName = this.addForm.value['lastName'];
    contact.birthPlace = this.addForm.value['birthPlace'];
    contact.country = this.addForm.value['country'];
    const date: Date = new Date(this.addForm.value['birthDate']);
    contact.dateOfBirth = date.toLocaleDateString();
    contact.gender = this.addForm.value['gender'];
    contact.email = this.addForm.value['email'];
    contact.phoneNumber = this.addForm.value['phoneNumber'];
    contact.address = this.addForm.value['address'].street;

    this.contactService.saveContactUsingPOST(contact).subscribe(result => {


      console.log('Contact has been saved');
      this.router.navigate(['/'], {skipLocationChange: true});
    }, error => {
    });
  }

}
