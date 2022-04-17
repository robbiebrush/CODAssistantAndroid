package com.example.codassistant.ViewPagers.RosterPager;

import static java.util.Arrays.asList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.codassistant.Database.databases.RostersDatabase;
import com.example.codassistant.Database.pojos.Roster;
import com.example.codassistant.R;
import com.example.codassistant.apiClasses.RostersSingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CustomViewPager2Adapter extends FragmentStateAdapter {
    ArrayList<JSONObject> teamList;
    private ArrayList<Roster> rosters;
    private Context context;
    private boolean firstTime;
    Bitmap vanguardImg;
    String url =
            "https://api.pandascore.co/codmw/teams?token=mvWM5Bc17pu66vR9ecg3q5ZdzX0WG6kallqmeaXbciBD-4DJ_bU";
    /**
     * Instantiates a new Custom view page adapter.
     *
     * @param fragmentActivity the fragment activity
     */
    public CustomViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, Context context, ArrayList<Roster> rosters) {
        super(fragmentActivity);
        this.context = context;
        this.rosters = rosters;
    }

    @NonNull
    public Fragment createFragment(int position) {
        RostersDatabase db = new RostersDatabase(context);
        Roster roster = null;
        
        if (rosters.size() == 0) {
            firstTime = true;
        } else {
            firstTime = false;
            roster = rosters.get(position);
        }

        if(firstTime || System.currentTimeMillis() - roster.getLastUpdated() > 7776000000L) {//3 months
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                teamList = new ArrayList<JSONObject>();
                                teamList.add(response.getJSONObject(15));//atlanta faze
                                teamList.add(response.getJSONObject(1));//boston breach
                                teamList.add(response.getJSONObject(12));//florida mutineers
                                teamList.add(response.getJSONObject(11));//london royal ravens
                                teamList.add(response.getJSONObject(10));//la guerillas
                                teamList.add(response.getJSONObject(3));//la thieves
                                teamList.add(response.getJSONObject(9));//minnesota rokkr
                                teamList.add(response.getJSONObject(8));//new york subliners
                                teamList.add(response.getJSONObject(6));//paris legion
                                teamList.add(response.getJSONObject(5));//seattle surge
                                teamList.add(response.getJSONObject(4));//toronto ultra
                                //OpTic roster hasn't been updated since roster change; roster on 2 different teams
                                teamList.add(response.getJSONObject(13));//dallas empire (non-existent currently)
                                teamList.add(response.getJSONObject(2));//optic texas
                                teamList.add(response.getJSONObject(14));//optic chicago (non existent currently)
                                Log.d("help", "Successful web pull");
                            } catch (JSONException e) {
                                Log.d("help","catch");
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
                        }
                    });
            Log.d("help","" + RostersSingleton.getInstance(context).getRequestQueue().add(request));
            RostersSingleton.getInstance(context).getRequestQueue().add(request);


            Roster atlRoster;
            Roster bosRoster;
            Roster floRoster;
            Roster lonRoster;
            Roster lagRoster;
            Roster latRoster;
            Roster minRoster;
            Roster nykRoster;
            Roster parRoster;
            Roster seaRoster;
            Roster torRoster;
            Roster optRoster;

            try {
                atlRoster = new Roster(Picasso.get().load(teamList.get(0).getString("image_url")).get(), teamList.get(0).getString("name"), teamList.get(0).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(0).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                bosRoster = new Roster(Picasso.get().load(teamList.get(1).getString("image_url")).get(), teamList.get(1).getString("name"), teamList.get(1).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                floRoster = new Roster(Picasso.get().load(teamList.get(2).getString("image_url")).get(), teamList.get(2).getString("name"), teamList.get(2).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                        + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                lonRoster = new Roster(Picasso.get().load(teamList.get(3).getString("image_url")).get(), teamList.get(3).getString("name"), teamList.get(3).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                lagRoster = new Roster(Picasso.get().load(teamList.get(4).getString("image_url")).get(), teamList.get(4).getString("name"), teamList.get(4).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                latRoster = new Roster(Picasso.get().load(teamList.get(5).getString("image_url")).get(), teamList.get(5).getString("name"), teamList.get(5).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                minRoster = new Roster(Picasso.get().load(teamList.get(6).getString("image_url")).get(), teamList.get(6).getString("name"), teamList.get(6).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("last_name") + "\n"
                        + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                nykRoster = new Roster(Picasso.get().load(teamList.get(7).getString("image_url")).get(), teamList.get(7).getString("name"), teamList.get(7).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                        + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                parRoster = new Roster(Picasso.get().load(teamList.get(12).getString("image_url")).get(), teamList.get(12).getString("name"), teamList.get(13).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(13).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(13).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                        + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n");

                seaRoster = new Roster(Picasso.get().load(teamList.get(8).getString("image_url")).get(), teamList.get(8).getString("name"), teamList.get(8).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                torRoster = new Roster(Picasso.get().load(teamList.get(9).getString("image_url")).get(), teamList.get(9).getString("name"), teamList.get(9).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");

                optRoster = new Roster(Picasso.get().load(teamList.get(10).getString("image_url")).get(), teamList.get(10).getString("name"), teamList.get(10).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                        + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                        + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                        + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                atlRoster = null;
                bosRoster = null;
                floRoster = null;
                lonRoster = null;
                lagRoster = null;
                latRoster = null;
                minRoster = null;
                nykRoster = null;
                parRoster = null;
                seaRoster = null;
                torRoster = null;
                optRoster = null;
            }

            Log.d("help", "" + atlRoster.getTeam());

            rosters.add(atlRoster);
            rosters.add(bosRoster);
            rosters.add(floRoster);
            rosters.add(lonRoster);
            rosters.add(lagRoster);
            rosters.add(latRoster);
            rosters.add(minRoster);
            rosters.add(nykRoster);
            rosters.add(parRoster);
            rosters.add(seaRoster);
            rosters.add(torRoster);
            rosters.add(optRoster);

            for (Roster aRoster : rosters) {
                aRoster.setLastUpdated(System.currentTimeMillis());
                db.addRoster(aRoster);
            }
            Log.d("help", "" + rosters.size());

            try {
                vanguardImg = Picasso.get().load("https://styles.redditmedia.com/t5_3sgon3/styles/communityIcon_m4hjnmn9nuw71.png").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("help", "added rosters to db" + rosters.size());
        } else {
            Log.d("help", "didn't add rosters to db");
        }

        switch (position) {
            case 0:
                return ViewPager2Fragment.newInstance(vanguardImg, context.getResources().getString(R.string.cdlRosters), "COD: Vanguard (2022)");
            case 1:
                roster = db.getRoster(0);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 2:
                roster = db.getRoster(1);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 3:
                roster = db.getRoster(2);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 4:
                roster = db.getRoster(3);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 5:
                roster = db.getRoster(4);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 6:
                roster = db.getRoster(5);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 7:
                roster = db.getRoster(6);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 8:
                roster = db.getRoster(7);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 9:
                roster = db.getRoster(8);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 10:
                roster = db.getRoster(9);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 11:
                roster = db.getRoster(10);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            case 12:
                roster = db.getRoster(11);
                return ViewPager2Fragment.newInstance(roster.getLogoBitmap(), roster.getTeam(), roster.getRoster());
            default:
                return ViewPager2Fragment.newInstance(null, "\nError:\n", "No content.");
        }
    }

    @Override
    public int getItemCount() {
        return 13;
    }
}