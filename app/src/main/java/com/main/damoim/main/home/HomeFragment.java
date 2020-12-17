package com.main.damoim.main.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.main.damoim.R;

import java.sql.Connection;
import java.sql.DriverManager;

public class HomeFragment extends Fragment {

    Button page_New, page_Hot, page_recommand;
    public WebView home_WebView;
    WebSettings home_WebView_Settings;

    String userId;
    String serverURL;
    String mainClass;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        userId = getArguments().getString("userId");
        serverURL = getArguments().getString("serverURL");
        mainClass = getArguments().getString("mainClass");

        page_New = (Button) view.findViewById(R.id.newTab);
        page_Hot = (Button) view.findViewById(R.id.hotTab);
        page_recommand = (Button) view.findViewById(R.id.recommandTab);

        home_WebView = (WebView) view.findViewById(R.id.homeWebView);

        home_WebView.setWebViewClient(new WebViewClient());
        home_WebView_Settings = home_WebView.getSettings();
        home_WebView_Settings.setJavaScriptEnabled(true);
        home_WebView_Settings.setSupportMultipleWindows(false);
        home_WebView_Settings.getJavaScriptCanOpenWindowsAutomatically();
        home_WebView_Settings.setLoadWithOverviewMode(true);
        home_WebView_Settings.setUseWideViewPort(true);
        home_WebView_Settings.setSupportZoom(false);
        home_WebView_Settings.setBuiltInZoomControls(false);
        home_WebView_Settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        home_WebView_Settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        home_WebView_Settings.setDomStorageEnabled(true);


        //홈화면 시작시 페이지
       home_WebView.loadUrl("http://" + serverURL + ":8181/" + mainClass+"/viewHome_Category_Recommand.jsp?userId=" + userId);


       page_recommand.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               home_WebView.loadUrl("http://" + serverURL + ":8181/" + mainClass+"/viewHome_Category_Recommand.jsp?userId=" + userId);
           }
       });

        page_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    home_WebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewHome_New_Policy.jsp?userId=" + userId);
            }
        });

        page_Hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_WebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewHome_Hot_Policy.jsp?userId=" + userId);
            }
        });



        return view;
    }
}
