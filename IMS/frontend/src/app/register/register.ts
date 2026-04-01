import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormsModule
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../auth';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule,RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {

  registerForm: FormGroup;

  constructor(private fb: FormBuilder,  
              private auth: AuthService,
               private router: Router
  ) {
    this.registerForm = this.fb.group({
      fullName: ['', [
        Validators.required,
        Validators.pattern(/^[A-Za-z ]+$/)
      ]],

      mobileNumber: ['', [
        Validators.required,
        Validators.pattern(/^[0-9]{10}$/)
      ]],

      email: ['', [
        Validators.required,
        Validators.email
      ]],

      userName: ['', Validators.required],

      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).+$/)
      ]],

      userRole: ['', Validators.required]
    });
  }

  field(name: string) {
    return this.registerForm.get(name)!;
  }

  onRegister() {
  if (this.registerForm.valid) {

    this.auth.register(this.registerForm.value).subscribe({
      next: (res) => {
        alert("Registration successful!");
        this.router.navigate(['/login']);
      },
      error: (err) => {
        alert("Registration failed!");
        console.log(err);
      }
    });

  }
}
}