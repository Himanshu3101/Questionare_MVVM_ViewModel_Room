package com.himanshu.questionare.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.himanshu.questionare.database.models.Questionaire;
import com.himanshu.questionare.repositories.QuestionaireRepository;

import java.util.List;

public class questionaireViewModel extends AndroidViewModel {

    private QuestionaireRepository questionaireRepository;

    private LiveData<List<Questionaire>> mAllData;

    public questionaireViewModel(@NonNull Application application) {
        super(application);
        questionaireRepository = new QuestionaireRepository(application);
        mAllData = questionaireRepository.getAllData();
    }

    public LiveData<List<Questionaire>> getAllWords() { return mAllData; }

    public void insert(Questionaire questionaire) {
        questionaireRepository.insert(questionaire);
    }

}
