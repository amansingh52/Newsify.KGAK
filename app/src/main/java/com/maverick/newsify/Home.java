package com.maverick.newsify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
//    public void MainActivity(View view){
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//
//        Pair[] pairs = new Pair[1];
//
//        pairs[0] = new Pair<View, String>(findViewById(R.id.home_btn),"transition_login");
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Home.this, pairs);
//            startActivity(intent, options.toBundle());
//        }
//        else {
//            startActivity(intent);
//        }
//    }
}