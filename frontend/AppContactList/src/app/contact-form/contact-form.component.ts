import { Component, inject } from '@angular/core';
import {
  Form,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContactService } from '../services/contact.service';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
})
export default class ContactFormComponent {
  private formB = inject(FormBuilder);
  private contactService = inject(ContactService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form?: FormGroup;
  contact?: Contact;

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    const id = this.route.snapshot.paramMap.get('id');
    console.log('ID: ', id);

    if (id) {
      this.contactService.get(parseInt(id)).subscribe((contact) => {
        //console.log(contact);
        this.contact = contact;
        this.form = this.formB.group({
          idContact: [contact.idContact],
          name: [contact.name, Validators.required],
          email: [contact.email, [Validators.required, Validators.email]],
          phone: [
            contact.phone,
            [
              Validators.required,
              Validators.pattern('[0-9]*'),
              Validators.minLength(8),
              Validators.maxLength(8),
            ],
          ],
          dateOfBirth: [contact.dateOfBirth, Validators.required],
        });
      });
    } else {
      this.form = this.formB.group({
        idContact: [null],
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        phone: [
          '',
          [
            Validators.required,
            Validators.pattern('[0-9]*'),
            Validators.minLength(8),
            Validators.maxLength(8),
          ],
        ],
        dateOfBirth: ['', Validators.required],
      });
    }
  }

  save() {
    if (this.form!.invalid) {
      return;
    }
    const contactForm = this.form!.value;
    console.log(contactForm);
    if (this.contact) {
      this.contactService.update(contactForm).subscribe((response) => {
        console.log(response);
        if (response.status === '200') {
          this.router.navigate(['/']);
        }
      });
    } else {
      this.contactService.create(contactForm).subscribe((response) => {
        console.log(response);
        if (response.status === '201 CREATED') {
          this.router.navigate(['/']);
        }
      });
    }
  }
}
