package com.applycreditcard.coinedone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import im.crisp.client.ChatActivity;
import im.crisp.client.Crisp;

public class ChatsFragment extends Fragment {

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        //configuring the crisp chat SDK.
        Crisp.configure(getContext(), "4008fa87-9b01-426f-81fb-cec6e8a51481");
        //launching a new intent for crisp chat SDK.
        Intent crispIntent = new Intent(getContext(), ChatActivity.class);
        startActivity(crispIntent);
        return view;
    }
}