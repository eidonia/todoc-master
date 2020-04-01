package com.cleanup.todoc.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TasksRepository {

    private TaskDAO taskDAO;
    private ProjectDAO projectDAO;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Task>> mAllTasksAsc;
    private LiveData<List<Task>> mAllTasksDesc;
    private LiveData<List<Task>> mAllTasksDate;
    private LiveData<List<Task>> mAllTasksDateDesc;
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

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public LiveData<List<Task>> getTaskAsc() {
        return mAllTasksAsc;
    }

    public LiveData<List<Task>> getTaskDesc() {
        return mAllTasksDesc;
    }

    public LiveData<List<Task>> getTaskDate() {
        return mAllTasksDate;
    }

    public LiveData<List<Task>> getTaskDateDesc() {
        return mAllTasksDateDesc;
    }

    public void insert(Task task) {
        Log.e(":insert", "" + task.getProjectId());
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
