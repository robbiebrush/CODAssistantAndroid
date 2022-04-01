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
    private long lastPulled;
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
        if(System.currentTimeMillis() - lastPulled > 7889400000L) {//3 months
            lastPulled = System.currentTimeMillis();
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
        }
        switch (position) {
            case 0:
                return ViewPager2Fragment.newInstance("https://styles.redditmedia.com/t5_3sgon3/styles/communityIcon_m4hjnmn9nuw71.png?width=256&s=eec96e0cb80b1899e6088cea3e31293a424727a2", "\nCDL Pro Rosters\n", "COD: Vanguard (2022)");
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
    }

    @Override
    public int getItemCount() {
        return 13;
    }

}