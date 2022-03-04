package com.maverick.newsify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDashboard extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getQuery();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();

    }
    private void getQuery(){
        categoryRVModalArrayList.add(new CategoryRVModal("Corona","https://images.unsplash.com/photo-1565478441918-ba8d56c559a9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80\n"));
        categoryRVModalArrayList.add(new CategoryRVModal("Student","https://images.unsplash.com/photo-1601026967915-281d3160683b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Tesla","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2072&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Apple","https://images.unsplash.com/photo-1529751480052-2f3f1839834e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));

        categoryRVAdapter.notifyDataSetChanged();
    }

    //    private void getNews(String category){
//        loadingPB.setVisibility(View.VISIBLE);
//        articlesArrayList.clear();
//        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=us&category="+ category +"&apiKey=cb0ecff5821d4ca2a2e4e09490f0ac50";
//        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=cb0ecff5821d4ca2a2e4e09490f0ac50";
//        String BASE_URL = "https://newsapi.org/";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
//        Call<NewsModal> call;
//        if(category.equals("All")){
//            call = retrofitAPI.getAllNews(url);
//        }else{
//            call = retrofitAPI.getNewsByCategory(categoryUrl);
//        }
//
//        call.enqueue(new Callback<NewsModal>() {
//            @Override
//            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
//                NewsModal newsModal = response.body();
//                loadingPB.setVisibility(View.GONE);
//                ArrayList<Articles> articles = newsModal.getArticles();
//                for(int i = 0; i < articles.size(); i++){
//                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
//
//                }
//
//                newsRVAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<NewsModal> call, Throwable t) {
//                Toast.makeText(UserDashboard.this,"Fail to get news", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
    private void getNews(String query){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
//        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=us&category="+ category +"&apiKey=cb0ecff5821d4ca2a2e4e09490f0ac50";
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=in&q="+ query +"&from=2022-03-02&sortBy=popularity&language=en&apiKey=8006f1e5052a42059e2e376412cda54f";
        String url = "https://newsapi.org/v2/top-headlines?country=in&sortBy=popularity&excludeDomains=stackoverflow.com&language=en&apiKey=8006f1e5052a42059e2e376412cda54f";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(query.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }else{
            call = retrofitAPI.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i = 0; i < articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }

                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(UserDashboard.this,"Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onCategoryClick(int position) {
        String query = categoryRVModalArrayList.get(position).getCategory();
        getNews(query);
    }
}

