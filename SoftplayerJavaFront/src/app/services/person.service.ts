import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService {


  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  create(model: any): Observable<any> {
    return this.http.post(`${environment.urlApi}/person`, model, { ...this.authService.getHeaders() });
  }

  update(model: any): Observable<any> {
    return this.http.put(`${environment.urlApi}/person`, model, { ...this.authService.getHeaders() });
  }

  getAll(): Observable<any> {
    return this.http.get(`${environment.urlApi}/person`, { ...this.authService.getHeaders() });
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${environment.urlApi}/person/${id}`, { ...this.authService.getHeaders() });
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${environment.urlApi}/person/${id}`, { ...this.authService.getHeaders() });
  }
}
