package com.example.massrecord.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.massrecord.model.Weight;
import com.example.massrecord.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_DATE + " TEXT, " + Params.KEY_WEIGHT + " TEXT" + ")";
        Log.d("DBDJ", "Query was run " + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addWeight(Weight weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_DATE, weight.getDate());
        values.put(Params.KEY_WEIGHT, weight.getWeight());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("DBDJ", "Succesfully inserted");
        db.close();
    }

//    public List<Weight> getAllWeights() {
//        List<Weight> weightList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //Generate the query to read from the database
//        String select = "SELECT * FROM " + Params.TABLE_NAME;
//        Cursor cursor = db.rawQuery(select, null);
//
//        //Loop through now
//        if (cursor.moveToFirst()){
//            do {
//                Weight weight = new Weight();
//                weight.setId(Integer.parseInt(cursor.getString(0)));
//                weight.setDate(cursor.getString(1));
//                weight.setWeight(cursor.getString(2));
//                weightList.add(weight);
//            } while(cursor.moveToNext());
//        }
//        return weightList;
//    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Params.TABLE_NAME, null);
        return res;
    }


    public int updateWeight(Weight weight){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_DATE, weight.getDate());
        values.put(Params.KEY_WEIGHT, weight.getWeight());

        //updating now
        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?" , new String[] {String.valueOf(weight.getId())} );
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Params.TABLE_NAME, "ID = ?", new String[] {id});
    }
}
