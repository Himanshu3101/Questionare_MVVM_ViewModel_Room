package com.himanshu.questionare.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.himanshu.questionare.utils.DateConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "questionaire_table")
public class Questionaire implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "questions")
    private String questions;
    @ColumnInfo(name = "answers")
    private String answers;
    //type converter for date
    @ColumnInfo(name = "created_at")
    private String createdAt;

    public Questionaire() {}


    public Questionaire(String name, String questions, String answers, String createdAt) {
        this.name = name;
        this.questions = questions;
        this.answers = answers;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
