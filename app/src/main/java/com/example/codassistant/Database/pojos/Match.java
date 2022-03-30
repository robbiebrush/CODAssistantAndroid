package com.example.codassistant.Database.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {
    private int id;
    private String map;
    private String mode;
    private int elims;
    private int deaths;
    private int obj;
    private long lastUpdated;

    public Match(String map, String mode, int elims, int deaths, int obj) {
        this.map = map;
        this.mode = mode;
        this.elims = elims;
        this.deaths = deaths;
        this.obj = obj;
        this.lastUpdated = System.currentTimeMillis() - 900000;
    }

    public Match(int id, String map, String mode, int elims, int deaths, int obj) {
        this.id = id;
        this.map = map;
        this.mode = mode;
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

    public double getKdRatio() { return this.elims / this.deaths; }

    public int getId() { return id; }

    public String getMap() { return map; }

    public void setMap(String map) { this.map = map; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public int getElims() { return elims; }

    public void setElims(int elims) { this.elims = elims; }

    public int getDeaths() { return deaths; }

    public void setDeaths(int deaths) { this.deaths = deaths; }

    public int getObj() { return obj; }

    public void setObj(int obj) { this.obj = obj; }

    @Override
    public String toString() { return getMap() + " " + getMode() + ": " + getElims() + "/" + getDeaths() + ", Elim-Death Ratio: " + getKdRatio() + ", Obj: " + getObj();}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(map);
        parcel.writeString(mode);
        parcel.writeInt(elims);
        parcel.writeInt(deaths);
        parcel.writeInt(obj);
    }
}
