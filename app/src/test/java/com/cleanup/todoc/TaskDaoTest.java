package com.cleanup.todoc;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.ToDocDatabase;
import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TaskDAO taskDAO;
    private ProjectDAO projectDAO;
    private ToDocDatabase toDocDatabase;

    @Before
    public void initDb() {
        Context context = ApplicationProvider.getApplicationContext();
        toDocDatabase = Room.inMemoryDatabaseBuilder(context, ToDocDatabase.class).allowMainThreadQueries().build();
        taskDAO = toDocDatabase.taskDAO();
        projectDAO = toDocDatabase.projectDAO();
    }

    @After
    public void closeDb() {
        toDocDatabase.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        this.toDocDatabase.projectDAO().insert(Project.getAllProjects());
        Project[] projects = LiveDataUtilsTest.getValue(this.toDocDatabase.projectDAO().getProject());
        Project project = projects[0];
        Task test = new Task(project.getId(), "Tache1", new Date().getTime());
        this.toDocDatabase.taskDAO().insertTask(test);
        Task task = LiveDataUtilsTest.getValue(this.toDocDatabase.taskDAO().getTaskTest(test.getName()));
        assertEquals(task.getName(), test.getName());
    }
}
