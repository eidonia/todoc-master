package com.cleanup.todoc.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;

import java.util.List;

public class TasksRepository {

    private TaskDAO taskDAO;
    private ProjectDAO projectDAO;
    private LiveData<List<TaskProject>> mAllTasks;
    private LiveData<List<TaskProject>> mAllTasksAsc;
    private LiveData<List<TaskProject>> mAllTasksDesc;
    private LiveData<List<TaskProject>> mAllTasksDate;
    private LiveData<List<TaskProject>> mAllTasksDateDesc;
    private LiveData<Project[]> mAllProjects;


    public TasksRepository(Application application) {
        ToDocDatabase db = ToDocDatabase.getINSTANCE(application);
        taskDAO = db.taskDAO();
        projectDAO = db.projectDAO();
        mAllTasks = taskDAO.getTask();
        mAllTasksAsc = taskDAO.getTaskAsc();
        mAllTasksDesc = taskDAO.getTaskDesc();
        mAllTasksDate = taskDAO.getTaskDate();
        mAllTasksDateDesc = taskDAO.getTaskDateDesc();
        mAllProjects = projectDAO.getProject();
    }

    public LiveData<List<TaskProject>> getAllTasks() {
        return mAllTasks;
    }

    public LiveData<List<TaskProject>> getTaskAsc() {
        return mAllTasksAsc;
    }

    public LiveData<List<TaskProject>> getTaskDesc() {
        return mAllTasksDesc;
    }

    public LiveData<List<TaskProject>> getTaskDate() {
        return mAllTasksDate;
    }

    public LiveData<List<TaskProject>> getTaskDateDesc() {
        return mAllTasksDateDesc;
    }

    public void insert(Task task) {
        ToDocDatabase.databaseWriterExecutor.execute(() -> {
            taskDAO.insertTask(task);
        });
    }

    public void delete(long id) {
        ToDocDatabase.databaseWriterExecutor.execute(() -> {
            taskDAO.deleteTask(id);
        });
    }

    public LiveData<Project[]> getProject() {
        return mAllProjects;
    }
}
