package aardiifagtiiidii;

import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.identity.Authentication;


@Stateless
@Named
public class TodoListController {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private TaskForm taskForm;

	private TodoItemEntity currentTask = new TodoItemEntity();

	private static Logger LOGGER = Logger.getLogger(TodoListController.class
			.getName());

	public TodoItemEntity persistTask(String name, String priority) {
		TaskListEntity.setTodolist((ArrayList<TodoItemEntity>) entityManager
				.createQuery("SELECT t from TodoItemEntity t").getResultList());
		TodoItemEntity task = new TodoItemEntity();
		task.setName(name);
		task.setPriority(Integer.parseInt(priority));
		task.setCompleted(false);
		entityManager.persist(task);
		entityManager.flush();
		LOGGER.log(Level.SEVERE, "Persisted task " + task.getName()
				+ "with priority: " + task.getPriority().toString());
		TaskListEntity.setTodolist((ArrayList<TodoItemEntity>) entityManager
				.createQuery("SELECT t from TodoItemEntity t").getResultList());
		return task;

	}

	public void updateTable(DelegateExecution delegateExecution) {

		TaskListEntity.setTodolist((ArrayList<TodoItemEntity>) entityManager
				.createQuery("SELECT t from TodoItemEntity t").getResultList());

	}

	public TodoItemEntity getTodoItem(Long itemId) {
		return entityManager.find(TodoItemEntity.class, itemId);
	}

	public void mergeItemAndCompleteTask(TodoItemEntity task) {
		// Merge detached order entity with current persisted state
		LOGGER.log(Level.SEVERE, "Task: "+ task.name + ", Completed: "+task.completed);
		entityManager.merge(task);
		entityManager.flush();

	}

}
