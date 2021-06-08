import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ErrorComponent } from '../utils/error/error.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formGroup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private dialog: MatDialog

  ) { }

  ngOnInit(): void {

    this.formGroup = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  onSubmit() {
    console.log(this.formGroup.value);
    this.authService.login(this.formGroup.value).subscribe((data) => {

      this.authService.setToken(data?.token);
      this.authService.setUserId(data?.userId);

      window.location.href = '/';

    }, (error) => {
      this.error(error.error.message);
    })
  }

  error(message: string) {
    const dialogRef = this.dialog.open(ErrorComponent, {
      data: { message: message }
    });
  }

}
