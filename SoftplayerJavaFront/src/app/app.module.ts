import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PersonComponent } from './person/person.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersonService } from './services/person.service';
import { AuthService } from './services/auth.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { DeleteDialogComponent } from './utils/delete-dialog/delete-dialog.component';
import { FormPersonComponent } from './person/form-person/form-person.component';
import { MatInputModule } from '@angular/material/input';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SuccessComponent } from './utils/success/success.component';
import { ErrorComponent } from './utils/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonComponent,
    DeleteDialogComponent,
    FormPersonComponent,
    SuccessComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    MatMenuModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatNativeDateModule,
    MatDatepickerModule,
    NgxMaskModule.forRoot(),

  ],
  providers: [
    AuthService,
    PersonService,
    MatDatepickerModule
  ],
  entryComponents: [SuccessComponent, ErrorComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
