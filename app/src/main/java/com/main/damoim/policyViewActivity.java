package com.main.damoim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class policyViewActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    String userId, serverURL, mainClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_view);

        Intent intent = getIntent();
        if(intent != null){
            userId = intent.getStringExtra("userId");
            serverURL = intent.getStringExtra("serverURL");
            mainClass = intent.getStringExtra("mainClass");
        }

        webView = (WebView) findViewById(R.id.unpopularWebView);

        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.getJavaScriptCanOpenWindowsAutomatically();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);

        webView.loadUrl("http://"+serverURL+":8181/"+mainClass+"/viewUnpopular.jsp?userId="+userId);
    }
}