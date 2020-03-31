package com.himanshu.questionare.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.questionare.R;
import com.himanshu.questionare.activities.Summary;
import com.himanshu.questionare.database.models.Questionaire;

import java.util.Collections;
import java.util.List;

public class AdapterSummary extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Questionaire> listQuestions;
    private Activity summary;
    public AdapterSummary(Summary summary, List<Questionaire> list) {
        this.summary = summary;
        this.listQuestions = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_summary, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.quest.setText(listQuestions.get(position).getQuestions());
        itemViewHolder.answ.setText(listQuestions.get(position).getAnswers());
    }

    public void setData(List<Questionaire> questData){
        listQuestions = questData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (listQuestions != null)
            return listQuestions.size();
        else return 0;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView quest, answ;

        public ItemViewHolder(View itemView) {
            super(itemView);

            quest = itemView.findViewById(R.id.quest);
            answ = itemView.findViewById(R.id.answ);
        }
    }
}
