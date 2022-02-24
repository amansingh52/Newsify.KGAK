package com.maverick.newsify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UserDashboard extends AppCompatActivity {
    RecyclerView CardRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //hooks
        CardRecyclerview=findViewById(R.id.CardRecyclerview);
        CardRecyclerview();
    }

    private void CardRecyclerview() {
        CardRecyclerview.setHasFixedSize(true);
        CardRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
}