package com.applycreditcard.coinedone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.applycreditcard.coinedone.Adapters.SliderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SinglePageActivity extends AppCompatActivity {
    //creating variables for all the views.
    String question, awareness, bigPic, canDo, concerned, time, shareLink;
    ArrayList<String> tags, images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page);
        //initializing all the variables for views.
        TextView questionTV = findViewById(R.id.idTVQuestion);
        FloatingActionButton shareFAB = findViewById(R.id.idFABShare);
        TextView awarenessTV = findViewById(R.id.idTVAwareness);
        TextView bigPicTV = findViewById(R.id.idTVBigPic);
        Button shareSolBtn = findViewById(R.id.idBtnShareSol);
        TextView canDoTV = findViewById(R.id.idTVCanDo);
        TextView concernedTV = findViewById(R.id.idTVConcerned);
        SliderView sliderView = findViewById(R.id.idSVSlider);
        //getting all the data passed through intent.
        shareLink = getIntent().getStringExtra("shareLink");
        question = getIntent().getStringExtra("question");
        awareness = getIntent().getStringExtra("awareness");
        bigPic = getIntent().getStringExtra("bigPic");
        canDo = getIntent().getStringExtra("canDo");
        concerned = getIntent().getStringExtra("concerned");
        time = getIntent().getStringExtra("time");
        tags = getIntent().getStringArrayListExtra("tags");
        images = getIntent().getStringArrayListExtra("images");
        //setting data to all views.
        questionTV.setText(question);
        awarenessTV.setText(awareness);
        bigPicTV.setText(bigPic);
        canDoTV.setText(canDo);
        concernedTV.setText(concerned);
        //adding adapter for slider view and passing list to it.
        SliderAdapter sliderAdapter = new SliderAdapter(this, images);
        //setting adapter to slider view.
        sliderView.setSliderAdapter(sliderAdapter);
        //setting auto cycle direction for slider view.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        //setting scroll time for slider view.
        sliderView.setScrollTimeInSec(3);
        //setting auto cycle for slider view.
        sliderView.setAutoCycle(true);
        //setting auto cycle for slider view.
        sliderView.startAutoCycle();
        //adding on click listener for share solution button.
        shareSolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SinglePageActivity.this, "Share your solution here..", Toast.LENGTH_SHORT).show();
            }
        });
        //adding on click listener for share floating action button.
        shareFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling an intent to share the URL.
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, shareLink);
                startActivity(Intent.createChooser(share, "Share this question"));
            }
        });

    }
}