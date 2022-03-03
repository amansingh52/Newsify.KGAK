package com.maverick.newsify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Analytics extends AppCompatActivity {

    WebView webView;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        webView = findViewById(R.id.webView);

        loader = findViewById(R.id.webViewLoader);

        Intent intent = getIntent();



        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://analytics.google.com/analytics/web/?_ga=2.74191957.691391321.1646071832-1928928068.1646071832#/p304367477/realtime/overview?params=_u..nav%3Dmaui%26_u..comparisons%3D%5B%7B%22name%22:%22All%20Users%22,%22filters%22:%5B%7B%22isCaseSensitive%22:true,%22expression%22:%220%22,%22fieldName%22:%22audience%22%7D%5D%7D,%7B%22name%22:%22City%20includes%20Kharabwadi%22,%22filters%22:%5B%7B%22expression%22:%22Kharabwadi%22,%22fieldName%22:%22city%22%7D%5D%7D%5D");
        if (webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}
