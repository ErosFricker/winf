package aardiifagtiiidii;


import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="tasklist")
@SessionScoped
public class TaskListEntity {
	private static final long serialVersionUID = 1L;
	
	
	private static ArrayList<TodoItemEntity> todolist = new ArrayList<TodoItemEntity>();
	
	
	public ArrayList<TodoItemEntity> getTodolist() {
		return todolist;
	}


	public static void setTodolist(ArrayList<TodoItemEntity> todolist) {
		TaskListEntity.todolist = todolist;
	}

}
