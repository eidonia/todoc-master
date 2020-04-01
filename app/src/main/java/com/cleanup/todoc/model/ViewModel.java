package com.cleanup.todoc.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TasksRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TasksRepository mRepos;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Task>> mAllTasksAsc;
    private LiveData<List<Task>> mAllTasksDesc;
    private LiveData<List<Task>> mAllTasksDate;
    private LiveData<List<Task>> mAllTasksDateDesc;
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

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public LiveData<List<Task>> getAllTasksAsc() {
        return mAllTasksAsc;
    }

    public LiveData<List<Task>> getAllTasksDesc() {
        return mAllTasksDesc;
    }

    public LiveData<List<Task>> getAllTasksDate() {
        return mAllTasksDate;
    }

    public LiveData<List<Task>> getAllTasksDateDesc() {
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
