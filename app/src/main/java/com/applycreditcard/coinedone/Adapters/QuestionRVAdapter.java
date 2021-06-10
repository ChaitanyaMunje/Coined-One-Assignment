package com.applycreditcard.coinedone.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.applycreditcard.coinedone.Modal.QuestionRVModal;
import com.applycreditcard.coinedone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionRVAdapter extends RecyclerView.Adapter<QuestionRVAdapter.ViewHolder> {
    private final ArrayList<QuestionRVModal> questionRVModalArrayList;
    private final Context context;
    private final OnQuestionClickInterface onQuestionClickInterface;

    public QuestionRVAdapter(ArrayList<QuestionRVModal> questionRVModalArrayList, Context context, OnQuestionClickInterface onQuestionClickInterface) {
        this.questionRVModalArrayList = questionRVModalArrayList;
        this.context = context;
        this.onQuestionClickInterface = onQuestionClickInterface;
    }

    @NonNull
    @Override
    public QuestionRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.problem_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRVAdapter.ViewHolder holder, int position) {
        QuestionRVModal questionRVModal = questionRVModalArrayList.get(position);
        holder.questionTV.setText(questionRVModal.getQuestion());
        holder.timeTV.setText(questionRVModal.getTime());
        if (questionRVModal.getImages().size() != 0) {
            Picasso.get().load(questionRVModal.getImages().get(0)).into(holder.questionIV);
        }
        ArrayList<String> tagsRVList = questionRVModal.getTags();
        TagsRVAdapter tagsRVAdapter = new TagsRVAdapter(tagsRVList);
        holder.tagsRV.setAdapter(tagsRVAdapter);
        holder.questionCV.setCardBackgroundColor(generateRandomColor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuestionClickInterface.onQuestionClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionTV;
        private final TextView timeTV;
        private final CircleImageView questionIV;
        private final RecyclerView tagsRV;
        private final CardView questionCV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagsRV = itemView.findViewById(R.id.idRVTags);
            questionTV = itemView.findViewById(R.id.idTVQuestion);
            timeTV = itemView.findViewById(R.id.idTVTime);
            questionIV = itemView.findViewById(R.id.idIVQuestion);
            questionCV = itemView.findViewById(R.id.idCVQuestion);
        }
    }

    public int generateRandomColor() {
        final Random mRandom = new Random(System.currentTimeMillis());

        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    public interface OnQuestionClickInterface {
        void onQuestionClick(int position);
    }
}
