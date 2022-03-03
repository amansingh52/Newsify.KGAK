package com.maverick.newsify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Web_view extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ProgressBar probar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_View);
        probar = findViewById(R.id.probar);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);

        webView.loadUrl("https://analytics.google.com/analytics/web/?_ga=2.74191957.691391321.1646071832-1928928068.1646071832#/p304367477/realtime/overview?params=_u..nav%3Dmaui%26_u..comparisons%3D%5B%7B%22name%22:%22All%20Users%22,%22filters%22:%5B%7B%22isCaseSensitive%22:true,%22expression%22:%220%22,%22fieldName%22:%22audience%22%7D%5D%7D,%7B%22name%22:%22City%20includes%20Kharabwadi%22,%22filters%22:%5B%7B%22expression%22:%22Kharabwadi%22,%22fieldName%22:%22city%22%7D%5D%7D%5D");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                probar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                probar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}

