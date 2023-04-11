package com.example.oop_task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_INFO = 1;
    TextView tVResult, tVResultLabel;
    //class의 Member Field로 추가해
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReq = (Button) findViewById(R.id.btnRequest);//btnRequest 찾아내! 정보요청 버튼
        Button btnEnd = (Button) findViewById(R.id.btnEnd);//btbEnd도 알아내! 종료버튼
        EditText editTextId = findViewById(R.id.editTextID);//editText에 있는 글자를 찾아서 저장을 하고 있음
        tVResultLabel = (TextView) findViewById(R.id.tVResultLabel);
        tVResult = (TextView) findViewById(R.id.tVResult);

        //이제 각자 다들 찾았으니 Listener장착해야지
        btnReq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                str = editTextId.getText().toString();
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                intent.putExtra("id", str);
                startActivityForResult(intent, REQUEST_INFO);
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    //어떤 결과가 오면 그걸 처리하는 method가 있어야지
    //서브 액티비티[InfomationActivity]가 결과를 보내면 OnActivityResult() 콜백 메소드가 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //@Nullable Intent data 이 매개변수는 intent, 정보가 담겨있어
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_INFO) && (resultCode == RESULT_OK)) {
            tVResultLabel.setText("전송\n정보\n출력");
            str = "아이디: " + str;
            str = str + "\n이름: " + data.getStringExtra("name");
            str = str + "\n나이: " + data.getStringExtra("age");
            str = str + "\n성별: " + data.getStringExtra("sex");
            str = str + "\n자격증: " + data.getStringExtra("license");
            tVResult.setText(str);
            //이 위는 인텐트에 실려온 정보를 파악하여 출력
        }
    }
}