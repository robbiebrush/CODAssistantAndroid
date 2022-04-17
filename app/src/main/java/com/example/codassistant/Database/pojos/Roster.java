package com.example.codassistant.Database.pojos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;

public class Roster implements Parcelable {
    private int id;
    private String team;
    private String roster;
    private byte[] logo;
    private long lastUpdated;
    //creating and inserting to DB
    public Roster(Bitmap logo, String team, String roster) {
        this.team = team;
        this.roster = roster;
        this.lastUpdated = System.currentTimeMillis();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.PNG, 90, stream);
        this.logo = stream.toByteArray();
    }
    //read from DB
    public Roster(Bitmap logo, String team, String roster, long lastUpdated) {
        this.team = team;
        this.roster = roster;
        this.lastUpdated = lastUpdated;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.PNG, 90, stream);
        this.logo = stream.toByteArray();
    }

    public Roster(int id, Bitmap logo, String team, String roster, long lastUpdated) {
        this.id = id;
        this.team = team;
        this.roster = roster;
        this.lastUpdated = lastUpdated;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.PNG, 90, stream);
        this.logo = stream.toByteArray();
    }

    public Roster() {

    }

    private Roster(Parcel in) {
        team = in.readString();
        roster = in.readString();
        in.readByteArray(logo);
        lastUpdated = in.readLong();
    }

    public static final Parcelable.Creator<Roster> CREATOR = new Parcelable.Creator<Roster>() {
        @Override
        public Roster createFromParcel(Parcel in) {
            return new Roster(in);
        }

        @Override
        public Roster[] newArray(int size) {
            return new Roster[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(team);
        parcel.writeString(roster);
        parcel.writeByteArray(logo);
        parcel.writeLong(lastUpdated);
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public byte[] getLogo() { return logo; }

    public Bitmap getLogoBitmap() {
        Bitmap image = BitmapFactory.decodeByteArray(this.logo, 0, this.logo.length);
        return image;
    }

    public void setLogo(byte[] logo) {this.logo = logo; }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
