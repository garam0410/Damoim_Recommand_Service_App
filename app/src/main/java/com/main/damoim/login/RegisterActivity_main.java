package com.main.damoim.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.main.damoim.R;

public class RegisterActivity_main extends AppCompatActivity {

    String name, id, pw, rePw, email, phoneNum, age, job, favorite, area;

    EditText writeName, writeId, writePw, writeRePw, writeEmail, writePhone, writeAge;
    Button cancel, registerComplete;
    Spinner selectJob, selectArea;

    RadioGroup radioGroup;
    RadioButton policy_StartUp, policy_Employment, policy_PolicyParticipation, policy_LivingWelfare, policy_HousingSupport, policy_Corona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        writeName = (EditText) findViewById(R.id.writeName);
        writeId = (EditText) findViewById(R.id.writeId);
        writePw = (EditText) findViewById(R.id.writePw);
        writeRePw = (EditText) findViewById(R.id.writeRePw);
        writeEmail = (EditText) findViewById(R.id.writeEmail);
        writePhone = (EditText) findViewById(R.id.writePhone);

        writeAge = (EditText) findViewById(R.id.writeAge);

        selectJob = (Spinner) findViewById(R.id.selectJob);
        selectArea = (Spinner) findViewById(R.id.selectArea);

        policy_StartUp = (RadioButton) findViewById(R.id.policy_StartUp);
        policy_Employment = (RadioButton) findViewById(R.id.policy_Employment);
        policy_PolicyParticipation = (RadioButton) findViewById(R.id.policy_PolicyParticipation);
        policy_LivingWelfare = (RadioButton) findViewById(R.id.policy_LivingWelfare);
        policy_HousingSupport = (RadioButton) findViewById(R.id.policy_HousingSupport);
        policy_Corona = (RadioButton) findViewById(R.id.policy_Corona);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        registerComplete = (Button) findViewById(R.id.registerComplete);
        cancel = (Button) findViewById(R.id.cancel);

        //직업 값 넣기
        String[] arraySpinner = new String[]{
                "1", "2", "3", "4", "5", "6", "7"
        };

        String[] arraySpinner_2 = new String[]{
                "1", "2", "3", "4", "5", "6", "7","8", "9", "10"
                //"경기남부", "경기북부", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectJob.setAdapter(adapter);

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arraySpinner_2);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectArea.setAdapter(adapter_2);

        // 취소 버튼 클릭 시
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 다음단계 버튼 클릭 시
        registerComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EditText에서 가져온 String값
                name = writeName.getText().toString();
                id = writeId.getText().toString();
                pw = writePw.getText().toString();
                rePw = writeRePw.getText().toString();
                email = writeEmail.getText().toString();
                phoneNum = writePhone.getText().toString();
                age = writeAge.getText().toString();
                job = selectJob.getSelectedItem().toString();
                area = selectArea.getSelectedItem().toString();

                //빈칸 검사
                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(id.equals("")){
                        Toast.makeText(getApplicationContext(),"아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        if(pw.equals("")){
                            Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            if(rePw.equals("")){
                                Toast.makeText(getApplicationContext(),"비밀번호 확인을 임력하세요.", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                if(rePw.equals(pw)){
                                    if(email.equals("")){
                                        Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                                    }

                                    else{
                                        if(phoneNum.equals("")){
                                            Toast.makeText(getApplicationContext(),"전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                                        }

                                        else{

                                            try{
                                                String result;

                                                Connect_Register_Server task = new Connect_Register_Server();
                                                result = task.execute(name,id,pw,email,phoneNum, age, job, favorite,area).get();

                                                if(result.trim().equals("fail")){
                                                    Toast.makeText(getApplicationContext()," 이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                                }
                                                else if(result.trim().equals("success")){
                                                    Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }

                                            }catch(Exception e){
                                                Toast.makeText(getApplicationContext(),"네트워크 오류로 회원가입이 제한됩니다.", Toast.LENGTH_SHORT).show();
                                                Log.i("DBtest", "........Error!");
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }

                                else{
                                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.policy_Employment){
                favorite = "4001";
            }

            else if (checkedId == R.id.policy_Corona){
                favorite = "4006";
            }

            else if (checkedId == R.id.policy_HousingSupport){
                favorite = "4003";
            }

            else if (checkedId == R.id.policy_LivingWelfare){
                favorite = "4004";
            }

            else if (checkedId == R.id.policy_PolicyParticipation){
                favorite = "4005";
            }

            else if (checkedId == R.id.policy_StartUp){
                favorite = "4002";
            }
        }
    };
}