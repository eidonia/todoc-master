package com.cleanup.todoc.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TasksRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TasksRepository mRepos;
    private LiveData<List<TaskProject>> mAllTasks;
    private LiveData<List<TaskProject>> mAllTasksAsc;
    private LiveData<List<TaskProject>> mAllTasksDesc;
    private LiveData<List<TaskProject>> mAllTasksDate;
    private LiveData<List<TaskProject>> mAllTasksDateDesc;
    private LiveData<Project[]> mAllProjects;

    public ViewModel(@NonNull Application application) {
        super(application);
        mRepos = new TasksRepository(application);
        mAllTasks = mRepos.getAllTasks();
        mAllProjects = mRepos.getProject();
        mAllTasksAsc = mRepos.getTaskAsc();
        mAllTasksDesc = mRepos.getTaskDesc();
        mAllTasksDate = mRepos.getTaskDate();
        mAllTasksDateDesc = mRepos.getTaskDateDesc();
    }

    public LiveData<List<TaskProject>> getAllTasks() {
        return mAllTasks;
    }

    public LiveData<List<TaskProject>> getAllTasksAsc() {
        return mAllTasksAsc;
    }

    public LiveData<List<TaskProject>> getAllTasksDesc() {
        return mAllTasksDesc;
    }

    public LiveData<List<TaskProject>> getAllTasksDate() {
        return mAllTasksDate;
    }

    public LiveData<List<TaskProject>> getAllTasksDateDesc() {
        return mAllTasksDateDesc;
    }

    public LiveData<Project[]> getAllProjects() {
        return mAllProjects;
    }

    public void insert(Task task) {
        mRepos.insert(task);
    }

    public void delete(long id) {
        mRepos.delete(id);
    }
}
