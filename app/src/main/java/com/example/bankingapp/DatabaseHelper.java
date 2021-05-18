package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname = "Bankdb";
    private static final int version= 1;

    public DatabaseHelper(Context context){
        super(context, dbname , null, version);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
       //table creation
       String sql = "CREATE TABLE USER_DETAILS (_id INTEGER , NAME TEXT, EMAIL VARCHAR PRIMARY KEY,BALANCE INTEGER,PHONENUMBER TEXT,ACCOUNT VARCHAR)";
       db.execSQL(sql);
       String sql_tr = "CREATE TABLE TRANSACT_DETAILS (transact_id INTEGER , FROMNAME TEXT, TONAME TEXT, AMOUNT INTEGER, STATUS TEXT)";
       db.execSQL(sql_tr);
       //to insert data
        insertData("Akhil","akhil@gmail.com",50000,"9820102647","XXXXXXXXXXXX4735",db);
        insertData("Ansan","ansan@gmail.com",15000,"9573920180","XXXXXXXXXXXX1344",db);
        insertData("Mahesh","mahesh@gmail.com",25000,"8876415627","XXXXXXXXXXXX3234",db);
        insertData("Vishvanth","vishvanth@gmail.com",56000,"9911023487","XXXXXXXXXXXX1284",db);
        insertData("Varadh","varadh@gmail.com",54000,"9102345013","XXXXXXXXXXXX1534",db);
        insertData("Karanpreet","karanpreet@gmail.com",30000,"9862852057","XXXXXXXXXXXX1294",db);
        insertData("Mahi","mahi@gmail.com",70000,"9939069420","XXXXXXXXXXXX1034",db);
        insertData("Deva","deva@gmail.com",85000,"9291046298","XXXXXXXXXXXX4234",db);
        insertData("Kevin","kevin@gmail.com",25000,"9837401938","XXXXXXXXXXXX1534",db);
        insertData("Abhishek","abhi@gmail.com",45000,"9354837196","XXXXXXXXXXXX8734",db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_DETAILS");
        db.execSQL("DROP TABLE IF EXISTS TRANSACT_DETAILS");
        onCreate(db);
    }
    public Cursor ReadAllData(){
        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USER_DETAILS",null);
        return cursor;
    }
    public Cursor readParticularData(String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from USER_DETAILS where Email = '"+Email + "'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor ReadSelectedData(String Email){
        SQLiteDatabase db = getWritableDatabase();
        String select ="select * from USER_DETAILS where email <> '"+Email + "'";
        Cursor c = db.rawQuery(select,null);
        return c;
    }
    public void UpdateAmount(String Email,String amount){
        SQLiteDatabase db =this.getWritableDatabase();
        String select="UPDATE USER_DETAILS set BALANCE = '"+ amount +"' where Email = '"+Email+"'";
        db.execSQL(select);
    }

    private void insertData(String name, String email, int balance,String phoneNumber,String account,SQLiteDatabase database){
        ContentValues values= new ContentValues();
        values.put("NAME",name);
        values.put("EMAIL",email);
        values.put("PHONENUMBER",phoneNumber);
        values.put("ACCOUNT",account);
        values.put("BALANCE",balance);
        database.insert("USER_DETAILS",null,values);
    }
    public void insertTransferData(String from_name, String to_name, int amount, String Status){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("FROMNAME",from_name);
        values.put("TONAME",to_name);
        values.put("AMOUNT",amount);
        values.put("STATUS",Status);
        database.insert("TRANSACT_DETAILS",null,values);
    }


    public Cursor Read_Transfer_amount_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from TRANSACT_DETAILS";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }
}
