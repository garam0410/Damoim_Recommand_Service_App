package com.main.damoim.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.main.damoim.LoadingActivity;
import com.main.damoim.main.MainActivity;
import com.main.damoim.R;
import com.main.damoim.main.MyPageFragment;

public class LoginActivity extends AppCompatActivity {


    String result, id, pw;
    private SharedPreferences appData;
    private boolean saveLoginData;

    Button login, register, find_id_pw, login_naver, login_facebook, login_kakao; // Button 정의
    EditText userId, userPw; // EditText 정의
    CheckBox auto_LoginCheck; // 자동로그인 checkbox 정의
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        Intent intent_2 = getIntent();

        login = (Button)findViewById(R.id.Login);
        register = (Button)findViewById(R.id.Resister);
        find_id_pw = (Button)findViewById(R.id.findIdPw);
        /*login_naver = (Button)findViewById(R.id.loginNaver);
        login_facebook = (Button)findViewById(R.id.loginFacebook);
        login_kakao = (Button)findViewById(R.id.loginKakao);*/

        userId = (EditText)findViewById(R.id.userId);
        userPw = (EditText)findViewById(R.id.userPw);
        auto_LoginCheck = (CheckBox) findViewById(R.id.autoLoginCheck);

        saveLoginData = intent_2.getBooleanExtra("SAVE_LOGIN_DATA", false);

        //계정 정보 저장을 위한 SharedPreferences 생성
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        //로딩창창
       progressDialog = new ProgressDialog(this);

        //자동로그인 체크 되어있다면, 바로 로그인 실행
        if(saveLoginData){
            userId.setText(id);
            userPw.setText(pw);
            auto_LoginCheck.setChecked(saveLoginData);
            Login();
        }

        //로그인 클릭 시
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        //회원가입 클릭 시
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity_main.class);
                startActivity(intent);
            }
        });

        //ID/PW 찾기 클릭 시
        find_id_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

        //네이버로 로그인하기 클릭 시
        /*login_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NaverLoginActivity.class);
                startActivity(intent);
            }
        });

        //페이스북으로 로그인하기 클릭 시
        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FacebookLoginActivity.class);
                startActivity(intent);
            }
        });

        // 카카오 계정으로 로그인하기 클릭 시
        login_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KakaoLoginActivity.class);
                startActivity(intent);
            }
        });*/
    }

    //로그인 실행
    private void Login(){

        final String id = userId.getText().toString().trim();
        String pw = userPw.getText().toString().trim();

        try {

            progressDialog.setMessage("로그인 중입니다. 잠시 기다려 주세요...");
            progressDialog.show();
            Connect_Login_Server login_check = new Connect_Login_Server();
            result = login_check.execute(id,pw).get();

            if(result.trim().equals("true")){
                save();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("SAVE_LOGIN_DATA",saveLoginData);
                intent.putExtra("userId", id);
                startActivity(intent);
                finish();
            }

            else if(result.trim().equals("false")){
                Toast.makeText(getApplicationContext(),"아이디 또는 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"네트워크 오류", Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
        }
    }

    //계정 정보 저장
    private void save(){
        SharedPreferences.Editor editor = appData.edit();

        editor.putBoolean("SAVE_LOGIN_DATA", auto_LoginCheck.isChecked());
        editor.putString("userId", userId.getText().toString().trim());
        editor.putString("userPw", userPw.getText().toString().trim());

        editor.apply();
    }

    //저장된 정보 불러오기
    public void load(){
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("userId", "");
        pw = appData.getString("userPw", "");
    }
}