import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Contact } from '../model/contact.interface';
import { ResponseDTO } from '../model/response.interface';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private http = inject(HttpClient);

  list() {
    return this.http.get<Contact[]>('http://localhost:3000/api/contact/getAll');
  }

  get(id: number) {
    return this.http.get<Contact>(
      `http://localhost:3000/api/contact/get?id=${id}`
    );
  }

  create(contact: Contact) {
    return this.http.post<ResponseDTO>(
      'http://localhost:3000/api/contact/create',
      contact
    );
  }
  update(contact: Contact) {
    return this.http.put<ResponseDTO>(
      `http://localhost:3000/api/contact/update?id=${contact.idContact}`,
      contact
    );
  }
  delete(id: number) {
    return this.http.delete<ResponseDTO>(
      `http://localhost:3000/api/contact/delete?id=${id}`
    );
  }
}
