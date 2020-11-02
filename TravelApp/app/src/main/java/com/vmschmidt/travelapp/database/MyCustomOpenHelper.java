package com.vmschmidt.travelapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vmschmidt.travelapp.support.MyCustomDate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MyCustomOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "trips";
    private static final String FILE_NAME = "database.sql";
    private Context context;

    public MyCustomOpenHelper(Context context){
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            InputStream inputStream = context.getAssets().open(FILE_NAME);
            Scanner scanner = new Scanner(inputStream);

            StringBuilder stringBuilder = new StringBuilder();
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
            }
            String[] sql = stringBuilder.toString().split(";");
            for(String statement : sql){
                sqLiteDatabase.execSQL(statement);
                Log.d("TEST", statement);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
