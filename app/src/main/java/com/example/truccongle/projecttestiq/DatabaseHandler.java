package com.example.truccongle.projecttestiq;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by truccongle on 09-Nov-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Khai báo biến
    private Context dbContext;
    private SQLiteDatabase db;
    private static final String dbPath = "data/data/com.example.truccongle.projecttestiq/databases/Question.sqlite";
    private static final String dbName = "Question.sqlite";
    private static final int dbVersion = 1;

    // Phương thức 1: Phương thức khởi dựng
    public DatabaseHandler(Context context) {
        super(context, dbName, null, dbVersion);
        // TODO Auto-generated constructor stub
        this.dbContext = context;
    }

    //Phương thức 2:
    //Kiểm tra sự tồn tại của CSDL và copy CSDL sang SD Card
    //Nếu CSDL SQLite ĐÃ CÓ trên SD Card rồi thì thôi
    //Nếu CSDL SQLite CHƯA CÓ trên SD Card thì copy sang
    public void copyDB2SDCard() throws IOException {
        boolean check = false;
        try {
            File file = new File(dbPath);
            check = file.exists();
            if (check) {
                this.close();
            } else if (!check) {
                this.getReadableDatabase();
                //
                InputStream myInput = dbContext.getAssets().open(dbName);
                String outFileName = dbPath;
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (Exception ex) {
            throw new Error("Lỗi không copy được database");
        }
    }

    // Phương thức 3: Mở CSDL
    public void openDB() {
        db = SQLiteDatabase.openDatabase(dbPath, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    // Phương thức 4: Đóng CSDL
    public void closeDB() {
        db.close();
    }

    //Phương thức 5: Đọc CSDL
    public Cursor getCursor(String strSQL) {
        //B1
        openDB();
        //B2
        Cursor c = db.rawQuery(strSQL, null);
        //B3
        return c;
    }

    //Phương thức 6: thực thi câu lệnh SQL: Insert, Update, Delete
    public void excuteSQL(String sql) {
        //B1
        openDB();
        //B2
        db.execSQL(sql);
        //B3
        closeDB();
    }


    // Phương thức Oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    // Phương thức onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
