package com.main.damoim.main.search;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.main.damoim.R;

import java.net.URLEncoder;

public class SearchFragment extends Fragment{

    String result, userId;
    int userId_Num;
    Button search, search_word, search_dialog, search_image;
    EditText search_text;

    public WebView searchWebView;
    WebSettings search_WebView_Settings;

    public LinearLayout search_1, search_2;

    boolean search_word_status = true;
    boolean search_dialog_status = false;
    boolean search_image_status = false;

    //서버 url
    String serverURL;
    String mainClass;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        userId = getArguments().getString("userId");
        userId_Num = getArguments().getInt("userId_Num");
        serverURL = getArguments().getString("serverURL");
        mainClass = getArguments().getString("mainClass");

        search = (Button) view.findViewById(R.id.search);
        search_word = (Button) view.findViewById(R.id.searchWord);
        search_image = (Button) view.findViewById(R.id.searchImage);
        search_dialog = (Button) view.findViewById(R.id.searchDialog);

        search_text = (EditText) view.findViewById(R.id.searchText);

        search_word.setBackgroundColor(Color.TRANSPARENT);
        search_image.setBackgroundColor(Color.LTGRAY);
        search_dialog.setBackgroundColor(Color.LTGRAY);

        search = (Button) view.findViewById(R.id.search);

        search_1 = (LinearLayout) view.findViewById(R.id.layout_search_1);
        search_2 = (LinearLayout) view.findViewById(R.id.layout_search_2);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //search_1.setVisibility(View.INVISIBLE);
                //search_2.setVisibility((View.INVISIBLE));

                searchWebView = (WebView) view.findViewById(R.id.searchWebView);
                searchWebView.setWebViewClient(new WebViewClient());
                search_WebView_Settings = searchWebView.getSettings();
                search_WebView_Settings.setJavaScriptEnabled(true);
                search_WebView_Settings.setSupportMultipleWindows(false);
                search_WebView_Settings.getJavaScriptCanOpenWindowsAutomatically();
                search_WebView_Settings.setLoadWithOverviewMode(true);
                search_WebView_Settings.setUseWideViewPort(true);
                search_WebView_Settings.setSupportZoom(false);
                search_WebView_Settings.setBuiltInZoomControls(false);
                search_WebView_Settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                search_WebView_Settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                search_WebView_Settings.setDomStorageEnabled(true);

            if(search_text.getText().toString() != null) {
                try {
                    String word = search_text.getText().toString();

                    Toast.makeText(getContext(), word, Toast.LENGTH_SHORT).show();

                    searchWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewSearch.jsp?search=" + word + "&userId=" + userId);
                    //searchWebView.loadUrl("http://192.168.25.44:8181/parseJSP/viewSearch.jsp?search=" + word + "&userId=" + userId);
                    //earchWebView.loadUrl(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(getContext(), "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
            }

            }
        });

        search_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_dialog_status == true || search_image_status == true){
                    search_image_status = false;
                    search_image.setBackgroundColor(Color.LTGRAY);
                    search_dialog_status = false;
                    search_dialog.setBackgroundColor(Color.LTGRAY);
                    search_word_status = true;
                    search_word.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_word_status == true || search_dialog_status == true){
                    search_word_status = false;
                    search_word.setBackgroundColor(Color.LTGRAY);
                    search_dialog_status = false;
                    search_dialog.setBackgroundColor(Color.LTGRAY);
                    search_image_status = true;
                    search_image.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        search_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_word_status == true || search_image_status == true){
                    search_word_status = false;
                    search_word.setBackgroundColor(Color.LTGRAY);
                    search_image_status = false;
                    search_image.setBackgroundColor(Color.LTGRAY);
                    search_dialog_status = true;
                    search_dialog.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        return view;
    }
}