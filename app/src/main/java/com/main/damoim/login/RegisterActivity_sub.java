package com.main.damoim.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.main.damoim.R;

import java.util.ArrayList;

public class RegisterActivity_sub extends AppCompatActivity {

    EditText writeAge, usedPolicy;
    Spinner selectJob;
    CheckBox policy_StartUp, policy_Employment, policy_PolicyParticipation, policy_LivingWelfare, policy_HousingSupport, policy_Corona;
    Button addPolicy, backStep, registerComplete;
    ListView usedPolicyList;

    String age, job, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sub);


        usedPolicy = (EditText) findViewById(R.id.usedPolicy);
        writeAge = (EditText) findViewById(R.id.writeAge);
        selectJob = (Spinner) findViewById(R.id.selectJob);

        policy_StartUp = (CheckBox) findViewById(R.id.policy_StartUp);
        policy_Employment = (CheckBox) findViewById(R.id.policy_Employment);
        policy_PolicyParticipation = (CheckBox) findViewById(R.id.policy_PolicyParticipation);
        policy_LivingWelfare = (CheckBox) findViewById(R.id.policy_LivingWelfare);
        policy_HousingSupport = (CheckBox) findViewById(R.id.policy_HousingSupport);
        policy_Corona = (CheckBox) findViewById(R.id.policy_Corona);
        backStep = (Button) findViewById(R.id.backStep);
        registerComplete = (Button) findViewById(R.id.registerComplete);

        addPolicy = (Button) findViewById(R.id.addPolicy);


        //빈 데이터 리스트 생성
        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, items);
        // ListView 생성 및 adapter 지정
        usedPolicyList = (ListView) findViewById(R.id.usedPolicyList);
        usedPolicyList.setAdapter(adapter);

        // + 버튼 클릭 시
        addPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policy;
                policy = usedPolicy.getText().toString();
                Toast.makeText(getApplicationContext(),policy,Toast.LENGTH_SHORT).show();
                items.add(policy);
                adapter.notifyDataSetChanged();
            }
        });

        //listView에서 아이템 클릭 시 삭제하는 메서드
        usedPolicyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        // 뒤로가기 버튼 클릭 시
        backStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에서 아이디값을 확인후, 삭제하고 회원가입 첫단계로 돌아가기
                // Register_main 액티비티에서 id값 불러오기
                Intent intent = getIntent();
                String userId = intent.getExtras().getString("userId");

                finish();
            }
        });

        //회원가입 완료 클릭 시
        registerComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}