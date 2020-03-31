package com.himanshu.questionare.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.himanshu.questionare.R;
import com.himanshu.questionare.utils.DateConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatTextView questionNumber, ques;
    AppCompatCheckBox opt1, opt2, opt3, opt4;
    AppCompatButton btnNext, finishBtn;
    List<String> myQues1 = new ArrayList<String>();
    List<String> myQues2 = new ArrayList<String>();
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        init();
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        btnNext.setOnClickListener(this);
        finishBtn.setOnClickListener(this);

    }

    private void init() {
        questionNumber = findViewById(R.id.questionNumber);
        ques = findViewById(R.id.ques);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);
        btnNext = findViewById(R.id.btnNext);
        finishBtn = findViewById(R.id.finishBtn);

        myQues1.add("Question 1");
        myQues1.add("Who is the best cricketer in the world?");
        myQues1.add("Sachin Tendulkar");
        myQues1.add("Virat Kolli");
        myQues1.add("Adam Gilchirst");
        myQues1.add("Jacques Kallis");

        myQues2.add("Question 2");
        myQues2.add("What are the colors in the national flag?");
        myQues2.add("White");
        myQues2.add("Yellow");
        myQues2.add("Orange");
        myQues2.add("Green");
        
        questionNumber.setText(myQues1.get(0));
        ques.setText(myQues1.get(1));
        opt1.setText(myQues1.get(2));
        opt2.setText(myQues1.get(3));
        opt3.setText(myQues1.get(4));
        opt4.setText(myQues1.get(5));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                onNextMethod();
                break;
        }
    }

    private void onNextMethod() {
        String checkedData = "";
        int CB_count = 0;
        if (opt1.isChecked()) {
            CB_count = CB_count + 1;
            checkedData = opt1.getText().toString();
        }
        if (opt2.isChecked()) {
            CB_count = CB_count + 1;
            checkedData = checkedData+","+opt2.getText().toString();
        }
        if (opt3.isChecked()) {
            CB_count = CB_count + 1;
            checkedData = checkedData+","+opt3.getText().toString();
        }
        if (opt4.isChecked()) {
            CB_count = CB_count + 1;
            checkedData = checkedData+","+opt4.getText().toString();
        }
        if (CB_count == 1) {

            if(questionNumber.getText().toString().equals("Question 1")) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Question1", myQues1.get(1));
                String first = String.valueOf(checkedData.charAt(0));
                if(first.equals(",")){
                    String data = checkedData.substring(1);
                    editor.putString("answer1", data);
                }else{
                    editor.putString("answer1", checkedData);
                }
                editor.commit();

                questionNumber.setText(myQues2.get(0));
                ques.setText(myQues2.get(1));
                opt1.setText(myQues2.get(2));
                opt2.setText(myQues2.get(3));
                opt3.setText(myQues2.get(4));
                opt4.setText(myQues2.get(5));
            }
            

            if (opt1.isChecked()) {
                opt1.setChecked(false);
            }
            if (opt2.isChecked()) {
                opt2.setChecked(false);
            }
            if (opt3.isChecked()) {
                opt3.setChecked(false);
            }
            if (opt4.isChecked()) {
                opt4.setChecked(false);
            }
        } else if(questionNumber.getText().toString().equals("Question 1")){
            Toast.makeText(getApplicationContext(), "You only can select 1 checkbox", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Question2", myQues2.get(1));
            String first = String.valueOf(checkedData.charAt(0));
            if(first.equals(",")){
                String data = checkedData.substring(1);
                editor.putString("answer2", data);
            }else{
                editor.putString("answer2", checkedData);
            }
            editor.commit();
            startActivity(new Intent(QuestActivity.this, Summary.class));
            finish();
        }
    }
}
