package com.example.codassistant.Database.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.codassistant.Database.pojos.Match;

import java.util.ArrayList;

public class MatchesDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "matchesDB";

    public static final String TABLE_MATCHES = "matches";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MAP = "map";
    public static final String COLUMN_MODE = "mode";
    public static final String COLUMN_TEAM_SCORE = "team_score";
    public static final String COLUMN_OPP_SCORE = "opp_score";
    public static final String COLUMN_ELIMS = "elims";
    public static final String COLUMN_DEATHS = "deaths";
    public static final String COLUMN_OBJ = "obj";

    /**
     * TODO
     * Create Temp and Timestamp columns
     */

    public static final String CREATE_MATCHES_TABLE = "CREATE TABLE " +
            TABLE_MATCHES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_MAP + " TEXT, " + COLUMN_MODE + " TEXT, "  +
            COLUMN_TEAM_SCORE + " INT, " + COLUMN_OPP_SCORE + " INT, " +
            COLUMN_ELIMS + " INT, " + COLUMN_DEATHS + " INT, " +
            COLUMN_OBJ + " INT)";

    public MatchesDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MATCHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addMatch(Match match){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAP, match.getMap());
        values.put(COLUMN_MODE, match.getMode());
        values.put(COLUMN_TEAM_SCORE, match.getTeamScore());
        values.put(COLUMN_OPP_SCORE, match.getOppScore());
        values.put(COLUMN_ELIMS, match.getElims());
        values.put(COLUMN_DEATHS, match.getDeaths());
        values.put(COLUMN_OBJ, match.getObj());
        db.insert(TABLE_MATCHES, null, values);
        db.close();
        Log.d("SQL", "Match added successfully.");
    }

    public Match getMatch(int id){
        SQLiteDatabase db  = this.getReadableDatabase();
        Match match = null;
        Cursor cursor = db.query(TABLE_MATCHES,
                new String[]{COLUMN_ID, COLUMN_MAP, COLUMN_MODE, COLUMN_ELIMS,
                        COLUMN_DEATHS, COLUMN_OBJ}, COLUMN_ID + "= ?"
                , new String[]{String.valueOf(id)},
                null, null, null);
        if(cursor.moveToFirst()){ //Checking that we have a value and select it to be read
            match = new Match(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7)
            );
        }
        db.close();
        return match;
    }

    public ArrayList<Match> getAllMatches(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Match> matches = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MATCHES, null);
        Log.d("SQL", "Matches Table: " + cursor.getCount() + " inserts");
        while(cursor.moveToNext()){
            matches.add(new Match(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7))
            );
        }
        db.close();
        return matches;
    }

    public int updateMatch(Match match){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAP, match.getMap());
        values.put(COLUMN_MODE, match.getMode());
        values.put(COLUMN_TEAM_SCORE, match.getTeamScore());
        values.put(COLUMN_OPP_SCORE, match.getOppScore());
        values.put(COLUMN_ELIMS, match.getElims());
        values.put(COLUMN_DEATHS, match.getDeaths());
        values.put(COLUMN_OBJ, match.getObj());
        return db.update(TABLE_MATCHES, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(match.getId())});
    }

    public void deleteMatch(int match){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MATCHES, COLUMN_ID +  "=?",
                new String[]{String.valueOf(match)});
        db.close();
    }
}