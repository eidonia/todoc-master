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
    private LiveData<Project[]> mAllProjects;


    public TasksRepository(Application application) {
        ToDocDatabase db = ToDocDatabase.getINSTANCE(application);
        taskDAO = db.taskDAO();
        projectDAO = db.projectDAO();
        mAllTasks = taskDAO.getTask();
        mAllProjects = projectDAO.getProject();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
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
