package com.himanshu.questionare.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;


import com.himanshu.questionare.database.Daos.NoteDao;
import com.himanshu.questionare.database.NoteDatabase;
import com.himanshu.questionare.database.models.Questionaire;
import com.himanshu.questionare.utils.DateConverter;

import java.util.List;

public class QuestionaireRepository {

    private NoteDao noteDao;
    private LiveData<List<Questionaire>> mQLiveData;

    public QuestionaireRepository(Application application) {
        NoteDatabase db = NoteDatabase.getDatabase(application);
        noteDao = db.daoAccess();
        mQLiveData = noteDao.fetchAllTasks();
    }

    public LiveData<List<Questionaire>> getAllData() {
        return mQLiveData;
    }

    public void insert (Questionaire questionaire) {
        new insertAsyncTask(noteDao).execute(questionaire);
    }

    private static class insertAsyncTask extends AsyncTask<Questionaire, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Questionaire... params) {
            mAsyncTaskDao.insertTask(params[0]);
            return null;
        }
    }
}
