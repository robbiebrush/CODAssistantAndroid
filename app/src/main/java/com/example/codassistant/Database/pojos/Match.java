package com.example.codassistant.Database.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DecimalFormat;

public class Match implements Parcelable {
    private int id;
    private String map;
    private String mode;
    private int teamScore;
    private int oppScore;
    private double elims;
    private double deaths;
    private int obj;
    private long lastUpdated;

    public Match(String map, String mode, int teamScore, int oppScore, int elims, int deaths, int obj) {
        this.map = map;
        this.mode = mode;
        this.teamScore = teamScore;
        this.oppScore = oppScore;
        this.elims = elims;
        this.deaths = deaths;
        this.obj = obj;
        this.lastUpdated = System.currentTimeMillis() - 900000;
    }

    public Match(int id, String map, String mode, int teamScore, int oppScore, int elims, int deaths, int obj) {
        this.id = id;
        this.map = map;
        this.mode = mode;
        this.teamScore = teamScore;
        this.oppScore = oppScore;
        this.elims = elims;
        this.deaths = deaths;
        this.obj = obj;
        this.lastUpdated = System.currentTimeMillis() - 900000;
    }

    public Match() {

    }

    protected Match(Parcel in) {
        id = in.readInt();
        map = in.readString();
        mode = in.readString();
        teamScore = in.readInt();
        oppScore = in.readInt();
        elims = in.readInt();
        deaths = in.readInt();
        obj = in.readInt();
    }

    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public double getKdRatio() {
        DecimalFormat df = new DecimalFormat("#.#");
        if (this.deaths == 0) {
            return Double.parseDouble(df.format(this.elims));
        } else {
            return Double.parseDouble(df.format(this.elims / this.deaths));
        }
    }

    public String getOutcome() {
        if (this.teamScore > this.oppScore) {
            return "W";
        } else if (this.teamScore < this.oppScore){
            return "L";
        } else {
            return "TIE";
        }
    }

    public int getId() { return id; }

    public String getMap() { return map; }

    public void setMap(String map) { this.map = map; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public int getTeamScore() { return teamScore; }

    public void setTeamScore(int teamScore) { this.teamScore = teamScore; }

    public int getOppScore() { return oppScore; }

    public void setOppScore(int oppScore) { this.oppScore = oppScore; }

    public int getElims() {
        DecimalFormat df = new DecimalFormat("#");
        return Integer.parseInt(df.format(this.elims));
    }

    public void setElims(int elims) { this.elims = elims; }

    public int getDeaths() {
        DecimalFormat df = new DecimalFormat("#");
        return Integer.parseInt(df.format(this.deaths));
    }

    public void setDeaths(int deaths) { this.deaths = deaths; }

    public int getObj() { return obj; }

    public void setObj(int obj) { this.obj = obj; }

    @Override
    public String toString() { return getMap() + " " + getMode() + ": " + getOutcome() + " " + getTeamScore() + "-" + getOppScore() + " " + getElims() + "/" + getDeaths() + ", " + getKdRatio() + "KD, Obj: " + getObj();}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(map);
        parcel.writeString(mode);
        parcel.writeInt(teamScore);
        parcel.writeInt(oppScore);
        parcel.writeDouble(elims);
        parcel.writeDouble(deaths);
        parcel.writeInt(obj);
    }
}
