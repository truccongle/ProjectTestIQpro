package com.example.truccongle.projecttestiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoneActivity extends AppCompatActivity {
    int tongDiem;
    int age, muc;
    String name = "";
    Button bangLichSu;
    TextView soDiem, diemcaonhat, txtName, txtAge, txtMuc,txtTongMuc;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        Bundle bundle = getIntent().getExtras();
        tongDiem = bundle.getInt("tongDiem");

        name = bundle.getString("name");
        age = bundle.getInt("age");
        muc = bundle.getInt("muc");


        db = new DatabaseHandler(this);
        txtAge = (TextView) findViewById(R.id.txtTuoi);
        txtName = (TextView) findViewById(R.id.txtTen);
        txtName.setText("Name: "+name + "");
        txtAge.setText("Tuổi: "+age + "");
        soDiem = (TextView) findViewById(R.id.txtSoDiem);
        soDiem.setText("IQ của bạn là: " + tongDiem + " ");
        diemcaonhat = (TextView) findViewById(R.id.textViewDiemCaoNhat);
        txtMuc = (TextView) findViewById(R.id.txtMucDo);
        txtTongMuc=(TextView)findViewById(R.id.txtTongMuc);
        if (muc == 1) {
            txtMuc.setText("Mức độ:Dễ");
        } else if (muc == 2) {
            txtMuc.setText("Mức độ:Trung bình");
        } else txtMuc.setText("Mức độ:Khó");


        //
        Cursor c = db.getCursor("SELECT * FROM LichSu ORDER BY TongDiem DESC");
        c.moveToFirst();

        diemcaonhat.setText("IQ cao nhất hiện tạị là:" + (c.getString(2)));

        bangLichSu = (Button) findViewById(R.id.buttonLichSuTest);
        bangLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DoneActivity.this, HighScoreActivity.class);
                startActivity(in);
            }
        });
        if(tongDiem<75){
            txtTongMuc.setText("Bạn thuộc nhóm người có mức IQ thấp");
        }
        else if(tongDiem<110){
            txtTongMuc.setText(" Bạn thuộc nhóm người có mức IQ Trung binh   ");
        }
else txtTongMuc.setText("Bạn thuộc nhóm người có mức IQ Cao");
    }


}