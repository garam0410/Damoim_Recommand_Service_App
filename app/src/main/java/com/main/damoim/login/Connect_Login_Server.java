package com.main.damoim.login;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Connect_Login_Server extends AsyncTask<String, Void, String> {

    String sendMsg, receiveMsg;

    protected String doInBackground(String... strings){

        try {
            String str;

            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://61.245.226.108:8181/damoimServer/loginUser.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&pw=" + strings[1];

            osw.write(sendMsg);
            osw.flush();

            //jsp와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                // jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                // 통신 실패
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
