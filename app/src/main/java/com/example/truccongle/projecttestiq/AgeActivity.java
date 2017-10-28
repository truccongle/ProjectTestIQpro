package com.example.truccongle.projecttestiq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgeActivity extends AppCompatActivity {
DatabaseHandler db= new DatabaseHandler(this);
    Button btnConfirm;
    EditText edtAge,edtTen;
    int age;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        edtAge=(EditText)findViewById(R.id.editAge);
        edtTen=(EditText)findViewById(R.id.editTen) ;
        btnConfirm=(Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name!= null || age >= 5)  {
                    Intent it = new Intent(AgeActivity.this,TestActivity.class);
                    it.putExtra("age", Integer.parseInt(edtAge.getText().toString()));
                    it.putExtra("name", edtTen.getText().toString());
                    startActivity(it);
                } else {
                    Intent it = new Intent(getApplicationContext(), TestActivity.class);
//                    it.putExtra("age", Integer.parseInt(edtAge.getText().toString()));
//                    it.putExtra("name", edtTen.getText().toString());
                    Toast.makeText(AgeActivity.this, " Bạn vui lòng nhập vào Tên và Tuổi", Toast.LENGTH_LONG).show();



                    startActivity(it);
                }
            }
        });

    }
}
