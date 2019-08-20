package com.n.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private SQLiteDatabase db;

    public DBManager(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    // Add a record
    public void add(MarriageRecord record) {
        db.execSQL("INSERT INTO marriage VALUES(null, ?, ?, ?,?)",
                new Object[]{record.husbandName, record.husbandProfession, record.temperType,record.husbandRole});
    }

    // Query all the records
    public List<MarriageRecord> query() {
        ArrayList<MarriageRecord> recordList = new ArrayList<MarriageRecord>();
        Cursor c = db.rawQuery("SELECT * FROM marriage", null);
        while (c.moveToNext()) {
            MarriageRecord record = new MarriageRecord();
            record._id = c.getInt(c.getColumnIndex("_id"));
            record.husbandName = c.getString(c.getColumnIndex("husband_name"));
            record.husbandProfession = c.getString(c.getColumnIndex("husband_profession"));
            record.temperType = c.getString(c.getColumnIndex("temper_type"));
            record.husbandRole = c.getString(c.getColumnIndex("husband_role"));
            recordList.add(record);
        }
        c.close();
        return recordList;
    }

    // Close database
    public void closeDB() {
        db.close();
    }
}