package com.example.codassistant.Database.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.codassistant.Database.pojos.Match;
import com.example.codassistant.Database.pojos.Roster;

import java.util.ArrayList;

public class RostersDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "rostersDB";

    public static final String TABLE_ROSTERS = "rosters";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEAM = "team";
    public static final String COLUMN_ROSTER = "roster";
    public static final String COLUMN_LOGO = "logo";
    public static final String COLUMN_LAST_UPDATED = "last_updated";

    /**
     * TODO
     * Create Temp and Timestamp columns
     */

    public static final String CREATE_ROSTERS_TABLE = "CREATE TABLE " +
            TABLE_ROSTERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_TEAM + " TEXT, " + COLUMN_ROSTER + " TEXT, "  +
            COLUMN_LOGO + " BLOB, " + COLUMN_LAST_UPDATED + " INTEGER)";

    public RostersDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ROSTERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Roster getRoster(int id){
        SQLiteDatabase db  = this.getReadableDatabase();
        Roster roster = null;
        Cursor cursor = db.query(TABLE_ROSTERS,
                new String[]{COLUMN_ID, COLUMN_TEAM, COLUMN_ROSTER, COLUMN_LOGO}, COLUMN_ID + "= ?"
                , new String[]{String.valueOf(id)},
                null, null, null);
        if(cursor.moveToFirst()){ //Checking that we have a value and select it to be read
            roster = new Roster(
                    cursor.getInt(0),
                    BitmapFactory.decodeByteArray(cursor.getBlob(3), 0, cursor.getBlob(3).length),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(4)
            );
        }
        db.close();
        return roster;
    }

    public void addRoster(Roster roster){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGO, roster.getLogo());
        values.put(COLUMN_TEAM, roster.getTeam());
        values.put(COLUMN_ROSTER, roster.getRoster());
        values.put(COLUMN_LAST_UPDATED, roster.getLastUpdated());
        db.insert(TABLE_ROSTERS, null, values);
        db.close();
        Log.d("SQL", "Roster " + roster.getTeam() + " added successfully.");
    }

    public ArrayList<Roster> getAllRosters(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Roster> rosters = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ROSTERS, null);
        Log.d("SQL", "Rosters Table: " + cursor.getCount() + " inserts");
        while(cursor.moveToNext()){
            rosters.add(new Roster(
                    cursor.getInt(0),
                    BitmapFactory.decodeByteArray(cursor.getBlob(3), 0, cursor.getBlob(3).length),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(4))
            );
        }
        db.close();
        return rosters;
    }
}
