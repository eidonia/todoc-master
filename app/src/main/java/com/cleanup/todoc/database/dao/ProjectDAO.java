package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM Project")
    LiveData<Project[]> getProject();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project... project);
}
