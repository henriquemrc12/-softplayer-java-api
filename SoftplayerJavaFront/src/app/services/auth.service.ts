import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
  ) { }

  login(model: any): Observable<any> {
    return this.http.post(`${environment.urlApi}/signin`, model);
  }

  getToken() {
    return localStorage.getItem("token");
  }

  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
  }

  setToken(token: string) {
    localStorage.setItem("token", token);
  }

  setUserId(userId: string) {
    localStorage.setItem("userId", userId);
  }

  getHeaders() {
    //var token: string = JSON.parse(localStorage.getItem('token'))
    let token: string = localStorage.getItem('token')
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + token,
        'Content-Type': "application/json"
      })
    }


  }
}
