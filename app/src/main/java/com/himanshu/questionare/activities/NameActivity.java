package com.himanshu.questionare.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.himanshu.questionare.R;
import com.himanshu.questionare.utils.DateConverter;

public class NameActivity extends AppCompatActivity  implements View.OnClickListener {
    AppCompatEditText editTextUserPin;
    AppCompatButton submitBtn;
    public static final String mypreference = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        initialize();
        submitBtn.setOnClickListener(this);
    }

    public void initialize() {
        editTextUserPin = findViewById(R.id.editTextUserPin);
        submitBtn = findViewById(R.id.submitBtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitBtn:
                onSubmitClick();
                break;
        }
    }

    private void onSubmitClick() {
        if (TextUtils.isEmpty(editTextUserPin.getText())) {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else {
            String word = editTextUserPin.getText().toString();
           if(!word.equals("")&&!word.equals(null)){
               String dateTime = DateConverter.getCurrentDateTime();


               SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedpreferences.edit();
               editor.putString("date", dateTime);
               editor.putString("name", word);
               editor.commit();
               startActivity(new Intent(NameActivity.this, QuestActivity.class));
               finish();
           }
        }

    }


}