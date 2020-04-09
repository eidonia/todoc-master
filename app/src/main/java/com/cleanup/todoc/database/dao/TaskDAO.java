package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;

import java.util.List;

@Dao
public interface TaskDAO {

    @Transaction
    @Query("SELECT * FROM Task")
    LiveData<List<TaskProject>> getTask();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertTask(Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    void deleteTask(long id);

    @Query("SELECT * FROM Task WHERE name = :name")
    LiveData<TaskProject> getTaskTest(String name);

    @Query("SELECT * FROM Task ORDER BY name")
    LiveData<List<TaskProject>> getTaskAsc();

    @Query("SELECT * FROM Task ORDER BY name DESC")
    LiveData<List<TaskProject>> getTaskDesc();

    @Query("SELECT * FROM Task ORDER BY createdAt")
    LiveData<List<TaskProject>> getTaskDate();

    @Query("SELECT * FROM Task ORDER BY createdAt DESC")
    LiveData<List<TaskProject>> getTaskDateDesc();
}

