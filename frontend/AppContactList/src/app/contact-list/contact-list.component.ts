import { Component, OnInit, inject } from '@angular/core';
import { ContactService } from '../services/contact.service';
import { DatePipe } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css',
})
export default class ContactListComponent implements OnInit {
  private contactService = inject(ContactService);
  private router = inject(Router);
  index = 0;
  contacts: Contact[] = [];

  ngOnInit(): void {
    this.reloadData();
  }
  reloadData() {
    this.contactService.list().subscribe((contacts) => {
      this.contacts = contacts;
      this.index = contacts.length;
      console.log(contacts);
    });
  }
  delete(idContact: number) {
    console.log(idContact);
    this.contactService.delete(idContact).subscribe((response) => {
      console.log(response);
      if (response.status === '200') {
        this.reloadData();
      }
    });
  }
}
