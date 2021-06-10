package com.applycreditcard.coinedone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import im.crisp.client.ChatActivity;
import im.crisp.client.Crisp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating and initializing variable for bottom nav view.
        BottomNavigationView bottomNav = findViewById(R.id.idBNV);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.idFLContainer, new HomeFragment()).commit();
    }

    //adding on nav listener for bottom navigation view.
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.idBNVExplore:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.idBNVChat:
                    selectedFragment = new ChatsFragment();
                    break;
            }
            //launching new fragment on clicking the bottom nav option
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.idFLContainer, selectedFragment)
                    .commit();
            return true;
        }
    };


}