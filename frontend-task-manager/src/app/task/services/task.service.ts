import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { TaskModel } from '../model/task.model';

@Injectable({
  providedIn: 'root',
})

export class TaskService {
  private baseURl =
    `${environment.API_PROTOCOL}://${environment.API_HOST}`;

    constructor(private http: HttpClient) { }

    getAllTasks(): Observable<TaskModel[]> {
      return this.http.get(`${this.baseURl}get`).pipe(
        map((response: any) => {
          return response as TaskModel[];
        })
      );
    }

    getTask(id: string): Observable<TaskModel> {
      return this.http.get(`${this.baseURl}get/${id}`).pipe(
        map((response: any) => {
          return response as TaskModel;
        })
      );
    }

    createTask(task: TaskModel): Observable<TaskModel> {
      return this.http.post(`${this.baseURl}create`, task).pipe(
        map((response: any) => {
          return response as TaskModel;
        })
      );
    }

    updateTask(task: TaskModel): Observable<TaskModel> {
      return this.http.put(`${this.baseURl}update/${task.id}`, task).pipe(
        map((response: any) => {
          return response as TaskModel;
        })
      );
    }

    deleteTask(id?: string): Observable<TaskModel> {
      return this.http.delete(`${this.baseURl}delete/${id}`).pipe(
        map((response: any) => {
          return response as TaskModel;
        })
      );
    }
}
