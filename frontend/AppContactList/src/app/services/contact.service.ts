import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private http = inject(HttpClient);

  list() {
    return this.http.get('http://localhost:8080/contact/getAll');
  }

  get(id: number) {
    return this.http.get(`http://localhost:8080/contact/get?id=${id}`);
  }

  create(contact: any) {
    return this.http.post('http://localhost:8080/contact/create', contact);
  }
  update(contact: any) {
    return this.http.put('http://localhost:8080/contact/update', contact);
  }
  delete(id: number) {
    return this.http.delete(`http://localhost:8080/contact/delete?id=${id}`);
  }
}
