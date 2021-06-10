package com.applycreditcard.coinedone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applycreditcard.coinedone.Adapters.QuestionRVAdapter;
import com.applycreditcard.coinedone.Modal.QuestionRVModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements QuestionRVAdapter.OnQuestionClickInterface {

    private ArrayList<QuestionRVModal> questionRVModalArrayList;
    private QuestionRVAdapter questionRVAdapter;
    private ProgressBar loadingPB;
    String url = "https://cdn.contentful.com/spaces/oyl9jnz1wloz/environments/master/entries?access_token=k7rY6ZvuydrzmKmyv7s-7bcKoa498Q8dC4m4sAWAyxI&content_type=question";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        loadingPB = view.findViewById(R.id.idPBLoading);
        RecyclerView questionRV = view.findViewById(R.id.idRVQuestions);
        //initializing recycler view and array list.
        questionRVModalArrayList = new ArrayList<>();
        questionRVAdapter = new QuestionRVAdapter(questionRVModalArrayList, getContext(), this::onQuestionClick);
        questionRV.setLayoutManager(new LinearLayoutManager(getContext()));
        questionRV.setAdapter(questionRVAdapter);
        getAllQuestions();
        return view;
    }

    private void getAllQuestions() {
        loadingPB.setVisibility(View.VISIBLE);
        questionRVModalArrayList.clear();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getContext());
        //making a call for getting json object from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {
                    //extracting the data from the API and passing it to modal class.
                    JSONArray itemsArray = response.getJSONArray("items");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        StringBuilder bigPicture = new StringBuilder();
                        StringBuilder concerned = new StringBuilder();
                        StringBuilder awareness = new StringBuilder();
                        StringBuilder canDo = new StringBuilder();
                        JSONObject itemObj = itemsArray.getJSONObject(i);
                        JSONObject fieldObj = itemObj.getJSONObject("fields");
                        String question = fieldObj.getString("question");
                        String shareLink = fieldObj.getString("shareLink");
                        String time = fieldObj.getString("time");
                        JSONArray tagsArray = fieldObj.getJSONArray("tags");
                        ArrayList<String> tagsList = new ArrayList<>();
                        for (int j = 0; j < tagsArray.length(); j++) {
                            tagsList.add(tagsArray.get(j).toString());
                        }
                        JSONArray imageArray = fieldObj.getJSONArray("imgeUrls");
                        ArrayList<String> imageList = new ArrayList<>();
                        for (int k = 0; k < imageArray.length(); k++) {
                            imageList.add(imageArray.get(k).toString());
                        }

                        JSONArray contentArray = fieldObj.getJSONObject("bigPicture").getJSONArray("content");
                        for (int l = 0; l < contentArray.length(); l++) {
                            JSONObject contObj = contentArray.getJSONObject(l);
                            bigPicture.append(contObj.getJSONArray("content").getJSONObject(0).getString("value"));
                            bigPicture.append("\n\n");
                        }

                        JSONArray content2Array = fieldObj.getJSONObject("concerned").getJSONArray("content");
                        for (int l = 0; l < content2Array.length(); l++) {
                            JSONObject contObj = content2Array.getJSONObject(l);
                            concerned.append(contObj.getJSONArray("content").getJSONObject(0).getString("value"));
                            concerned.append("\n\n");
                        }

                        JSONArray content3Array = fieldObj.getJSONObject("awareness").getJSONArray("content");
                        for (int l = 0; l < content3Array.length(); l++) {
                            JSONObject contObj = content3Array.getJSONObject(l);
                            awareness.append(contObj.getJSONArray("content").getJSONObject(0).getString("value"));
                            awareness.append("\n\n");
                        }

                        JSONArray content4Array = fieldObj.getJSONObject("canDo").getJSONArray("content");
                        for (int l = 0; l < content4Array.length(); l++) {
                            JSONObject contObj = content4Array.getJSONObject(l);
                            canDo.append(contObj.getJSONArray("content").getJSONObject(0).getString("value"));
                            canDo.append("\n\n");
                        }
                        questionRVModalArrayList.add(new QuestionRVModal(question, time, bigPicture.toString(), imageList, concerned.toString(), awareness.toString(), canDo.toString(), shareLink, tagsList));
                    }
                    questionRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Fail to get data..", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onQuestionClick(int position) {
        //opening a single page activity and passing data to it.
        Intent i = new Intent(getContext(), SinglePageActivity.class);
        i.putExtra("question", questionRVModalArrayList.get(position).getQuestion());
        i.putExtra("awareness", questionRVModalArrayList.get(position).getAwareness());
        i.putExtra("bigPic", questionRVModalArrayList.get(position).getBigPic());
        i.putExtra("canDo", questionRVModalArrayList.get(position).getCanDo());
        i.putExtra("concerned", questionRVModalArrayList.get(position).getConcerned());
        i.putExtra("time", questionRVModalArrayList.get(position).getTime());
        i.putExtra("shareLink", questionRVModalArrayList.get(position).getShareLink());
        i.putExtra("tags", questionRVModalArrayList.get(position).getTags());
        i.putExtra("images", questionRVModalArrayList.get(position).getImages());
        getContext().startActivity(i);
    }
}