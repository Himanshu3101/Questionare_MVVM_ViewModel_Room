package com.himanshu.questionare.database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.himanshu.questionare.database.models.Questionaire;
import com.himanshu.questionare.utils.DateConverter;

import java.util.List;
import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
@Entity
@TypeConverters(DateConverter.class)
public interface NoteDao {
    // Dao method to get all notes

//    @Query("SELECT * FROM questionaire_table")
//    LiveData<List<Questionaire>> getAllNotes();

    @Query("SELECT * FROM questionaire_table ORDER BY created_at desc")
    LiveData<List<Questionaire>> fetchAllTasks();

    // Dao method to insert questionaire
    @Insert(onConflict = REPLACE)
    void insertTask(Questionaire questionaire);

    /*// Dao method to delete questionaire
    @Delete
    void deleteNote(Questionaire questionaire);

    @Query("SELECT * FROM questionaire_table WHERE name =:taskId")
    LiveData<Questionaire> getTask(int taskId);*/
}
