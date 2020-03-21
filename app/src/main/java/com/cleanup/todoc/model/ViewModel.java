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
    private LiveData<Project[]> mAllProjects;

    public ViewModel(@NonNull Application application) {
        super(application);
        mRepos = new TasksRepository(application);
        mAllTasks = mRepos.getAllTasks();
        mAllProjects = mRepos.getProject();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
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
