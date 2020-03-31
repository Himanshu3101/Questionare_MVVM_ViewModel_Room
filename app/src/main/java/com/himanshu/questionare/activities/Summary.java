package com.himanshu.questionare.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.questionare.R;
import com.himanshu.questionare.adapters.AdapterSummary;
import com.himanshu.questionare.database.models.Questionaire;
import com.himanshu.questionare.viewModels.questionaireViewModel;

import java.util.ArrayList;
import java.util.List;

public class Summary extends AppCompatActivity implements View.OnClickListener{

    private questionaireViewModel mquestionaireViewModel;
    AppCompatTextView userName;
    RecyclerView summaryRecycler;
    public static final String mypreference = "mypref";
    List<Questionaire> list = new ArrayList<Questionaire>();
    AppCompatButton btnHistory,finishBtn;
    String date;
    AdapterSummary adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        initialize();
        sharedPrefrenceWork();
        finishBtn.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
    }

    private void sharedPrefrenceWork() {
        SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userName.setText(sharedpreferences.getString("name", "Hiey!"+""));
        date = sharedpreferences.getString("date", "");
        String ques1 = sharedpreferences.getString("Question1", "");
        String ques2 = sharedpreferences.getString("Question2", "");
        String ans1 = sharedpreferences.getString("answer1", "");
        String ans2 = sharedpreferences.getString("answer2", "");

        Questionaire questionaire = new Questionaire();
        questionaire.setQuestions(ques1);
        questionaire.setAnswers(ans1);
        list.add(0,questionaire);
        Questionaire questionaire1 = new Questionaire();
        questionaire1.setQuestions(ques2);
        questionaire1.setAnswers(ans2);
        list.add(1,questionaire1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new AdapterSummary(Summary.this, list);
        summaryRecycler.setLayoutManager(layoutManager);
        summaryRecycler.setAdapter(adapter);
        summaryRecycler.scheduleLayoutAnimation();

    }

    public void initialize() {
        userName = findViewById(R.id.userName);
        summaryRecycler = findViewById(R.id.summaryRecycler);
        btnHistory = findViewById(R.id.btnHistory);
        finishBtn = findViewById(R.id.finishBtn);
        mquestionaireViewModel = new questionaireViewModel(getApplication());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHistory:
                onHistoryMethod();
                break;
            case R.id.finishBtn:
                onFinishMethod();
                break;
        }
    }

    private void onHistoryMethod() {
        mquestionaireViewModel.getAllWords().observe(this, new Observer<List<Questionaire>>() {
            @Override
            public void onChanged(@Nullable final List<Questionaire> dataQues) {
                // Update the cached copy of the words in the adapter.
                adapter.setData(dataQues);
            }
        });

    }

    private void onFinishMethod() {
        try {
            for (int i = 0; i < list.size(); i++) {
                Questionaire questionaire = new Questionaire();
                questionaire.setName(userName.getText().toString());
                questionaire.setQuestions(list.get(i).getQuestions());
                questionaire.setAnswers(list.get(i).getAnswers());
                questionaire.setCreatedAt(date);
                mquestionaireViewModel.insert(questionaire);
            }
            startActivity(new Intent(Summary.this, NameActivity.class));
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
