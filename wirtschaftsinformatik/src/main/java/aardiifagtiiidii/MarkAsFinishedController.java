package aardiifagtiiidii;

import java.awt.Window;
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

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;


@Stateless
@Named
public class MarkAsFinishedController {

	@Inject
	private BusinessProcess businessProcess;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private TaskListEntity taskList;

	@Inject
	private TodoListController todoListController;

	@Inject
	private TaskForm taskForm;
	
	private static Logger LOGGER = Logger.getLogger(TodoListController.class
			.getName());

	public void markAsFinished(TodoItemEntity task) {
			task.completed = !(entityManager.find(TodoItemEntity.class, task.id).completed);
			todoListController.mergeItemAndCompleteTask(task); 
		
	}

}
