import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import {
  CdkDragDrop,
  CdkDrag,
  CdkDropList,
  CdkDropListGroup,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { TaskModel } from './model/task.model';
import {
  MatDialog
} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import { TaskAddEditComponent } from './task-add-edit/task-add-edit.component';
import { statusArray } from './model/status.enum';
import {MatIconModule} from '@angular/material/icon';
import { TaskService } from './services/task.service';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [CdkDropListGroup, CdkDropList, CdkDrag, MatButtonModule, MatIconModule, DatePipe],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  status = statusArray;
  tasksTODO: TaskModel[] = [];
  tasksInPogress: TaskModel[] = [];
  tasksCompleted: TaskModel[] = [];
  tasksCanceled: TaskModel[] = [];
  taskGroup: any;
  task: TaskModel = new TaskModel();

  constructor(public dialog: MatDialog,
    public taskService: TaskService) { }

  ngOnInit(): void {
    this.taskGroup = [this.tasksTODO, this.tasksInPogress, this.tasksCompleted, this.tasksCanceled];
    this.taskService.getAllTasks()
    .subscribe(res => {
      this.tasksTODO = res.filter(r => r.status === this.status[0]);
      this.tasksInPogress = res.filter(r => r.status === this.status[1]);
      this.tasksCompleted = res.filter(r => r.status === this.status[2]);
      this.tasksCanceled = res.filter(r => r.status === this.status[3]);
      this.taskGroup = [this.tasksTODO, this.tasksInPogress, this.tasksCompleted, this.tasksCanceled];
    })
  }

  drop(event: CdkDragDrop<TaskModel[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {

      const idCurrent = event.container.id;
      const idPrevious = event.previousContainer.id;

      if(idCurrent && idPrevious) {
        const idList = idCurrent.split('-');
        const idIndex = +idList[idList.length - 1];
        const idPList = idPrevious.split('-')[idList.length - 1];
        const idPIndex = +idPList;
        const selectedItem = event.previousContainer.data[event.previousIndex] as TaskModel;
        selectedItem.status = this.status[idIndex];

        this.editTask(selectedItem, idPIndex, event.previousIndex, this.status[idPIndex]);
      }
    }
  }


  openDialog(item: number): void {
    const data = new TaskModel();
    data.status = this.status[item];
    const dialogRef = this.dialog.open(TaskAddEditComponent, {
      width: '500px',
      hasBackdrop: true,
      data: data,
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.taskService.createTask(result)
        .subscribe(res => {
          this.taskGroup[item].push(res);
          if(res.status !== this.status[item]){
            const pos = this.status.findIndex(s => s === res.status);
            transferArrayItem(
              this.taskGroup[item],
              this.taskGroup[pos],
              this.taskGroup[item].length-1,
              0,
            );
          }
        });
      }
    });
  }

  openDialogEdit(task: TaskModel, indexGroup: number, i: number): void {
    const oldStatus = task.status
    const dialogRef = this.dialog.open(TaskAddEditComponent, {
      width: '500px',
      hasBackdrop: true,
      data: {...task},
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
       this.editTask(result, indexGroup, i, oldStatus);
      }
    });
  }

  editTask(result: TaskModel, indexGroup: number, i: number, oldStatus?: string){
    this.taskService.updateTask(result)
    .subscribe(res => {
      this.taskGroup[indexGroup][i]=res;
      if(result.status !== oldStatus){
        const pos = this.status.findIndex(s => s === res.status);
        transferArrayItem(
          this.taskGroup[indexGroup],
          this.taskGroup[pos],
          i,
          0,
        );
      }
    })
  }

  delete(taskArray: TaskModel[], index: number) {
    if(taskArray[index]?.id){
      this.taskService.deleteTask(taskArray[index].id)
      .subscribe(() => taskArray.splice(index, 1))
    }
  }

}
