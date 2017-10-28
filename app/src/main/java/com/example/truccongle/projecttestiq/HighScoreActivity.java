package com.example.truccongle.projecttestiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);

    ArrayList<String> arrList = null;
    ArrayAdapter<String> adapter = null;
    Button btnDel, btnHome;

    ListView lvlichsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        lvlichsu = (ListView) findViewById(R.id.lvHighScore);
        try {


            //
            db.copyDB2SDCard();
            //Hiển thị dữ liệu từ CSDL lên ListVIew
            data2ListView();
            //

            btnDel=(Button)findViewById(R.id.btnDel);
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.excuteSQL("Delete from LichSu");
                    data2ListView();

                }
            });
            btnHome=(Button)findViewById(R.id.btnHome);
            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HighScoreActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void data2ListView() {

        //Khởi tạo lại ArrayList
        ArrayList arrayList = new ArrayList<String>();
        //Lấy dữ liệu vào ArrayList
        Cursor c = db.getCursor("select * from LichSu");
//        c.moveToFirst();
        while (c.moveToNext()) {
            String row = c.getString(3) + " IQ của bạn là:" + c.getString(2);
            arrayList.add(row);

            //Set Adapter
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            lvlichsu.setAdapter(adapter);
        }
    }
}


