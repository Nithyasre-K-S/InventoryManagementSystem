import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private registerUrl = "http://localhost:8080/register";  

  constructor(private http: HttpClient) {}

  register(data: any): Observable<any> {
    return this.http.post(this.registerUrl, data);
  }
}