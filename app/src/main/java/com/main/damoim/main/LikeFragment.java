package com.main.damoim.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.main.damoim.R;

public class LikeFragment extends Fragment {

    String userId;
    int userId_Num;
    String serverURL;
    String mainClass;
    public WebView likeWebView;
    public WebSettings like_WebView_Settings;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_like, container, false);
        userId = getArguments().getString("userId");
        userId_Num = getArguments().getInt("userId_Num");
        serverURL = getArguments().getString("serverURL");
        mainClass = getArguments().getString("mainClass");

        likeWebView = (WebView) view.findViewById(R.id.likeWebView);

        likeWebView.setWebViewClient(new WebViewClient());
        like_WebView_Settings = likeWebView.getSettings();
        like_WebView_Settings.setJavaScriptEnabled(true);
        like_WebView_Settings.setSupportMultipleWindows(false);
        like_WebView_Settings.getJavaScriptCanOpenWindowsAutomatically();
        like_WebView_Settings.setLoadWithOverviewMode(true);
        like_WebView_Settings.setUseWideViewPort(true);
        like_WebView_Settings.setSupportZoom(false);
        like_WebView_Settings.setBuiltInZoomControls(false);
        like_WebView_Settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        like_WebView_Settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        like_WebView_Settings.setDomStorageEnabled(true);
        like_WebView_Settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        like_WebView_Settings.setEnableSmoothTransition(true);

        likeWebView.loadUrl("http://" + serverURL + ":8181/" + mainClass + "/viewLike.jsp?userId=" + userId);
        return view;
    }
}
