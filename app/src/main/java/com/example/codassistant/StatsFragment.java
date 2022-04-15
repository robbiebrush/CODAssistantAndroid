package com.example.codassistant;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codassistant.Database.MatchesDatabase;
import com.example.codassistant.Database.pojos.Match;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    private ArrayList<Match> matches;
    private ArrayList<Match> hpMatches = new ArrayList<Match>();
    private ArrayList<Match> sndMatches = new ArrayList<Match>();
    private ArrayList<Match> ctlMatches = new ArrayList<Match>();

    double wins = 0;
    double losses = 0;
    double elims = 0;
    double deaths = 0;
    double winLossRatio = 0;
    double elimDeathRatio = 0;

    double avElims = 0;
    double avDeaths = 0;

    double seconds = 0;
    double plants = 0;
    double objKills = 0;

    int openFlag = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));

        switch (sharedPreferences.getString("filter", "overall")) {
            case "overall":
                MainActivity.filter = 0;
                break;
            case "hp":
                MainActivity.filter = 1;
                break;
            case "snd":
                MainActivity.filter = 2;
                break;
            case "ctl":
                MainActivity.filter = 3;
                break;
        }

        if (sharedPreferences.getString("font", "default").equals("default")) {
            MainActivity.font = 0;
        } else if (sharedPreferences.getString("font" ,"default").equals("large")) {
            MainActivity.font = 1;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        DecimalFormat noDec = new DecimalFormat("#");
        DecimalFormat oneDec = new DecimalFormat("#.#");
        DecimalFormat twoDec = new DecimalFormat("#.##");

        MatchesDatabase db = new MatchesDatabase(getContext());
        matches = db.getAllMatches();

        if (openFlag == 0) {
            for (Match match : matches) {
                if (match.getMode().equals(getResources().getString(R.string.hp))) {
                    hpMatches.add(match);
                } else if (match.getMode().equals("Search and Destroy")) {
                    sndMatches.add(match);
                } else if (match.getMode().equals(getResources().getString(R.string.ctl))) {
                    ctlMatches.add(match);
                }
            }
        }


        TextView filterName = view.findViewById(R.id.filterName);
        TextView winLoss = view.findViewById(R.id.winLoss);
        TextView totalStats = view.findViewById(R.id.totalStats);
        TextView averageStats = view.findViewById(R.id.averageStats);
        TextView objStats = view.findViewById(R.id.objStats);

        TextView objStatsTitle = view.findViewById(R.id.averageObjTitle);
        TextView averageStatsTitle = view.findViewById(R.id.averageStatsTitle);
        TextView winLossTitle = view.findViewById(R.id.winLossTitle);
        TextView totalStatsTitle = view.findViewById(R.id.totalStatsTitle);

        if (MainActivity.font == 0) {
            filterName.setTextSize(getResources().getDimension(R.dimen.title_text) / getResources().getDisplayMetrics().density);
            winLoss.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            totalStats.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            averageStats.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            objStats.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            objStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
            averageStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
            winLossTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
            totalStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
        } else if (MainActivity.font == 1) {
            filterName.setTextSize(getResources().getDimension(R.dimen.title_text_large) / getResources().getDisplayMetrics().density);
            winLoss.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            totalStats.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            averageStats.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            objStats.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            objStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
            averageStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
            winLossTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
            totalStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
        }
        if (matches != null) {
            if (MainActivity.filter == 0) {
                filterName.setText(getResources().getString(R.string.overall));

                for (Match match : matches) {

                    elims += match.getElims();
                    deaths += match.getDeaths();
                    if (deaths == 0) {
                        elimDeathRatio = elims;
                    } else {
                        elimDeathRatio = elims / deaths;
                    }

                    if (match.getOutcome().equals("W")) {
                        wins += 1;
                    } else {
                        losses += 1;
                    }
                    if (losses == 0) {
                        winLossRatio = wins;
                    } else {
                        winLossRatio = wins / losses;
                    }

                    if (match.getMode().equals(getResources().getString(R.string.hp))) {
                        seconds += match.getObj();
                    } else if (match.getMode().equals(getResources().getString(R.string.snd))) {
                        plants += match.getObj();
                    } else if (match.getMode().equals(getResources().getString(R.string.ctl))) {
                        objKills += match.getObj();
                    }
                }
                if (seconds != 0) {
                    seconds = seconds / hpMatches.size();
                }
                if (plants != 0) {
                    plants = plants / sndMatches.size();
                }
                if (objKills != 0) {
                    objKills = objKills / ctlMatches.size();
                }

                if (elims == 0) {
                    avElims = 0;
                    avDeaths = deaths;
                } else {
                    avElims = elims / matches.size();
                    avDeaths = deaths / matches.size();
                }

                if (deaths == 0) {
                    avDeaths = 0;
                    avElims = elims;
                } else {
                    avElims = elims / matches.size();
                    avDeaths = deaths / matches.size();
                }

                winLoss.setText(noDec.format(wins) + "/" + noDec.format(losses) + "     " + twoDec.format(winLossRatio) + "W/L");
                totalStats.setText(noDec.format(elims) + "/" + noDec.format(deaths) + "     " + twoDec.format(elimDeathRatio) + "K/D");
                averageStats.setText(noDec.format(avElims) + "/" + noDec.format(avDeaths));
                objStats.setText(oneDec.format(seconds) + " " + getResources().getString(R.string.secs) + "     " + oneDec.format(plants) + " " + getResources().getString(R.string.plants) + "     " + oneDec.format(objKills) + " obj elims");
                
            } else if (MainActivity.filter == 1) {
                filterName.setText(getResources().getString(R.string.hp));

                for (Match match : hpMatches) {

                    elims += match.getElims();
                    deaths += match.getDeaths();
                    if (deaths == 0) {
                        elimDeathRatio = elims;
                    } else {
                        elimDeathRatio = elims / deaths;
                    }

                    if (match.getOutcome().equals("W")) {
                        wins += 1;
                    } else {
                        losses += 1;
                    }
                    if (losses == 0) {
                        winLossRatio = wins;
                    } else {
                        winLossRatio = wins / losses;
                    }

                    seconds += match.getObj();
                }

                if (seconds != 0) {
                    seconds = seconds / hpMatches.size();
                }

                if (deaths == 0) {
                    avDeaths = 0;
                    avElims = elims;
                } else {
                    avElims = elims / hpMatches.size();
                    avDeaths = deaths / hpMatches.size();
                }

                winLoss.setText(noDec.format(wins) + "/" + noDec.format(losses) + "     " + twoDec.format(winLossRatio) + "W/L");
                totalStats.setText(noDec.format(elims) + "/" + noDec.format(deaths) + "     " + twoDec.format(elimDeathRatio) + "K/D");
                averageStats.setText(noDec.format(avElims) + "/" + noDec.format(avDeaths));
                objStats.setText(oneDec.format(seconds) + " " + getResources().getString(R.string.secs));

            } else if (MainActivity.filter == 2) {
                filterName.setText(getResources().getString(R.string.snd));

                for (Match match : sndMatches) {

                    elims += match.getElims();
                    deaths += match.getDeaths();
                    if (deaths == 0) {
                        elimDeathRatio = elims;
                    } else {
                        elimDeathRatio = elims / deaths;
                    }

                    if (match.getOutcome().equals("W")) {
                        wins += 1;
                    } else {
                        losses += 1;
                    }
                    if (losses == 0) {
                        winLossRatio = wins;
                    } else {
                        winLossRatio = wins / losses;
                    }

                    plants += match.getObj();
                }

                if (plants != 0) {
                    plants = plants / sndMatches.size();
                }

                if (deaths == 0) {
                    avDeaths = 0;
                    avElims = elims;
                } else {
                    avElims = elims / sndMatches.size();
                    avDeaths = deaths / sndMatches.size();
                }

                winLoss.setText(noDec.format(wins) + "/" + noDec.format(losses) + "     " + twoDec.format(winLossRatio) + "W/L");
                totalStats.setText(noDec.format(elims) + "/" + noDec.format(deaths) + "     " + twoDec.format(elimDeathRatio) + "K/D");
                averageStats.setText(noDec.format(avElims) + "/" + noDec.format(avDeaths));
                objStats.setText(oneDec.format(plants) + " " + getResources().getString(R.string.plants));
            } else if (MainActivity.filter == 3) {
                filterName.setText(getResources().getString(R.string.ctl));

                for (Match match : ctlMatches) {

                    elims += match.getElims();
                    deaths += match.getDeaths();
                    if (deaths == 0) {
                        elimDeathRatio = elims;
                    } else {
                        elimDeathRatio = elims / deaths;
                    }

                    if (match.getOutcome().equals("W")) {
                        wins += 1;
                    } else {
                        losses += 1;
                    }
                    if (losses == 0) {
                        winLossRatio = wins;
                    } else {
                        winLossRatio = wins / losses;
                    }

                    objKills += match.getObj();
                }
                
                if (objKills != 0) {
                    objKills = objKills / ctlMatches.size();
                }

                if (deaths == 0) {
                    avDeaths = 0;
                    avElims = elims;
                } else {
                    avElims = elims / ctlMatches.size();
                    avDeaths = deaths / ctlMatches.size();
                }

                winLoss.setText(noDec.format(wins) + "/" + noDec.format(losses) + "     " + twoDec.format(winLossRatio) + "W/L");
                totalStats.setText(noDec.format(elims) + "/" + noDec.format(deaths) + "     " + twoDec.format(elimDeathRatio) + "K/D");
                averageStats.setText(noDec.format(avElims) + "/" + noDec.format(avDeaths));
                objStats.setText(oneDec.format(objKills) + " obj elims");
            }
        } else {
            winLoss.setText("0/0     0W/L");
            totalStats.setText("0/0     0K/D");
            averageStats.setText("0/0");
            objStats.setText("0");
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        wins = 0;
        losses = 0;
        elims = 0;
        deaths = 0;
        winLossRatio = 0;
        elimDeathRatio = 0;
        avElims = 0;
        avDeaths = 0;
        seconds = 0;
        plants = 0;
        objKills = 0;

        openFlag = 1;
    }
}