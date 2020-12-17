package com.main.damoim.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.main.damoim.R;
import com.main.damoim.main.category.CategoryFragment;
import com.main.damoim.main.home.HomeFragment;
import com.main.damoim.main.search.SearchFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    private Fragment category, search, home, like, mypage;

    private CategoryFragment fragment_Category;
    private SearchFragment fragment_Search;
    private HomeFragment fragment_Home;
    private LikeFragment fragment_LIke;
    private MyPageFragment fragment_MyPage;

    private FragmentTransaction transaction;

    Button eye_tracking_on_off;
    boolean eye_tracking_status;

    SharedPreferences data;
    SharedPreferences.Editor editor;

    boolean SAVE_LOGIN_DATA;
    String userId;
    int userId_Num;

    //서버 주소와 클래스 설정
    String serverURL= "61.245.226.108";
    String mainClass = "damoimServer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null){
            SAVE_LOGIN_DATA = intent.getBooleanExtra("SAVE_LOGIN_DATA", false);
            userId = intent.getStringExtra("userId");
        }


        eye_tracking_on_off = (Button) findViewById(R.id.eyeTrackingOnOff);

        data = getSharedPreferences("data", 0);
        editor = data.edit();

        if(data.getBoolean("eye_tracking_status", true)){
            eye_tracking_on_off.setText("Eye_ON");
            eye_tracking_status = true;
        }
        else if(data.getBoolean("eye_tracking_status", false)){
            eye_tracking_on_off.setText("Eye_OFF");
            eye_tracking_status = false;
        }


        eye_tracking_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eye_tracking_status == true){
                    eye_tracking_on_off.setText("Eye_OFF");
                    eye_tracking_status = false;
                    editor.putBoolean("eye_tracking_status", false);
                    editor.commit();
                }

                else if(eye_tracking_status == false){
                    eye_tracking_on_off.setText("Eye_ON");
                    eye_tracking_status = true;
                    editor.putBoolean("eye_tracking_status", true);
                    editor.commit();
                }
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.category:
                        setFrag(0);
                        break;
                    case R.id.search:
                        setFrag(1);
                        break;
                    case R.id.home:
                        setFrag(2);
                        break;
                    case R.id.like:
                        setFrag(3);
                        break;
                    case R.id.myPage:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        fragment_Category = new CategoryFragment();
        fragment_Search = new SearchFragment();
        fragment_Home = new HomeFragment();
        fragment_LIke = new LikeFragment();
        fragment_MyPage = new MyPageFragment();

        setFrag(2); // 첫 프래그먼트 화면 지정
    }

    private void setFrag(int n){
        fragmentManager = getSupportFragmentManager();
        transaction= fragmentManager.beginTransaction();
        switch (n)
        {
            case 0:
                Bundle bundle_1 =  new Bundle();
                bundle_1.putString("userId", userId);
                bundle_1.putInt("userId_Num", userId_Num);
                bundle_1.putString("serverURL", serverURL);
                bundle_1.putString("mainClass", mainClass);
                fragment_Category.setArguments(bundle_1);
                transaction.replace(R.id.Main_Frame,fragment_Category);
                transaction.commit();
                break;

            case 1:
                Bundle bundle_2 =  new Bundle();
                bundle_2.putString("userId", userId);
                bundle_2.putInt("userId_Num", userId_Num);
                bundle_2.putString("serverURL", serverURL);
                bundle_2.putString("mainClass", mainClass);
                fragment_Search.setArguments(bundle_2);
                transaction.replace(R.id.Main_Frame,fragment_Search);
                transaction.commit();
                break;

            case 2:
                Bundle bundle_3 =  new Bundle();
                bundle_3.putString("userId", userId);
                bundle_3.putInt("userId_Num", userId_Num);
                bundle_3.putString("serverURL", serverURL);
                bundle_3.putString("mainClass", mainClass);
                fragment_Home.setArguments(bundle_3);
                transaction.replace(R.id.Main_Frame,fragment_Home);
                transaction.commit();
                break;

            case 3:
                Bundle bundle_4 =  new Bundle();
                bundle_4.putString("userId", userId);
                bundle_4.putInt("userId_Num", userId_Num);
                bundle_4.putString("serverURL", serverURL);
                bundle_4.putString("mainClass", mainClass);
                fragment_LIke.setArguments(bundle_4);
                transaction.replace(R.id.Main_Frame,fragment_LIke);
                transaction.commit();
                break;

            case 4:
                Bundle bundle_5 =  new Bundle();
                bundle_5.putBoolean("SAVE_LOGIN_DATA", SAVE_LOGIN_DATA);
                bundle_5.putString("userId", userId);
                bundle_5.putString("serverURL", serverURL);
                bundle_5.putString("mainClass", mainClass);
                fragment_MyPage.setArguments(bundle_5);
                transaction.replace(R.id.Main_Frame,fragment_MyPage);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        if(fragment_Search.searchWebView.canGoBack()){
            try{
                fragment_Search.searchWebView.goBack();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        else if(fragment_Category.categoryWebView.canGoBack()){
            try {
                fragment_Category.categoryWebView.goBack();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        else if(fragment_Home.home_WebView.canGoBack()){
            try {
                fragment_Home.home_WebView.goBack();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        else if(fragment_LIke.likeWebView.canGoBack()){
            try {
                fragment_LIke.likeWebView.goBack();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}