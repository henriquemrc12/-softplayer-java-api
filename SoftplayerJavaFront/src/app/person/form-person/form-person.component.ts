import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PersonService } from 'src/app/services/person.service';
import { ErrorComponent } from 'src/app/utils/error/error.component';
import { SuccessComponent } from 'src/app/utils/success/success.component';

@Component({
  selector: 'app-form-person',
  templateUrl: './form-person.component.html',
  styleUrls: ['./form-person.component.css']
})
export class FormPersonComponent implements OnInit {

  isEdit: boolean;
  formGroup: FormGroup;

  constructor(
    private personService: PersonService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    public dialogRef: MatDialogRef<FormPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data
  ) {
    this.isEdit = data?.isEdit;
    if (this.isEdit) {
      this.edit(data?.id);
    }
  }

  ngOnInit(): void {
    this.formGroup = this.fb.group({
      id: [null],
      name: [null, [Validators.required]],
      gender: [null, [Validators.required]],
      cpf: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      placeOfBirth: [null, [Validators.required]],
      birthDate: [null, [Validators.required]],
    });
  }

  edit(id: number) {
    this.personService.getById(id).subscribe((data) => {
      this.formGroup.patchValue(data);
    })
  }

  save() {
    if (!this.isEdit) {
      this.personService.create(this.formGroup.value).subscribe(() => {
        this.success('Cadastro feito com sucesso!');
        this.close();
      }, (error) => {
        this.error(error.error.message);
      });
    } else {
      this.personService.update(this.formGroup.value).subscribe(() => {
        this.success('Atualização feita com sucesso!');
        this.close();
      }, (error) => {
        this.error(error.error.message);
      });
    }
  }

  success(message: string) {
    const dialogRef = this.dialog.open(SuccessComponent, {
      data: { message: message }
    });
    dialogRef.afterClosed().subscribe(() => {
      this.close()
    })
  }

  error(message: string) {
    const dialogRef = this.dialog.open(ErrorComponent, {
      data: { message: message }
    });
  }

  close() {
    this.dialogRef.close();
  }
}
