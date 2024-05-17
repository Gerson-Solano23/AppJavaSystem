import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./contact-list/contact-list.component'),
  },
  {
    path: 'newContact',
    loadComponent: () => import('./contact-form/contact-form.component'),
  },
  {
    path: ':id/update',
    loadComponent: () => import('./contact-form/contact-form.component'),
  },
];
