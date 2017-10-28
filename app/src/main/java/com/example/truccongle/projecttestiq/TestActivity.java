package com.example.truccongle.projecttestiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    TextView cauHoi, daA, daB, daC, daD, cauHoiSo, tvdongho, txtName, txtMucDo;
    Button btnA, btnB, btnC, btnD;
    String dapAn;
    int diem;
    int tongDiem = 0;
    int age, muc;
    String name = "";
    DatabaseHandler db;
    int count = 0;

    ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        db = new DatabaseHandler(this);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        age = bundle.getInt("age");
        cauHoi = (TextView) findViewById(R.id.txtCauHoi);
        if (age <= 15) {
            muc = 1;

        } else if (age <= 20) {
            muc = 2;
        } else muc = 3;
//          manHinh = (RelativeLayout) findViewById(R.id.ManHinh);
        //  manHinh.setBackgroundResource(R.drawable.iq);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);

//        daA = (TextView) findViewById(R.id.txtA);
//        daB = (TextView) findViewById(R.id.txtB);
//        daC = (TextView) findViewById(R.id.txtC);
//        daD = (TextView) findViewById(R.id.txtD);
        txtName = (TextView) findViewById(R.id.txtName);
        txtMucDo = (TextView) findViewById(R.id.txtMuc);

        cauHoiSo = (TextView) findViewById(R.id.txtCauHoiSo);
        tvdongho = (TextView) findViewById(R.id.txtTime);
        loadData();

        if (muc == 1) {
            txtMucDo.setText("Mức độ:Dễ");
        } else if (muc == 2) {
            txtMucDo.setText("Mức độ:Trung Bình");
        } else txtMucDo.setText("Mức độ:Khó");
        final int[] count = {1500000};
        txtName.setText("Xin chào: " + name);

        final CountDownTimer countDownTimer = new CountDownTimer(count[0], 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvdongho.setText("Time:" + TimeUtils.formatDuration(count[0]) + "s");
                count[0] = count[0] - 1000;
            }

            @Override
            public void onFinish() {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//                Date date=new Date();
//                String ngay=sdf.format(date);
                db.excuteSQL("INSERT INTO LichSu VALUES(" + tongDiem + ")");
                Intent it = new Intent(getApplicationContext(), DoneActivity.class);
                it.putExtra("tongDiem", tongDiem);
                startActivity(it);

            }
        };
        countDownTimer.start();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "A";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "B";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "C";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "D";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });

    }

    //
    public void loadData() {
        Cursor c = db.getCursor("SELECT* FROM CauHoi WHERE MucDo=" + muc);
        int length = c.getCount();
        Random rd = new Random();
        int random = rd.nextInt(length) + 1;

        do {
            if (count < 5) {


                c.moveToPosition(random);
                String cauHoi = c.getString(1);
                this.cauHoi.setText(cauHoi);
                String daA = c.getString(2);
                this.btnA.setText("A. " + daA);
                String daB = c.getString(3);
                this.btnB.setText("B. " + daB);
                String daC = c.getString(4);
                this.btnC.setText("C. " + daC);
                String daD = c.getString(5);
                this.btnD.setText("D. " + daD);
                diem = c.getInt(7);
                dapAn = c.getString(6);

                arr.add(cauHoi);

                count++;
                cauHoiSo.setText("Câu hỏi:" + count + "/5");

            } else {
//                Calendar cal = GregorianCalendar.getInstance();
//                cal.setTime(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date = new Date();
                String ngay = sdf.format(date);
//                cal.add(Calendar.DAY_OF_YEAR, 0);//so ngay truoc. 0 la ngay hien tai
//                Date day = cal.getTime();
//                String ngay=sdf.format(day);

                db.excuteSQL("INSERT INTO LichSu VALUES(null,'" + ngay + "','" + tongDiem + "','" + name + "','" + age + "')");

                Intent it = new Intent(getApplicationContext(), DoneActivity.class);
                it.putExtra("tongDiem", tongDiem);
                it.putExtra("age", age);
                it.putExtra("name", name);
                it.putExtra("muc", muc);
                startActivity(it);

            }
        } while (arr.contains(cauHoi));

    }


}
