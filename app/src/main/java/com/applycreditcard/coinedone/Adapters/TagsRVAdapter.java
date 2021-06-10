package com.applycreditcard.coinedone.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applycreditcard.coinedone.R;

import java.util.ArrayList;

public class TagsRVAdapter extends RecyclerView.Adapter<TagsRVAdapter.ViewHolder> {
    private final ArrayList<String> tagsList;

    public TagsRVAdapter(ArrayList<String> tagsList) {
        this.tagsList = tagsList;
    }

    @NonNull
    @Override
    public TagsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsRVAdapter.ViewHolder holder, int position) {
        holder.tagsTV.setText(tagsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tagsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tagsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagsTV = itemView.findViewById(R.id.idTVTag);
        }
    }
}
