export class TaskModel {
  id?:          string;
  title?:       string;
  description?: string;
  status?:      string;
  createdAt?:   Date;
  updatedAt?:   Date;
}

export class TaskGroupModel {
  taskGroup: TaskModel[] = [];
}
