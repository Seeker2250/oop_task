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
    //editText에서 입력한 ID값을 저장하기 위한 member field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReq = (Button) findViewById(R.id.btnRequest);//btnRequest 찾아내! 정보요청 버튼
        Button btnEnd = (Button) findViewById(R.id.btnEnd);//btbEnd도 알아내! 종료버튼
        EditText editTextId = findViewById(R.id.editTextID);//editText에 있는 글자를 찾아서 저장을 하고 있음
        tVResultLabel = (TextView) findViewById(R.id.tVResultLabel);
        tVResult = (TextView) findViewById(R.id.tVResult);
        EditText editTextID = findViewById(R.id.editTextID);

        //이제 각자 다들 찾았으니 Listener장착해야지
        btnReq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {//정보 요청할 때 함수
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);//intent 객체 만들어
                str = editTextID.getText().toString();//id를 꺼내서 계속 가지고 있어 (str에 가지고 있어)
                //toString을 통해 문자열로 바꿔줘

                intent.putExtra("id", str);// id에 str에 담긴 정보를 넣어줘
                startActivityForResult(intent, REQUEST_INFO);//결과를 받아와야지
                // requestCode적어줘, 1이면 처리해도 되는거야
                // 근데 위에 상수 지정해줘서 REQUEST_INFO라고 적는거지(헷갈릴까봐)
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {//btnEnd에도 Listener가 달려 있어야 해
            public void onClick(View view) {
                finish();
            }
        });
    }

    //어떤 결과가 오면 그걸 처리하는 method가 있어야지
    //서브 액티비티[InfomationActivity]가 결과를 보내면 OnActivityResult() 콜백 메소드가 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//결과를 보내오면 무조건 여기에서 처리돼
        //결과가 오는 건 싹 다 onActivityResult에서 처리해
        //requestCode가 맞아야 해! 어디에서 오는 결과인지, 어디로 반환되는지 알아야지
        //@Nullable Intent data 이 매개변수는 intent, 정보가 담겨있어
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_INFO) && (resultCode == RESULT_OK)) {//requestCode가 맞고 resultCode(상태)가 OK라면
            str = "아이디: " + str;
            str = str + "\n이름: " + data.getStringExtra("name");//putExtra했던 거 꺼내와야지
            str = str + "\n나이: " + data.getStringExtra("age");
            str = str + "\n성별: " + data.getStringExtra("sex");
            str = str + "\n자격증: " + data.getStringExtra("license");
            //이 위는 인텐트에 실려온 정보를 파악하여 출력

            tVResultLabel.setText("전송\n정보\n출력");
            tVResult.setText(str);
        }
    }
    //되나



}