package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTask();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertTask(Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    void deleteTask(long id);

    @Query("SELECT * FROM Task WHERE name = :name")
    LiveData<Task> getTaskTest(String name);
}

