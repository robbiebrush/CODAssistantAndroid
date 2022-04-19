package com.example.codassistant.ViewPagers.RosterPager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.codassistant.R;
import com.example.codassistant.apiClasses.RostersSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomViewPager2Adapter extends FragmentStateAdapter {
    private ArrayList<JSONObject> teamList;
    private Context context;
    /*
    private ArrayList<Roster> rosters;
    private boolean firstTime;
    Bitmap vanguardImg;
    */
    String url =
            "https://api.pandascore.co/codmw/teams?token=mvWM5Bc17pu66vR9ecg3q5ZdzX0WG6kallqmeaXbciBD-4DJ_bU";
    /**
     * Instantiates a new Custom view page adapter.
     *
     * @param fragmentActivity the fragment activity
     */
    public CustomViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    public Fragment createFragment(int position) {
/*
        if (rosters.size() == 0) {
            firstTime = true;
        } else {
            firstTime = false;
            roster = rosters.get(position);
        }
        */
        if(/*firsTime || */System.currentTimeMillis() - 0 == 0 /*roster.getLastUpdated() > 7776000000L*/) {//3 months
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                teamList = new ArrayList<JSONObject>();
                                JSONArray teamJsonArray = response;
                                teamList.add(teamJsonArray.getJSONObject(15));//atlanta faze
                                teamList.add(teamJsonArray.getJSONObject(1));//boston breach
                                teamList.add(teamJsonArray.getJSONObject(12));//florida mutineers
                                teamList.add(teamJsonArray.getJSONObject(11));//london royal ravens
                                teamList.add(teamJsonArray.getJSONObject(10));//la guerillas
                                teamList.add(teamJsonArray.getJSONObject(3));//la thieves
                                teamList.add(teamJsonArray.getJSONObject(9));//minnesota rokkr
                                teamList.add(teamJsonArray.getJSONObject(8));//new york subliners
                                teamList.add(teamJsonArray.getJSONObject(6));//paris legion
                                teamList.add(teamJsonArray.getJSONObject(5));//seattle surge
                                teamList.add(teamJsonArray.getJSONObject(4));//toronto ultra
                                //OpTic roster hasn't been updated since roster change; roster on 2 different teams
                                teamList.add(teamJsonArray.getJSONObject(13));//dallas empire (non-existent currently)
                                teamList.add(teamJsonArray.getJSONObject(2));//optic texas
                                teamList.add(teamJsonArray.getJSONObject(14));//optic chicago (non existent currently)

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("VOLLEY_ERROR", "error");
                        }
                    });
            RostersSingleton.getInstance(context).getRequestQueue().add(request);

            /* Didn't successfully cache, teamList was always null, would have came for help, time management changed due to partner contributions

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
            */

        }

        switch (position) {
            case 0:
                return ViewPager2Fragment.newInstance("https://styles.redditmedia.com/t5_3sgon3/styles/communityIcon_m4hjnmn9nuw71.png?width=256&s=eec96e0cb80b1899e6088cea3e31293a424727a2", "\n" + context.getResources().getString(R.string.cdlRosters) + "\n", "COD: Vanguard (2022)");
            case 1:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(0).getString("image_url"), "\n" + teamList.get(0).getString("name") + "\n",teamList.get(0).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(0).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(1).getString("name") + "\" " +  teamList.get(0).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(0).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(1).getString("image_url"), "\n" + teamList.get(1).getString("name") + "\n",teamList.get(1).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(1).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(2).getString("image_url"), "\n" + teamList.get(2).getString("name") + "\n",teamList.get(2).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                            + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(2).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 4:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(3).getString("image_url"), "\n" + teamList.get(3).getString("name") + "\n",teamList.get(3).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(3).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 5:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(4).getString("image_url"), "\n" + teamList.get(4).getString("name") + "\n",teamList.get(4).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(4).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 6:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(5).getString("image_url"), "\n" + teamList.get(5).getString("name") + "\n",teamList.get(5).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(5).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 7:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(6).getString("image_url"), "\n" + teamList.get(6).getString("name") + "\n",teamList.get(6).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(6).getString("last_name") + "\n"
                            + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(6).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 8:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(7).getString("image_url"), "\n" + teamList.get(7).getString("name") + "\n",teamList.get(7).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                            + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(7).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 9:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(12).getString("image_url"), "\n" + teamList.get(12).getString("name") + "\n",teamList.get(13).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(13).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(13).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("first_name") + " \"" + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("name") + "\" " + teamList.get(13).getJSONArray("players").getJSONObject(4).getString("last_name") + "\n"
                            + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(11).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(11).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 10:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(8).getString("image_url"), "\n" + teamList.get(8).getString("name") + "\n",teamList.get(8).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(8).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 11:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(9).getString("image_url"), "\n" + teamList.get(9).getString("name") + "\n",teamList.get(9).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(9).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 12:
                try {
                    return ViewPager2Fragment.newInstance(teamList.get(10).getString("image_url"), "\n" + teamList.get(10).getString("name") + "\n",teamList.get(10).getJSONArray("players").getJSONObject(0).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(0).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(0).getString("last_name") + "\n"
                            + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(1).getString("last_name") + "\n"
                            + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(2).getString("last_name") + "\n"
                            + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("first_name") + " \"" + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("name") + "\" " + teamList.get(10).getJSONArray("players").getJSONObject(3).getString("last_name") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                return ViewPager2Fragment.newInstance("https://freeiconshop.com/wp-content/uploads/edd/document-error-outline.png", "\nError:\n", "No content.");
        }
        /*
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
        */

    }

    @Override
    public int getItemCount() {
        return 13;
    }

}