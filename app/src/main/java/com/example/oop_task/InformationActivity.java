package com.example.oop_task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Intent it = getIntent();//저쪽에서 보내온 정보 파악만 하고 사용은 안하지만 그래도 Intent 받아오자
        String str = it.getStringExtra("id");// str에 id 담아놔

        //참조변수 이름과 Controller 이름 동일하게
        EditText editTextName = findViewById(R.id.editTextName);//헷갈리지 않게 조심
        EditText editTextAge = findViewById(R.id.editTextAge);//헷갈리지 않게 조심
        RadioButton radioMale = findViewById((R.id.radioMale));
        RadioButton radioFemale = findViewById((R.id.radioFemale));
        CheckBox cBInfo = findViewById(R.id.cBInfo);
        CheckBox cBAI = findViewById(R.id.cBAI);
        CheckBox cBSecurity = findViewById(R.id.cBSecurity);
        Button btnSend = findViewById(R.id.btnSend);
        //다 인식시켜둔거야

        //btnSend 차례
        //전송하기 버튼을 누르면 Information에 있는 정보를 capture해서 Main으로 보내주고 종료해야해
        //해야할 일 구현하려면 Listener 달아줘야지
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();//매개변수 필요없어 실행요청이 아니라 그냥 정보 보내는거니까
                it.putExtra("name",editTextName.getText().toString());//인텐트 객체에 정보를 담아야지
                //name이라는 key로 editTextName에 담겨져 있던 text가져와서 String으로
                it.putExtra("age", editTextAge.getText().toString());
                //나이도 그냥 String취급해 출력하기 위한거니까

                if(radioMale.isChecked())
                    it.putExtra("sex", "male");
                else
                    it.putExtra("sex", "female");
                //남성버튼에 체크면 남성 아니면 여성

                String strLicense = "";
                if (cBInfo.isChecked())
                    strLicense = strLicense + "\n    정보처리기사";
                if (cBAI.isChecked())
                    strLicense = strLicense + "\n    인공지능데이터전문가";
                if (cBSecurity.isChecked())
                    strLicense = strLicense + "\n    정보보안기사";

                it.putExtra("License", strLicense);//이거 또 보내줘야지 catch한 정보
                setResult(RESULT_OK, it);//제대로 전달, intent를 보내줘야 저쪽에서도 확인하지
                //setResult(); 보내면 그 순간 결과 전송 저쪽에서 onCreate 발동
                finish();//더 할 일 없으니 finish
            }
        });


    }
}
