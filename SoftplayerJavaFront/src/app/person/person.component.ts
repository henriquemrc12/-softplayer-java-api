import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../services/auth.service';
import { PersonService } from '../services/person.service';
import { DeleteDialogComponent } from '../utils/delete-dialog/delete-dialog.component';
import { FormPersonComponent } from './form-person/form-person.component';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  displayedColumns: string[] = [
    'name',
    'gender',
    'cpf',
    'email',
    'placeOfBirth',
    'birthDate',
    'edit',
    'delete'
  ];
  dataSource = new MatTableDataSource();
  hasPeople: boolean;

  constructor(
    private authService: AuthService,
    private personService: PersonService,
    private dialog: MatDialog
  ) {
    this.getAllPeople();
  }

  ngOnInit(): void {
    if (!this.authService.getToken()) {
      window.location.href = "/login"
    }
  }

  getAllPeople() {
    this.personService.getAll().subscribe((data) => {
      console.log(data);
      setTimeout(() => {
        if (data.length == 0) {
          this.hasPeople = false;
        } else {
          this.dataSource = new MatTableDataSource(data);
          this.hasPeople = true;
        }
      });
    }, (err) => {
      console.error(err)
    })
  }

  edit(id: number) {
    const dialogRef = this.dialog.open(FormPersonComponent, {
      data: { id: id, isEdit: true }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllPeople();
    });
  }

  new() {
    const dialogRef = this.dialog.open(FormPersonComponent, {
      data: { isEdit: false }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllPeople();
    });
  }

  delete(id: number) {

    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data: { id: id }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.answer) {
        this.personService.deleteById(id).subscribe(() => {
          this.getAllPeople();
        }, (error) => {

        })
      }
    });
  }

  logout() {
    this.authService.logout();
    window.location.href = "/login";
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
