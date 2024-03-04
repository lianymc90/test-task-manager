import { Component, Inject } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { TaskModel } from '../model/task.model';
import { statusArray } from '../model/status.enum';

import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-task-add-edit',
  standalone: true,
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    MatSelectModule,
    DatePipe
  ],
  templateUrl: './task-add-edit.component.html',
  styleUrl: './task-add-edit.component.css'
})
export class TaskAddEditComponent  {
  status = statusArray;

  constructor(
    public dialogRef: MatDialogRef<TaskAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TaskModel,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  getErrorMessage(title: any){
    return 'The field is required'
  }
}
